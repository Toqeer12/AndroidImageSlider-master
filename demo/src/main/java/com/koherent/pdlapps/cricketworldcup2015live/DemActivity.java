package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;

import utilities.AlertDialogManager;

import static utilities.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static utilities.CommonUtilities.EXTRA_MESSAGE;
import static utilities.CommonUtilities.SENDER_ID;

public class DemActivity extends Activity {
	// label to display gcm messages
	TextView lblMessage;
	String key, type, test,current,inn,day;
	// Asyntask
	AsyncTask<Void, Void, Void> mRegisterTask;
	
	// Alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();
	
	// Connection detector
	ConnectionDetector cd;
	
	public static String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
	public static String email;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(DemActivity.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}


		// Getting name, email from intent
		Intent i = getIntent();
		
		c1 = i.getStringExtra("country1");
		c2 = i.getStringExtra("country2");
		c3 = i.getStringExtra("country3");
		c4 = i.getStringExtra("country4");
		c5 = i.getStringExtra("country5");
		c6= i.getStringExtra("country6");
		c7= i.getStringExtra("country7");
		c8= i.getStringExtra("country8");
		c9= i.getStringExtra("country9");
		c10= i.getStringExtra("country10");

		i = getIntent();
		key = i.getStringExtra("key");
		type = i.getStringExtra("type");
		inn=i.getStringExtra("innings");
		day=i.getStringExtra("day");
		// Make sure the device has the proper dependencies.
		GCMRegistrar.checkDevice(this);

		// Make sure the manifest was properly set - comment out this line
		// while developing the app, then uncomment it when it's ready.
		GCMRegistrar.checkManifest(this);


		
		registerReceiver(mHandleMessageReceiver, new IntentFilter(
				DISPLAY_MESSAGE_ACTION));
		
		// Get GCM registration id
		final String regId = GCMRegistrar.getRegistrationId(this);

		// Check if regid already presents
		if (regId.equals("")) {
			// Registration is not present, register now with GCM			
			GCMRegistrar.register(this, SENDER_ID);
		}

//		else {
//			// Device is already registered on GCM
//			if (GCMRegistrar.isRegisteredOnServer(this)) {
//				// Skips registration.
//				Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
//			}
 else {
				// Try to register again, but not in the UI thread.
				// It's also necessary to cancel the thread onDestroy(),
				// hence the use of AsyncTask instead of a raw thread.
				final Context context = this;
				mRegisterTask = new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						// Register on our server
						// On server creates a new user
						ServerUtilities.register(context,c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,regId);
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						mRegisterTask = null;
					}

				};
				mRegisterTask.execute(null, null, null);
			}
		}


	/**
	 * Receiving push messages
	 * */
	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
			// Waking up mobile if it is sleeping
			WakeLocker.acquire(getApplicationContext());
			
			/**
			 * Take appropriate action on this message
			 * depending upon your app requirement
			 * For now i am just displaying it on the screen
			 * */
			
			// Showing received message
			//Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
			
			// Releasing wake lock
			WakeLocker.release();

			Intent i=new Intent(getApplicationContext(),FullCardTesting.class);

			i.putExtra("key", key);
			i.putExtra("type", type);
			i.putExtra("innings", inn);
			i.putExtra("day", day);
			startActivity(i);
            finish();
		}
	};
	
	@Override
	protected void onDestroy() {
		if (mRegisterTask != null) {
			mRegisterTask.cancel(true);
		}
		try {
			unregisterReceiver(mHandleMessageReceiver);
			GCMRegistrar.onDestroy(this);
		} catch (Exception e) {
			Log.e("UnRegister Receivr", e.getMessage().toString());
		}
		super.onDestroy();
	}

}
