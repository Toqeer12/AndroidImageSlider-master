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

import net.simonvt.menudrawer.MenuDrawer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.HistoryModel;
import adapter.HistoryAdapter;
import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;


/**
 * Created by T.A on 19/05/2015.
 */
public class History extends Activity {


    private MenuDrawer mDrawer;
    List<HistoryModel> historyObject;
    Button btn;
    ListView list;
    HistoryAdapter adapter;
    JSONArray arr = null;
    JSONArray contacts = null;
    String jsonStr;
    String url;
    ServiceHandler sh;
    ConnectionDetector cd;
    LinearLayout layout;
    Advertise advertise;
    Button fix,news,rank,gallery,venue,live,share,home,player,record,moreapps,worldcup,recent_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.history);
        mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        url= Constants.History;
        historyObject=new ArrayList<HistoryModel>();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        list=(ListView)findViewById(R.id.hislist);
        fix=(Button)findViewById(R.id.fixture);
        recent_result=(Button)findViewById(R.id.label);
        news=(Button)findViewById(R.id.news);
        rank=(Button)findViewById(R.id.rank);
        worldcup=(Button)findViewById(R.id.wc);
        gallery=(Button)findViewById(R.id.gallery);
        venue=(Button)findViewById(R.id.venue);
        live=(Button)findViewById(R.id.lscore);
        share=(Button)findViewById(R.id.share);
        moreapps=(Button)findViewById(R.id.mapps);
        record=(Button)findViewById(R.id.record);
        home=(Button)findViewById(R.id.home);
        btn=(Button)findViewById(R.id.title_bar_left_menu);
        player=(Button)findViewById(R.id.pprofile);
        cd = new ConnectionDetector(getApplicationContext());
        advertise=new Advertise();
        layout= (LinearLayout)findViewById(R.id.add);
        advertise.Banner(layout, this);
        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            showAlertDialog(History.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }
            Intent i=getIntent();
            i.getStringExtra("Id");


        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                  home.setBackground(getDrawable(R.drawable.home_pressed));
                Intent i = new Intent(getApplicationContext(), History.class);
                startActivity(i);
                finish();
            }
        });

        recent_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Recent_Result.class);
                startActivity(i);
                finish();
            }
        });
        moreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MoreApps.class);
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
        worldcup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), WorldCup.class);
                startActivity(i);
                finish();

            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                HistoryModel livelistItem;
                livelistItem = historyObject.get(position);
                Log.d("out IF", "" + livelistItem.getTitle());

                Intent i = new Intent(History.this, HistoryDetail.class);
                i.putExtra("Id", livelistItem.getId());
                startActivity(i);


            }


        });

        record.setVisibility(View.GONE);
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
        else
        {

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
        if(CommonMethods.isNetworkAvailable(History.this)) {
            new HistoryThread().execute();
        }
        else
        {

        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    private class HistoryThread extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog


            CommonMethods.showProgressDialog(History.this);

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
                    contacts = jsonObj.getJSONArray("history");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String title = c.getString("Title");
                        historyObject.add(new HistoryModel(id,title));
                        adapter=new HistoryAdapter(History.this,historyObject);
                        list.setAdapter(adapter);

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
