package com.pt.furry;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;



public class AndroidBlueTooth implements iBlueTooth{

	
	public LinkedList<BluetoothDevice> devices;
	private BluetoothDevice connectedDevice = null;
	private int deviceIndex = 0;
	private Activity currentActivity;
	private final Handler mHandler;
	private int mState;
	private AcceptThread mAcceptThread;
	private ConnectThread mConnectThread;
	private ConnectedThread mConnectedThread;

	public static final int STATE_NONE = 0;
	public static final int STATE_LISTEN = 1;
	public static final int STATE_CONNECTING = 2;
	public static final int STATE_CONNECTED = 3;

	public boolean isConnected = false;
	public boolean canConnect = true;

	public String message;
	public boolean messageTaken = true;

	public BluetoothAdapter bta = null;
	
	public Handler uiThread;
	public Context appContext;

	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				devices.add(device);
			}
		}
	};
	



	public boolean isConnected() {
		return isConnected;
	}


	public String getMessage() {
		if (!messageTaken) {
			messageTaken = true;
			if (message != null)
				message = message.substring(0, 2);
			return message;
		}

		return null;
	}

	@Override
	public String getTest() {
		try {
			for (int i = 0; i < devices.size(); i++) {
				if (devices.get(i).getName().contains("HTC")
						|| devices.get(i).getName().contains("GT")) {
					connectedDevice = devices.get(i);
					return devices.get(i).getName();
				}
			}
		} catch (Exception exc) {

		}
		return null;
	}

	@Override
	public String getDevice() {
		try {
			connectedDevice = devices.get(deviceIndex);
			return connectedDevice.getName();
		} catch (Exception exc) {

		}

		return null;
	}

	@Override
	public boolean isLast() {
		try {
			if (connectedDevice == null)
				return true;
			if (devices.isEmpty())
				return true;
			if (connectedDevice.equals(devices.getLast())) {
				return true;
			}
		} catch (Exception exc) {

		}

		return false;
	}

	@Override
	public boolean isFirst() {
		try {
			if (connectedDevice == null)
				return true;
			if (devices.isEmpty())
				return true;
			if (connectedDevice.equals(devices.getFirst())) {
				return true;
			}
		} catch (Exception exc) {

		}

		return false;
	}

	@Override
	public void switchToNextDevice() {
		if (!isLast()) {
			connectedDevice = devices.get(++deviceIndex);
		}
	}

	@Override
	public void switchToPrevDevice() {
		if (!isFirst()) {
			connectedDevice = devices.get(--deviceIndex);
		}
	}

	private void init() {
		devices = new LinkedList<BluetoothDevice>();
	}

	public AndroidBlueTooth(Activity activity, Handler handler,Context appContext) {
		init();

		currentActivity = activity;
		mState = STATE_NONE;
		mHandler = handler;

		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		currentActivity.registerReceiver(mReceiver, filter);
		
	     uiThread = new Handler();
	     this.appContext = appContext;
	     
	}

	private synchronized void setState(int state) {
		mState = state;

		mHandler.obtainMessage(BTActivity.MESSAGE_STATE_CHANGE, state, -1)
				.sendToTarget();
	}

	public synchronized int getState() {
		return mState;
	}

	public synchronized void start() {
		if (mConnectThread != null) {
			mConnectThread.cancel();
			mConnectThread = null;
		}

		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}

		if (mAcceptThread == null) {
			mAcceptThread = new AcceptThread();
			mAcceptThread.start();
		}
		setState(STATE_LISTEN);
	}

	public synchronized void connect(BluetoothDevice device) {
		if (mState == STATE_CONNECTING) {
			if (mConnectThread != null) {
				mConnectThread.cancel();
				mConnectThread = null;
			}
		}

		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}

		mConnectThread = new ConnectThread(device);
		mConnectThread.start();
		setState(STATE_CONNECTING);
	}

	public synchronized void connected(BluetoothSocket socket,
			BluetoothDevice device) {
		if (mConnectThread != null) {
			mConnectThread.cancel();
			mConnectThread = null;
		}

		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}

		if (mAcceptThread != null) {
			mAcceptThread.cancel();
			mAcceptThread = null;
		}

		mConnectedThread = new ConnectedThread(socket);
		mConnectedThread.start();

		Message msg = mHandler.obtainMessage(BTActivity.MESSAGE_DEVICE_NAME);
		Bundle bundle = new Bundle();
		bundle.putString(BTActivity.DEVICE_NAME, device.getName());
		msg.setData(bundle);
		mHandler.sendMessage(msg);

		setState(STATE_CONNECTED);
	}

	@Override
	public synchronized void stop() {
		if (mConnectThread != null) {
			mConnectThread.cancel();
			mConnectThread = null;
		}
		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}
		if (mAcceptThread != null) {
			mAcceptThread.cancel();
			mAcceptThread = null;
		}
		setState(STATE_NONE);
		isConnected = false;
	}

	public void write(byte[] out) {
		ConnectedThread r;
		synchronized (this) {
			if (mState != STATE_CONNECTED)
				return;
			r = mConnectedThread;
		}
		r.write(out);
	}

	private void connectionFailed() {
		setState(STATE_LISTEN);

		canConnect = false;
		Message msg = mHandler.obtainMessage(BTActivity.MESSAGE_TOAST);
		Bundle bundle = new Bundle();
		bundle.putString(BTActivity.TOAST, "Unable to connect device");
		msg.setData(bundle);
		mHandler.sendMessage(msg);
	}

	private void connectionLost() {
		setState(STATE_LISTEN);

		Message msg = mHandler.obtainMessage(BTActivity.MESSAGE_TOAST);
		Bundle bundle = new Bundle();
		bundle.putString(BTActivity.TOAST, "Device connection was lost");
		msg.setData(bundle);
		mHandler.sendMessage(msg);
	}

	@Override
	public void enableDiscoveribility() {
		Intent discoverableIntent = new Intent(
				BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		discoverableIntent.putExtra(
				BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600);
		currentActivity.startActivity(discoverableIntent);
	}

	public BluetoothAdapter getAdapter() {
		if (bta == null) {
			bta = BluetoothAdapter.getDefaultAdapter();
		}

		return bta;
	}
	

	@Override
	public void enableBluetooth() {
		if (!getAdapter().isEnabled()) {
			Intent enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			currentActivity.startActivityForResult(enableIntent, 2);
		}
	}

	@Override
	public boolean isDiscovering() {
		return getAdapter().isDiscovering();
	}
	
	@Override
	public void discoverDevices() {
		if (getAdapter().isDiscovering()) {
			getAdapter().cancelDiscovery();
		}

		getAdapter().startDiscovery();
	}

	@Override
	public void stopDiscovering() {
		getAdapter().cancelDiscovery();
	}

	@Override
	public boolean startServer() { 
		if (getAdapter().isEnabled()) {
			this.start();
			return true;
		}
		
		return false;
	}

	@Override
	public boolean canConnect() {
		return this.canConnect;
	}

	@Override
	public void connectToServer() {
		canConnect = true;
		try {
			BluetoothDevice device = getAdapter().getRemoteDevice(
					connectedDevice.getAddress());
			this.connect(device);
		} catch (Exception exc) {
			
		}
	}

	@Override
	public void sendMessage(String message) {
		if (message.length() > 0) {
			if (message.length() == 1) {
				message = "0" + message;
			}
			byte[] send = message.getBytes();
			this.write(send);
		}
	}

	private class AcceptThread extends Thread {
		private final BluetoothServerSocket mmServerSocket;

		public AcceptThread() {
			BluetoothServerSocket tmp = null;
			try {
				tmp = getAdapter()
						.listenUsingRfcommWithServiceRecord(
								"ServiceName",
								UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEEE"));
			} catch (IOException e) {
			}
			mmServerSocket = tmp;
		}

		@Override
		public void run() {
			BluetoothSocket socket = null;
			while (mState != STATE_CONNECTED) {
				try {
					socket = mmServerSocket.accept();
				} catch (IOException e) {
					break;
				}
				if (socket != null) {
					synchronized (AndroidBlueTooth.this) {
						switch (mState) {
						case STATE_LISTEN:
						case STATE_CONNECTING:
							connected(socket, socket.getRemoteDevice());
							break;
						case STATE_NONE:
						case STATE_CONNECTED:
							try {
								socket.close();
							} catch (IOException e) {
							}
							break;
						}
					}
					try {
						mmServerSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}

		public void cancel() {
			try {
				mmServerSocket.close();
			} catch (IOException e) {
			}
		}
	}

	private class ConnectThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final BluetoothDevice mmDevice;

		public ConnectThread(BluetoothDevice device) {
			BluetoothSocket tmp = null;
			mmDevice = device;

			try {
				tmp = device.createRfcommSocketToServiceRecord(UUID
						.fromString("52468fc0-5243-11e4-916c-0800200c9a66"));
			} catch (IOException e) {
			}
			mmSocket = tmp;
		}

		@Override
		public void run() {
			getAdapter().cancelDiscovery();

			try {
				mmSocket.connect();
			} catch (IOException connectException) {
				connectionFailed();
				try {
					mmSocket.close();
				} catch (IOException closeException) {
				}

				return;
			}

			synchronized (AndroidBlueTooth.this) {
				mConnectThread = null;
			}

			connected(mmSocket, mmDevice);
		}

		public void cancel() {
			try {
				mmSocket.close();
			} catch (IOException e) {
			}
		}
	}

	private class ConnectedThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final InputStream mmInStream;
		private final OutputStream mmOutStream;

		public ConnectedThread(BluetoothSocket socket) {
			mmSocket = socket;
			InputStream tmpIn = null;
			OutputStream tmpOut = null;
			isConnected = true;

			try {
				tmpIn = socket.getInputStream();
				tmpOut = socket.getOutputStream();
			} catch (IOException e) {
			}

			mmInStream = tmpIn;
			mmOutStream = tmpOut;
		}

		@Override
		public void run() {
			byte[] buffer = new byte[1024];
			int bytes;

			while (true) {
				try {
					bytes = mmInStream.read(buffer);
					setMessage(new String(buffer, "UTF-8"));
					mHandler.obtainMessage(BTActivity.MESSAGE_READ, bytes, -1,
							buffer).sendToTarget();
				} catch (IOException e) {
					connectionLost();
					break;
				}
			}
		}

		public void write(byte[] bytes) {
			try {
				mmOutStream.write(bytes);
			} catch (IOException e) {
			}
		}

		public void cancel() {
			try {
				mmSocket.close();
				isConnected = false;
			} catch (IOException e) {
			}
		}
	}

	private void setMessage(String message) {
		if (messageTaken) {
			this.message = message;
			messageTaken = false;
		}
	}

	@Override
	public void showToast(CharSequence toastMessage, int toastDuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAlertBox(String alartBoxTitle, String alartBoxMessage,
			String alartBoxButtonText) {
		// TODO Auto-generated method stub
		
	}
}
