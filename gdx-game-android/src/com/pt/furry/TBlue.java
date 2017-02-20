package com.pt.furry;

//tBlue.java - simple wrapper for Android Bluetooth libraries
//used for game BlueTooth connection 
//UUID for electronic devices 00001101-0000-1000-8000-00805F9834FB keep hard to find


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class TBlue implements iBlueTooth{ 
 private String address=null; 
 private String TAG="tBlue";
 private BluetoothAdapter localAdapter=null;
 private BluetoothDevice remoteDevice=null;
 private BluetoothSocket socket=null;
 public OutputStream outStream = null;
 public InputStream inStream=null;
 private  boolean isConnected;
 private boolean failed=false;

 public TBlue(String address) {
	 
     this.address=address.toUpperCase();
     localAdapter = BluetoothAdapter.getDefaultAdapter(); 
     if((localAdapter!=null) && localAdapter.isEnabled()) {
         Log.i(TAG, "Bluetooth adapter found and enabled on phone. ");
     } else {
         Log.e(TAG, "Bluetooth adapter NOT FOUND or NOT ENABLED!");
         return;
     }
     
     isConnected = false;
     connect(); 
 } 

 public void connect()
 {
     Log.i(TAG, "Bluetooth connecting to "+address+"...");
     try     {
         remoteDevice = localAdapter.getRemoteDevice(address); 
     } catch (IllegalArgumentException e) {
         Log.e(TAG, "Failed to get remote device with MAC address."
                 +"Wrong format? MAC address must be upper case. ", 
                 e);
         return;
     }

     Log.i(TAG, "Creating RFCOMM socket..."); 
     try {
         Method m = remoteDevice.getClass().getMethod
                 ("createRfcommSocket", new Class[] { int.class });
         socket = (BluetoothSocket) m.invoke(remoteDevice, 1); 
         Log.i(TAG, "RFCOMM socket created.");
     } catch (NoSuchMethodException e) {
         Log.i(TAG, "Could not invoke createRfcommSocket.");
         e.printStackTrace();
     } catch (IllegalArgumentException e) {
         Log.i(TAG, "Bad argument with createRfcommSocket.");
         e.printStackTrace();
     } catch (IllegalAccessException e) {
         Log.i(TAG, "Illegal access with createRfcommSocket.");
         e.printStackTrace();
     } catch (InvocationTargetException e) {
         Log.i(TAG, "Invocation target exception: createRfcommSocket.");
         e.printStackTrace();
     }
     Log.i(TAG, "Got socket for device "+socket.getRemoteDevice()); 
     localAdapter.cancelDiscovery(); 

     Log.i(TAG, "Connecting socket...");
     try {
         socket.connect(); 
         Log.i(TAG, "Socket connected.");
         
        
         
     } catch (IOException e) {
         try {
             Log.e(TAG, "Failed to connect socket. ", e);
             socket.close();
             Log.e(TAG, "Socket closed because of an error. ", e);
         } catch (IOException eb) {
             Log.e(TAG, "Also failed to close socket. ", eb);
         }
         return;
     }

     try {
         outStream = socket.getOutputStream(); 
         Log.i(TAG, "Output stream open.");
         inStream = socket.getInputStream();
         Log.i(TAG, "Input stream open.");
     } catch (IOException e) {
         Log.e(TAG, "Failed to create output stream.", e);  
     }
     return;
 }

 public void write(String s) 
 {
     Log.i(TAG, "Sending \""+s+"\"... "); 
     byte[] outBuffer= s.getBytes(); 
     try {
         outStream.write(outBuffer);
     } catch (IOException e) {
         Log.e(TAG, "Write failed.", e); 
     }

 }

 public boolean streaming()
 {
     return ( (inStream!=null) && (outStream!=null) );
 }

 public String read()
 {
     if (!streaming()) return "";
     String inStr="";
     try {
         if (0<inStream.available()) {
             byte[] inBuffer = new byte[1024];
             int bytesRead = inStream.read(inBuffer);
             inStr = new String(inBuffer, "ASCII");
             inStr=inStr.substring(0, bytesRead);
             Log.i(TAG, "byteCount: "+bytesRead+ ", inStr: "+inStr);
             //i put true for 1st response from modem 
             isConnected = true;
         }
     } catch (IOException e) {
         Log.e(TAG, "Read failed", e); 
     }
     return inStr;
 }

 public void close()
 {
     Log.i(TAG, "Bluetooth closing... ");
     try    {
         socket.close();
         Log.i(TAG, "BT closed");
         isConnected = false;
     } catch (IOException e2) {
         Log.e(TAG, "Failed to close socket. ", e2); 
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

@Override
public void enableBluetooth() {
	// TODO Auto-generated method stub
	
}

@Override
public void enableDiscoveribility() {
	// TODO Auto-generated method stub
	
}

@Override
public void discoverDevices() {
	// TODO Auto-generated method stub
	
}

@Override
public void stopDiscovering() {
	// TODO Auto-generated method stub
	
}

@Override
public boolean startServer() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void connectToServer() {
	// TODO Auto-generated method stub
	
}

@Override
public String getTest() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void sendMessage(String message) {
	write(message);
}

@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean isConnected() {
	return isConnected;
}

@Override
public boolean canConnect() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void switchToNextDevice() {
	// TODO Auto-generated method stub
	
}

@Override
public void switchToPrevDevice() {
	// TODO Auto-generated method stub
	
}

@Override
public String getDevice() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void stop() {
	// TODO Auto-generated method stub
	
}

@Override
public boolean isFirst() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean isLast() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean isDiscovering() {
	// TODO Auto-generated method stub
	return false;
}
}