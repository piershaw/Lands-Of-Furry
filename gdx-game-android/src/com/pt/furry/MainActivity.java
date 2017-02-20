package com.pt.furry;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.pt.furry.BluetoothManager;
import android.os.*;

public class MainActivity extends AndroidApplication {
	
	private TBlue tBlue;
	private String blueToothAdress;
	private BluetoothManager bm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main); //
			blueToothAdress = "00:06:66:48:8A:F9";
		
		   AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
	        //cfg.useGL20 = false;
			cfg.hideStatusBar = true;
	      
	       bm = new BluetoothManager(this,new Handler(),getApplicationContext());
				 bm.enableBluetooth();
			
	        initialize(new MyGdxGame(), cfg);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
			if(tBlue == null)
					tBlue=new TBlue(blueToothAdress); // You must change this! 2 // my MAC id "00:06:66:48:8A:F9" works 
		
		     if (tBlue.streaming()) {
		         Log.i("moto","Connected succesfully! ");
		     } else {
		    	 Log.i("moto","Error: Failed to connect. ");
		     } 
		     String s="";
		     while (tBlue.streaming() && (s.length()<10) ) {
		         s+=tBlue.read(); 
		     }
		     Log.i("moto","Read from Bluetooth: \n"+s);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
	//	if (id == R.id.action_settings) {
			//return true;
		//}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}



	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}


}
