package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.simonvt.menudrawer.MenuDrawer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import Model.LivelistItem;
import adapter.LiveListAdapter;
import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by T.A on 21/05/2015.
 */
public class LiveList extends Activity {

    private static final String LIVE_LIST_KEY = "livelist";
    private MenuDrawer mDrawer;
    ListView listview;
    ArrayList<LivelistItem> livelistArray = new ArrayList<LivelistItem>();
    String team1 = "teamAname";
    String team2 = "teamBname";
    String key = "match_key";
   String date="start_date";
//    String Check="Check";
    String status="status";
    String jsonStr;
    String statuss;
    String link;
    JSONArray arr = null;
    JSONArray contacts = null;
    ServiceHandler sh;
    LiveListAdapter adapter;
    TextView tv;
    ConnectionDetector cd;
    LinearLayout layout;
    String url= Constants.LiveMatchDetail_URL;

            //"http://pdlapps.com/crictoday/teamname.php";
    Button btn;
    String ch;
    Button fix,news,rank,gallery,venue,live,share,home,player,record,moreapps,recent_match,worldcup;

    Advertise advertise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.livelist);
        mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        layout= (LinearLayout)findViewById(R.id.add);
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        final boolean addd = sharedPreferences.getBoolean("add", false);
        tv = (TextView)findViewById(R.id.marque);

        layout= (LinearLayout)findViewById(R.id.add);
        advertise = new Advertise();
        advertise.Banner(layout, this);

        if(addd)
        {

        }
        else
        {
            advertise.InterstitialAd(LiveList.this);
        }

