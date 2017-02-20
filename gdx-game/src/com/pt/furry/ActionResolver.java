package com.pt.furry;

public interface ActionResolver {
      public void showToast(CharSequence toastMessage, int toastDuration);
      public void showAlertBox(String alertBoxTitle, String alertBoxMessage, String alertBoxButtonText);
	  public void enableBluetooth();
	  public void enableDiscoveribility();
	  public void discoverDevices();
	  public void stopDiscovering();
	  public boolean startServer();
	  public void connectToServer();
	  public String getTest();
	  public void sendMessage(String message);
	  public String getMessage();
	  public boolean isConnected();
	  public boolean canConnect();
	  public void switchToNextDevice();
	  public void switchToPrevDevice();
	  public String getDevice();
	  public void stop();
	  public boolean isFirst();
	  public boolean isLast();
	  public boolean isDiscovering();
}
