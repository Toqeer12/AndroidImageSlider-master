package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
/*
 * Created by T.A on 20/05/2015.
 */
public class Splash extends Activity {
    ConnectionDetector cd;
    private static int SPLASH_TIME_OUT = 3000;
    AsyncTask<Void, Void, Void> mRegisterTask;
    public static String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            showAlertDialog(Splash.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }
        c1="0";
//        GCMRegistrar.checkDevice(this);
//
//        // Make sure the manifest was properly set - comment out this line
//        // while developing the app, then uncomment it when it's ready.
//        GCMRegistrar.checkManifest(this);
//        registerReceiver(mHandleMessageReceiver, new IntentFilter(
//                DISPLAY_MESSAGE_ACTION));
//
//        // Get GCM registration id
//        final String regId = GCMRegistrar.getRegistrationId(this);
//
//        // Check if regid already presents
//
//        if (regId.equals("")) {
//            // Registration is not present, register now with GCM
//            GCMRegistrar.register(this, SENDER_ID);
//
//        }
//
//		else {
//			// Device is already registered on GCM
//			if (GCMRegistrar.isRegisteredOnServer(this)) {
//				// Skips registration.
//				//Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
//			}
//        else {
//            // Try to register again, but not in the UI thread.
//            // It's also necessary to cancel the thread onDestroy(),
//            // hence the use of AsyncTask instead of a raw thread.
//            final Context context = this;
//            mRegisterTask = new AsyncTask<Void, Void, Void>() {
//
//                @Override
//                protected Void doInBackground(Void... params) {
//                    // Register on our server
//                    // On server creates a new user
//                    ServerUtilities.register(context,c1,c2,c3,c4,c5,c6,c7,c8,c9,c10, regId);
//                    return null;
//                }
//
//                @Override
//                protected void onPostExecute(Void result) {
//                    mRegisterTask = null;
//                }
//
//            };
//            mRegisterTask.execute(null, null, null);
//        }
//    }


    Thread timer = new Thread() {
        public void run() {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                Intent openStartingPoint = new Intent(Splash.this, MainActivity.class);
                startActivity(openStartingPoint);
                finish();

            }
        }
    };

    timer.start();

    }



    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if(status != null)
            // Setting alert dialog icon
            //    alertDialog.setIcon((status) ? R.drawable.icon : R.drawable.icon);

            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

        // Showing Alert Message
        alertDialog.show();
    }

//private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
//        // Waking up mobile if it is sleeping
//        WakeLocker.acquire(getApplicationContext());
//
//        /**
//         * Take appropriate action on this message
//         * depending upon your app requirement
//         * For now i am just displaying it on the screen
//         * */
//
//        // Showing received message
//
//        Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
//
//        // Releasing wake lock
//        WakeLocker.release();
//    }
//};
//
//    @Override
//    protected void onDestroy() {
//        if (mRegisterTask != null) {
//            mRegisterTask.cancel(true);
//        }
//        try {
//            unregisterReceiver(mHandleMessageReceiver);
//            GCMRegistrar.onDestroy(this);
//        } catch (Exception e) {
//            Log.e("UnRegister Receivr", e.getMessage().toString());
//        }
//        super.onDestroy();
//    }
}
