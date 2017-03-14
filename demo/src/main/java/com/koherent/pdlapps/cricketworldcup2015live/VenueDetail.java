package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import net.simonvt.menudrawer.MenuDrawer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.venueitem;
import adapter.ServiceHandler;
import adapter.VenueDetailAdapter;
import utilities.CommonMethods;

/**
 * Created by T.A on 18/05/2015.
 */
public class VenueDetail extends Activity {
    ConnectionDetector cd;
    private MenuDrawer mDrawer;
    String url;
    String venuee = "Venue";
    String City = "City";
    String Link = "Link";
    JSONArray contacts = null;
    ServiceHandler sh;
    ArrayList<venueitem> itemobject;
    String jsonStr;
    JSONArray arr = null;
    VenueDetailAdapter adapter;
    private ListView listView;
    Button btn,back;
        Advertise advertise;
    LinearLayout layout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mDrawer = MenuDrawer.attach(this);
        setContentView(R.layout.ven);
      //  mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        layout= (LinearLayout)findViewById(R.id.add);
        advertise = new Advertise();
        advertise.Banner(layout,this);
        listView=(ListView)findViewById(R.id.listView);
        itemobject = new ArrayList<venueitem>();

        back = (Button) findViewById(R.id.back);

        Intent i = getIntent();
        url = i.getStringExtra("url");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Venues.class);
                startActivity(i);
                finish();
            }
        });
        if (CommonMethods.isNetworkAvailable(VenueDetail.this)) {
            new GetTeamDetail().execute();
        } else {
            cd = new ConnectionDetector(getApplicationContext());

            // Check if Internet present
            if (!cd.isConnectingToInternet()) {
                // Internet Connection is not present
                showAlertDialog(VenueDetail.this,
                        "Internet Connection Error",
                        "Please connect to working Internet connection", false);
                // stop executing code by return
                return;
            }
        }




    }

    private class GetTeamDetail extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            CommonMethods.showProgressDialog(VenueDetail.this);

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            sh = new ServiceHandler();

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray("Toqeer");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String ven = c.getString(venuee);
                        String city = c.getString(City);
                        String link = c.getString(Link);


                        // Phone node is JSON Object
                        Log.d("Response: ", "> " + ven+""+city+link);
                        itemobject.add(new venueitem(ven, city, link));
                        // tmp hashmap for single contact

                        adapter = new VenueDetailAdapter(VenueDetail.this, itemobject);
                        listView.setAdapter(adapter);

                            CommonMethods.hideProgressDialog();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
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


            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

        // Showing Alert Message
        alertDialog.show();
    }
}