//        tv.setSelected(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        recent_match=(Button)findViewById(R.id.label);
        fix=(Button)findViewById(R.id.fixture);
        news=(Button)findViewById(R.id.news);
        rank=(Button)findViewById(R.id.rank);
        gallery=(Button)findViewById(R.id.gallery);
        venue=(Button)findViewById(R.id.venue);
        live=(Button)findViewById(R.id.lscore);
        share=(Button)findViewById(R.id.share);
        moreapps=(Button)findViewById(R.id.mapps);
        record=(Button)findViewById(R.id.record);
        home=(Button)findViewById(R.id.home);
        btn=(Button)findViewById(R.id.title_bar_left_menu);
        player=(Button)findViewById(R.id.pprofile);
        listview = (ListView) findViewById(R.id.gridView1);
        worldcup=(Button)findViewById(R.id.wc);

        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            showAlertDialog(LiveList.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }

        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                  home.setBackground(getDrawable(R.drawable.home_pressed));
                Intent i=new Intent(getApplicationContext(),History.class);
                startActivity(i);
                finish();
            }
        });
        moreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MoreApps.class);
                startActivity(i);
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                  home.setBackground(getDrawable(R.drawable.home_pressed));
                Intent i=new Intent(getApplicationContext(),Player_Stats.class);
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

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                  home.setBackground(getDrawable(R.drawable.home_pressed));
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
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
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LiveList.class);
                savePreferences("add", false);

                startActivity(i);
                finish();
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
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),PictureGallery.class);
                startActivity(i);
                finish();
            }
        });
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Ranking.class);
                startActivity(i);
                finish();
            }
        });
        venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Venues.class);
                startActivity(i);
                finish();
            }
        });
        recent_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Recent_Result.class);
                startActivity(i);
                finish();
            }
        });
        worldcup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WorldCup.class);
                startActivity(i);
                finish();
            }
        });

        record.setVisibility(View.GONE);
        moreapps.setVisibility(View.GONE);
        worldcup.setVisibility(View.GONE);


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
        if(CommonMethods.isNetworkAvailable(LiveList.this)) {
            new GetContacts().execute();

            if(checkBoxValue1)
            {
                worldcup.setVisibility(View.VISIBLE);

                fix.setVisibility(View.GONE);
                recent_match.setVisibility(View.GONE);
                live.setVisibility(View.GONE);

            }
            else {

                worldcup.setVisibility(View.GONE);
                //        record.setVisibility(View.VISIBLE);
                fix.setVisibility(View.VISIBLE);
                recent_match.setVisibility(View.VISIBLE);
                live.setVisibility(View.VISIBLE);

            }


            if(checkBoxValue2)
            {

                fix.setVisibility(View.VISIBLE);
                recent_match.setVisibility(View.VISIBLE);
                live.setVisibility(View.VISIBLE);
            }
            else {
                fix.setVisibility(View.GONE);
                recent_match.setVisibility(View.GONE);
                live.setVisibility(View.GONE);
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(LiveList.this);
            Gson gson = new Gson();
            String json1 = sharedPrefs.getString(LIVE_LIST_KEY, null);

            //String json2 = sharedPrefs.getString(LIST_2_KEY, null);
            if(json1==null)
            {
                Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
                //finish();
                onBackPressed();
            }
            else {
                Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
                Type type = new TypeToken<ArrayList<LivelistItem>>() {
                }.getType();
                livelistArray = gson.fromJson(json1, type);
                adapter = new LiveListAdapter(LiveList.this, livelistArray);
                listview.setAdapter(adapter);
            }
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                LivelistItem livelistItem;
                livelistItem = livelistArray.get(position);
                Log.d("out IF", "" + livelistItem.getKey());
//                if(livelistItem.getCheck().equalsIgnoreCase("true"))
//                {
//                    Bundle b = new Bundle();
//
                    Intent i = new Intent(LiveList.this, TestActivity.class);
                    i.putExtra("key",livelistItem.getKey());
                    i.putExtra("date",livelistItem.getDate());
                    i.putExtra("Live","Live");
                    startActivity(i);


            }


        });


    }
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute(){
            livelistArray.clear();
            super.onPreExecute();
            // Showing progress dialog


            CommonMethods.showProgressDialog(LiveList.this);

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

            Log.d("Response: ", "> " + jsonStr);
         if(jsonStr!=null)
            {


                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray("Toqeer");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String team = c.getString(team1);
                        String teamttwo = c.getString(team2);
                        String match_key = c.getString(key);
                        String dateformat=c.getString(date);
                        String season = c.getString("season_name");
                        String ven = c.getString("venue");
                        String related=c.getString("related_name");

                         statuss=c.getString(status);
                            if(statuss.equalsIgnoreCase("0"))
                            {
                                listview.setVisibility(View.GONE);
                                tv.setText("No Live Match Today");
                                CommonMethods.hideProgressDialog();
                            }
                        // Phone node is JSON Object
                            else {

                                tv.setVisibility(View.GONE);
                                livelistArray.add(new LivelistItem(team, teamttwo, statuss, match_key, dateformat, season, ven, related));
                                // tmp hashmap for single contact

                                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(LiveList.this);
                                SharedPreferences.Editor editor = sharedPrefs.edit();
                                Gson gson = new Gson();

                                String json = gson.toJson(livelistArray);

                                editor.putString(LIVE_LIST_KEY, json);

                                editor.commit();


                                adapter = new LiveListAdapter(LiveList.this, livelistArray);
                                listview.setAdapter(adapter);

                                CommonMethods.hideProgressDialog();

                            }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();

                    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(LiveList.this);
                    Gson gson = new Gson();
                    String json1 = sharedPrefs.getString(LIVE_LIST_KEY, null);
                    //String json2 = sharedPrefs.getString(LIST_2_KEY, null);

                    if(json1==null)
                    {
                        Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
                        //finish();
                        onBackPressed();
                    }
                    else {
                        Type type = new TypeToken<ArrayList<LivelistItem>>() {
                        }.getType();
                        livelistArray = gson.fromJson(json1, type);
                        adapter = new LiveListAdapter(LiveList.this, livelistArray);
                        listview.setAdapter(adapter);
                    }


                }

            }
        }
    }
    @Override
    public void onBackPressed() {
            super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
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
    public void savePreferences(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}
