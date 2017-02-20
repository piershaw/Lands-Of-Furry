package com.pt.furry;

public class BluetoothSingleton {
	 
    private static volatile BluetoothSingleton instance = null;
    public iBlueTooth bluetooth;
    
    public static BluetoothSingleton getInstance() {
    	  if (instance == null) {
              synchronized (BluetoothSingleton.class) {
                  if (instance == null) {
                      instance = new BluetoothSingleton();
                  }
              }
          }
          return instance;
    }
     
}