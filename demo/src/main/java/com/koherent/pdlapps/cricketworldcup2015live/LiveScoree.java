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
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Model.LiveScore;
import adapter.ListScoreAdapter;
import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by T.A on 17/05/2015.
 */
public class LiveScoree extends Activity{

    private static String LIVESCORE_KEY = "livescore";
    String jsonStr;
    public String url;
    public static String runstr;
    ProgressDialog pDialog;
    String match_current_innings = "match_current_innings";
    String toss = "Vs";
    String bothTeamsName = "toss";
    String battingTeamName = "current_bat_team";
    String batTeamScore = "current_score";
    public static String bat1Name;
    String batsmen1Runs = "str_Score";
    String batsmen1Balls = "str_ball";
    String batsmen2Name = "non_str_name";
    String batsmen2Runs = "non_str_score";
    String batsmen2Balls = "non_str_ball";
    String bowlingTeamName = "current_bowl_team";
    String bowler1Name = "bowler_name";
    String bowler1Overs = "bowler_overs";
    String bowler1Maid = "bowlers_maiden";
    String bowler1Runs = "bowler_runs";
    String bowler1Wickets = "bowlers_wickets";
    String teamAkey="teamAKey";
    String day="Day";
    String teamBkey="teamBKey";
    public static String teamAname;
    public static String teamBname;

    String link="Link";
    String linnk;
    public static String info;
    public static String mom;
    Thread myThread;
    boolean running;
    int cnt;
    int refreshCounter = 1;


    //String link = "LINK_TO_SCORECARD";

    int key;

    private ArrayList<LiveScore> liveScoreList;
    private ListScoreAdapter listScoreAdapter;
    private ListView listView;
    private View view;
    ServiceHandler sh;
   // private MenuDrawer mDrawer;

    LinearLayout layout;
    Button btn,back,refresh;
    TextView home;
    TextView news,gallery,rank,venuee,fix,share,live,player,record,moreapps,comentry,worldcup;
    String teamName1,teamName2;
    Handler mHandler;
    Thread thread;
    String match_key,type,inn,cat,dayyy;
    public static String breakreason;
    JSONArray contacts = null;
    Advertise advertise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liveScoreList = new ArrayList<LiveScore>();
        url= Constants.DemoAct;
       // getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //mDrawer = MenuDrawer.attach(this);
        setContentView(R.layout.livescore);
       // mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        layout= (LinearLayout)findViewById(R.id.add);
        advertise = new Advertise();
        advertise.Banner(layout, this);

        comentry=(TextView)findViewById(R.id.comentry);
        listView = (ListView) findViewById(R.id.listView1);
        player=(TextView)findViewById(R.id.pprofile);
        record=(TextView)findViewById(R.id.record);

        home=(TextView)findViewById(R.id.home);
        news=(TextView)findViewById(R.id.news);
        rank = (TextView) findViewById(R.id.rank);
        gallery=(TextView)findViewById(R.id.gallery);
        venuee=(TextView)findViewById(R.id.venue);
        fix=(TextView)findViewById(R.id.fixture);
        moreapps=(TextView)findViewById(R.id.mapps);
        live=(TextView)findViewById(R.id.lscore);
        back=(Button)findViewById(R.id.back);
        refresh=(Button)findViewById(R.id.refresh);
        btn=(Button)findViewById(R.id.title_bar_left_menu);
        share=(TextView)findViewById(R.id.share);
        worldcup=(Button)findViewById(R.id.wc);
        Log.i("position in live score", LIVESCORE_KEY);
        Intent i=getIntent();
        match_key=i.getStringExtra("key");
        type=i.getStringExtra("Live");
        cat=i.getStringExtra("cat");
        inn=i.getStringExtra("innings");
        dayyy=i.getStringExtra("day");
//        Log.d("Msg",type);
        this.mHandler = new Handler();

