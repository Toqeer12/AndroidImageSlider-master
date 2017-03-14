package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import net.simonvt.menudrawer.MenuDrawer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.Items;
import adapter.CustomAdapter;
import adapter.Item;
import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by T.A on 13/05/2015.
 */
public class Fixture extends Activity {


//    private RevMob revmob;
//    RevMobAdsListener revmobListener = new RevMobAdsListener() {
//
//        // Required
//        @Override
//        public void onRevMobSessionIsStarted() {
//            Log.d("[Toqeer", "RevMob session is started");
//            showBanner();
//        }
//
//        @Override
//        public void onRevMobSessionNotStarted(String message) {
//            Log.d("Toqeer", "RevMob session failed to start");
//        }
//
//        // Optional
//        @Override
//        public void onRevMobAdDisplayed() {
//            Log.d("Toqeer", "onAdDisplayed");
//        }
//
//        @Override
//        public void onRevMobAdReceived() {
//            Log.d("Toqeer", "onAdReceived");
//        }
//
//        @Override
//        public void onRevMobAdNotReceived(String message) {
//            Log.d("Toqeer", "onAdNotReceived");
//        }
//
//        @Override
//        public void onRevMobAdDismissed() {
//            Log.d("Toqeer", "onAdDismissed");
//        }
//
//        @Override
//        public void onRevMobAdClicked() {
//            Log.d("Toqeer", "onAdClicked");
//        }
//    };
//
//
//    void showBanner() {
//        final RevMobBanner banner = revmob.createBanner(this, revmobListener);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("Toqeer","Inthread");
//                ViewGroup view = (ViewGroup) findViewById(R.id.add);
//                view.addView(banner);
//            }
//        });
//    }
    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    String team1 = "teamAname";
    String team2 = "teamBname";
    String series = "related_name";
    String venue = "venue";
    String abc = "start_date";
    String teamAkey="teamAkey";
    String teamBkey="teamBkey";
    String jsonStr;
    JSONArray arr = null;
    LinearLayout layout;

    public static final String FIXTURES_KEY = "fixtures";

    //    private ProgressDialog pDialog;
    private static String url = Constants.Fixture;
    JSONArray contacts = null;
    ServiceHandler sh;
    ArrayList<Items> itemobject;
    CustomAdapter adapter;
    private MenuDrawer mDrawer;
    Button btn;
    TextView home;
    Advertise advertise;
    ConnectionDetector cd;
    TextView news,gallery,rank,venuee,live,share,fix,player,record,moreapps,worldcup,recent_result,title;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fixture);
        itemobject = new ArrayList<Items>();

        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.fixture);
        mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        gridView = (GridView) findViewById(R.id.gridView1);
        layout= (LinearLayout)findViewById(R.id.add);
        advertise = new Advertise();
        advertise.Banner(layout, this);
        advertise.InterstitialAd(Fixture.this);
