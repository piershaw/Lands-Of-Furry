package com.pt.furry;

import com.pt.furry.BluetoothManager;
import com.pt.furry.BluetoothSingleton;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class BTActivity extends Activity {
 
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
 
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        BluetoothManager btm = new BluetoothManager(this, new Handler(), this.getApplicationContext());
        btm.getAdapter();
        BluetoothSingleton.getInstance().bluetooth = btm;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(BTActivity.this, MainActivity.class);
        BTActivity.this.startActivity(intent);
    }
}