        this.mHandler.postDelayed(m_Runnable, 1000 * 30);
        Log.d("", "" + url);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(m_Runnable);
if(type.equalsIgnoreCase("Live")) {
    Intent i = new Intent(getApplicationContext(), LiveList.class);
    savePreferences("add",true);
    startActivity(i);
    finish();
}
                else if(type.equalsIgnoreCase("wc"))
{
    Intent i = new Intent(getApplicationContext(), WorldCup.class);

    startActivity(i);
    finish();
}
else
{
    Intent i = new Intent(getApplicationContext(), Recent_Result.class);

    startActivity(i);
    finish();
}
            }
        });


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommonMethods.isNetworkAvailable(LiveScoree.this)) {
                    refreshCounter = 1;
                    new FetchLiveScore().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
                }
            }
        });



        if(CommonMethods.isNetworkAvailable(LiveScoree.this)) {
            new FetchLiveScore().execute();
        }
        else
        {

            Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
            CommonMethods.hideProgressDialog();
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(LiveScoree.this);
            Gson gson = new Gson();
            String json1 = sharedPrefs.getString(LIVESCORE_KEY, null);
            //String json2 = sharedPrefs.getString(LIST_2_KEY, null);
            if(json1==null)
            {
                Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
                mHandler.removeCallbacks(m_Runnable);
                finish();
                //onBackPressed();
            }
            else {
                Type type = new TypeToken<ArrayList<LiveScore>>() {
                }.getType();
                liveScoreList = gson.fromJson(json1, type);
                listScoreAdapter = new ListScoreAdapter(LiveScoree.this, liveScoreList);

                listView.setAdapter(listScoreAdapter);
                //LIVESCORE_KEY = "livescore";
            }
        }


    }


    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            if(CommonMethods.isNetworkAvailable(LiveScoree.this)) {
                refreshCounter=0;
                new FetchLiveScore().execute();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
            }

            LiveScoree.this.mHandler.postDelayed(m_Runnable, 1000*30);
        }

    };//runnable




    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(m_Runnable);
        // android.os.Process.killProcess(android.os.Process.myPid());
        Log.d("On Called", "Stop");
    }
    private class FetchLiveScore extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(refreshCounter==0) {
                // CommonMethods.showProgressDialog(LiveScoree.this);
                Toast.makeText(getApplicationContext(), "Auto Refresh", Toast.LENGTH_LONG).show();

            }
            else {
                CommonMethods.showProgressDialog(LiveScoree.this);
            }

        }
        @Override
        protected Void doInBackground(String... params) {

//            sh = new ServiceHandler();
//            // Making a request to url and getting response
//            jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
//            Log.d("Response: ", "> " + jsonStr);

            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost(url);
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
                        1);

                nameValuePairs.add(new BasicNameValuePair("match_key", match_key));


                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                httpclient.execute(httppost);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
            try {
                HttpResponse response = httpclient.execute(httppost);
                jsonStr = EntityUtils.toString(response.getEntity());
                Log.d("match response", jsonStr);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    contacts = jsonObj.getJSONArray("Toqeer");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String bothTeamsNames = c.getString(bothTeamsName);
                        String tossStatus = c.getString(toss);
                        String batTeamScores = c.getString(batTeamScore);
                        String current_innings=c.getString(match_current_innings);
                         bat1Name = c.getString("str_Name");
                        String bat1Runs = c.getString(batsmen1Runs);
                        String bat1Balls = c.getString(batsmen1Balls);

                        String bat2Name = c.getString(batsmen2Name);
                        String bat2Runs = c.getString(batsmen2Runs);
                        String bat2Balls = c.getString(batsmen2Balls);

                        String bow1Name = c.getString(bowler1Name);
                        String bow1Overs = c.getString(bowler1Overs);
                        String bow1Maid = c.getString(bowler1Maid);
                        String bow1Runs = c.getString(bowler1Runs);
                        String bow1Wickets = c.getString(bowler1Wickets);


                        String bowTeamName = c.getString(bowlingTeamName);
                        String batTeamName = c.getString(battingTeamName);
                        String team1key=c.getString(teamAkey);
                        String team2key=c.getString(teamBkey);
                            teamAname=c.getString("teamAName");
                        teamBname=c.getString("teamBName");
                        String dayy=c.getString(day);
                        linnk=c.getString(link);
                        info=c.getString("msg");
                        String key=c.getString("match_key");
                        String run=c.getString("run_st");
                         runstr=c.getString("run_str");
                        mom=c.getString("manofmatch");
                        breakreason=c.getString("statusover");
                        Log.d("Break",breakreason);
                        if(linnk.equalsIgnoreCase("link"))
                        {
                            comentry.setVisibility(View.GONE);
                        }
                        else {
                            comentry.setText(linnk);
                        }
                        liveScoreList.clear();
                        liveScoreList.add(new LiveScore(bothTeamsNames, tossStatus, batTeamScores,
                                bat1Name, bat1Runs, bat1Balls, bat2Name, bat2Runs, bat2Balls,
                                bow1Name, bow1Overs, bow1Maid, bow1Runs, bow1Wickets,
                                bowTeamName, batTeamName, team1key, team2key, current_innings, linnk,info, run,key,dayy,runstr,breakreason,teamAname,teamBname,mom));

                        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(LiveScoree.this);
                        SharedPreferences.Editor editor = sharedPrefs.edit();
                        Gson gson = new Gson();

                        String json = gson.toJson(liveScoreList);

                        editor.putString(LIVESCORE_KEY, json);

                        editor.commit();

                        listScoreAdapter = new ListScoreAdapter(LiveScoree.this, liveScoreList);

                        listView.setAdapter(listScoreAdapter);
if(bat1Name.equalsIgnoreCase("no"))
{
    String breakreas = null;
    if(breakreason.equalsIgnoreCase("NO")) {

    }
    else if(breakreason.equalsIgnoreCase("drinks_break")) {
        breakreas="Drink Break";
    }
    else if(breakreason.equalsIgnoreCase("innings_break")) {
        breakreas="Innings Break";
    }
    else if(breakreason.equalsIgnoreCase("lunch_break")) {
        breakreas="Lunch Break";
    }
    else if(breakreason.equalsIgnoreCase("in_play"))
    {
        breakreas="Play";
    }
    else if(breakreason.equalsIgnoreCase("rain_delay")) {
        breakreas= "Rain Delay";
    }
    else if(breakreason.equalsIgnoreCase("stumps")) {
        breakreas="Stumps";
    }
    else if(breakreason.equalsIgnoreCase("bad_light")) {
        breakreas="Bad Lights";
    }
    else if(breakreason.equalsIgnoreCase("crowd_trouble")) {
        breakreas="Crowd Trouble";
    }
    else if(breakreason.equalsIgnoreCase("bad_pitch_condition")) {
        breakreas="Bad Pitch";
    }
    else if(breakreason.equalsIgnoreCase("player_injured")) {
        breakreas="Player Injured";
    }
    else if(breakreason.equalsIgnoreCase("floodlight_failure")) {
        breakreas="Flood Light Failure";
    }
    else if(breakreason.equalsIgnoreCase("ball_change")) {
        breakreas="Ball Change";
    }
    else if(breakreason.equalsIgnoreCase("scheduled")) {
        breakreas= "Scheduled";
    }
    else if(breakreason.equalsIgnoreCase("start_delayed"))
    {
        breakreas="Match delayed by a wet outfield";
    }
    showAlertDialog(LiveScoree.this,
            "Match Delay",
            breakreas, false);
}
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                if(bat1Name.equalsIgnoreCase("no"))
                                {
                                    if(type.equalsIgnoreCase("Live")) {
                                        Intent i = new Intent(getApplicationContext(), LiveList.class);
                                        savePreferences("add",true);
                                        startActivity(i);
                                        finish();
                                    }
                                    else if(type.equalsIgnoreCase("wc"))
                                    {
                                        Intent i = new Intent(getApplicationContext(), WorldCup.class);

                                        startActivity(i);
                                        finish();
                                    }
                                    else
                                    {
                                        Intent i = new Intent(getApplicationContext(), Recent_Result.class);

                                        startActivity(i);
                                        finish();
                                    }
                                }
                                else {
                                    LiveScore item = liveScoreList.get(position);
                                    Intent i = new Intent(LiveScoree.this, FullCardTesting.class);
                                    i.putExtra("key", item.getKey());
                                    i.putExtra("innings",item.getInnings());
                                    i.putExtra("type",type);
                                    i.putExtra("day",item.getDayy());
                                    startActivity(i);

                                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                                    mHandler.removeCallbacks(m_Runnable);
                                        finish();
                               // } else {
                              }
                            }
                        });

                            CommonMethods.hideProgressDialog();

                    }
                    }catch(JSONException e){
                        e.printStackTrace();

                    Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();

                    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(LiveScoree.this);
                    Gson gson = new Gson();
                    String json1 = sharedPrefs.getString(LIVESCORE_KEY, null);
                    //String json2 = sharedPrefs.getString(LIST_2_KEY, null);

                    if(json1==null)
                    {
                        Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
                        mHandler.removeCallbacks(m_Runnable);
                        finish();

                        //onBackPressed();
                    }
                    else {
                        Type type = new TypeToken<ArrayList<LiveScore>>() {
                        }.getType();
                        liveScoreList = gson.fromJson(json1, type);
                        listScoreAdapter = new ListScoreAdapter(LiveScoree.this, liveScoreList);

                        listView.setAdapter(listScoreAdapter);
                        LIVESCORE_KEY = "livescore";
                    }
                    }


            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(m_Runnable);
        savePreferences("add",true);
        Intent i=new Intent(getApplicationContext(),LiveList.class);
        startActivity(i);
        finish();
    }
    public void savePreferences(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(LiveScoree.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
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
                    if(type.equalsIgnoreCase("Live")) {
                        Intent i = new Intent(getApplicationContext(), LiveList.class);
                        savePreferences("add",true);
                        startActivity(i);
                        finish();
                    }
                    else if(type.equalsIgnoreCase("wc"))
                    {
                        Intent i = new Intent(getApplicationContext(), WorldCup.class);

                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Intent i = new Intent(getApplicationContext(), Recent_Result.class);

                        startActivity(i);
                        finish();
                    }
                }
            });

        // Showing Alert Message
        alertDialog.show();
    }
}