//        revmob = RevMob.startWithListener(this, revmobListener);
//        showBanner();
//        revmob.showFullscreen(this);
        title=(TextView)findViewById(R.id.titled);
        player=(TextView)findViewById(R.id.pprofile);
        recent_result=(Button)findViewById(R.id.label);
        record=(TextView)findViewById(R.id.record);
        home=(TextView)findViewById(R.id.home);
        news=(TextView)findViewById(R.id.news);
        rank = (TextView) findViewById(R.id.rank);
        gallery=(TextView)findViewById(R.id.gallery);
        venuee=(TextView)findViewById(R.id.venue);
        live=(TextView)findViewById(R.id.lscore);
        moreapps=(TextView)findViewById(R.id.mapps);
        worldcup=(TextView)findViewById(R.id.wc);
        fix=(TextView)findViewById(R.id.fixture);
        btn=(Button)findViewById(R.id.title_bar_left_menu);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        share=(TextView)findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareBody = "https://play.google.com/store/apps/details?id=com.koherent.pdlapps.cricketworldcup2015live&hl=en";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "CricDroid App");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.CricDroid)));
            }
        });
        recent_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Recent_Result.class);
                startActivity(i);
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openMenu();
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),News.class);
                startActivity(i);
                finish();
            }
        });
        moreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MoreApps.class);
                startActivity(i);
                finish();
            }
        });

        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Fixture.class);
                startActivity(i);
                finish();
            }
        });
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), LiveList.class);
                startActivity(i);
                finish();
            }
        });
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), History.class);
                startActivity(i);
                finish();
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), Player_Stats.class);
                startActivity(i);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Ranking.class);
                startActivity(i);
                finish();

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),PictureGallery.class);
                startActivity(i);
                finish();

            }
        });
        venuee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Venues.class);
                startActivity(i);
                finish();

            }
        });
        worldcup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), WorldCup.class);
                startActivity(i);
                finish();

            }
        });
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mNav.toggleLeftDrawer();
//
//            }
//        });
record.setVisibility(View.GONE);

        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            showAlertDialog(Fixture.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }
        moreapps.setVisibility(View.GONE);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        final boolean checkBoxValue1 = sharedPreferences.getBoolean("wc", false);
        final boolean checkBoxValue2 = sharedPreferences.getBoolean("sp", false);
        final boolean checkBoxValue3 = sharedPreferences.getBoolean("pg", false);
        if(checkBoxValue3)
        {
            gallery.setVisibility(View.GONE);
        }
        else
        {
            gallery.setVisibility(View.VISIBLE);
        }
        if(checkBoxValue1)
        {
            worldcup.setVisibility(View.VISIBLE);

            fix.setVisibility(View.GONE);
            recent_result.setVisibility(View.GONE);
            live.setVisibility(View.GONE);

        }
        else {

            worldcup.setVisibility(View.GONE);
          //  record.setVisibility(View.VISIBLE);
            fix.setVisibility(View.VISIBLE);
            recent_result.setVisibility(View.VISIBLE);
            live.setVisibility(View.VISIBLE);

        }
        if(checkBoxValue2)
        {

            fix.setVisibility(View.VISIBLE);
            recent_result.setVisibility(View.VISIBLE);
            live.setVisibility(View.VISIBLE);
        }
        else {
            fix.setVisibility(View.GONE);
            recent_result.setVisibility(View.GONE);
            live.setVisibility(View.GONE);
        }
            if(CommonMethods.isNetworkAvailable(Fixture.this)) {
            new GetContacts().execute();
        }
        else {
            Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();

//            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(Fixture.this);
//            Gson gson = new Gson();
//            String json1 = sharedPrefs.getString(FIXTURES_KEY, null);
//            //String json2 = sharedPrefs.getString(LIST_2_KEY, null);
//            if (json1 == null) {
//                Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
//                //mHandler.removeCallbacks(m_Runnable);
//                // finish();
//                //onBackPressed();
//            } else {
//                Type type = new TypeToken<ArrayList<Items>>() {
//                }.getType();
//                itemobject = gson.fromJson(json1, type);
//                //battingTeamTwoList = gson.fromJson(json2, type);
//
//                adapter = new CustomAdapter(Fixture.this, itemobject);
//                gridView.setAdapter(adapter);
//            }
        }


    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog


            CommonMethods.showProgressDialog(Fixture.this);

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            sh = new ServiceHandler();

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);



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

                        String team = c.getString(team1);
                        String teamtwo = c.getString(team2);
                        String ser = c.getString(series);
                        String loc = c.getString(venue);
                        String date = c.getString(abc);
                        String teamA=c.getString(teamAkey);
                        String teamB=c.getString(teamBkey);
                        String status=c.getString("status");
                        // Phone node is JSON Object
                        if(status.equalsIgnoreCase("0"))
                        {
                            gridView.setVisibility(View.GONE);
                            title.setText("No match in current month!");
                            CommonMethods.hideProgressDialog();
                        }
                        // Phone node is JSON Object
                        else {
                            title.setVisibility(View.GONE);
                            itemobject.add(new Items(team, teamtwo, ser, loc, date, teamA, teamB));
                            // tmp hashmap for single contact
                            Log.d("Response: ", "> " + date);

//                        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(Fixture.this);
//                        SharedPreferences.Editor editor = sharedPrefs.edit();
//                        Gson gson = new Gson();
//
//                        String json = gson.toJson(itemobject);
//
//                        editor.putString(FIXTURES_KEY, json);
//
//                        editor.commit();
                            adapter = new CustomAdapter(Fixture.this, itemobject);
                            gridView.setAdapter(adapter);
                            CommonMethods.hideProgressDialog();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();

                    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(Fixture.this);
                    Gson gson = new Gson();
                    String json1 = sharedPrefs.getString(FIXTURES_KEY, null);
                    //String json2 = sharedPrefs.getString(LIST_2_KEY, null);
//                    if(json1==null)
//                    {
//                        Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
//                        //mHandler.removeCallbacks(m_Runnable);
//                        // finish();
//                        //onBackPressed();
//                    }
//                    else {
//                        Type type = new TypeToken<ArrayList<Items>>() {
//                        }.getType();
//                        itemobject = gson.fromJson(json1, type);
//                        //battingTeamTwoList = gson.fromJson(json2, type);
//
//                        adapter = new CustomAdapter(Fixture.this, itemobject);
//                        gridView.setAdapter(adapter);
//                    }

                }
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
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
}
