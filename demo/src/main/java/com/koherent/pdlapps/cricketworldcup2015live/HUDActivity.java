package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by Danii on 5/18/2015.
 */



public class HUDActivity extends Activity {



    private static String HUD_KEY = "hudkey";
    TextView battingTeamNameHUD;
    TextView battingTeamScoreHUD;
    TextView battingTeamOversHUD;
    TextView battingTeamRRHUD;

    TextView batsmenOneNameHUD;
    TextView batsmenOneRunsHUD;

    TextView batsmenTwoNameHUD;
    TextView batsmenTwoRunsHUD;
    TextView screenmsg;
    TextView screenmsg2;
    TextView bowlerOneNameHUD;
    TextView bowler1OverHudTv;
    TextView bowler1ECNHudTv;
    TextView bowler1RunsHudTv;
    TextView bowler1WicketsHudTv,man;
    int  refreshCounter=1;

    TextView bowlerTwoNameHUD;
    TextView bowler2OverHudTv;
    TextView bowler2ECNHudTv;
    TextView bowler2RunsHudTv;
    TextView bowler2WicketsHudTv;
    String jsonStr;
    String url= Constants.DemoAct;
    Handler mHandler;
    ProgressDialog pDialog;
    ServiceHandler sh;
    String match_current_innings = "match_current_innings";
    String toss = "Vs";
    String bothTeamsName = "toss";
    String battingTeamName = "current_bat_team";
    String batTeamScore = "current_score";
    String batsmen1Name = "str_Name";
    String batsmen1Runs = "str_Score";
    String batsmen1Balls = "str_ball";
    String batsmen2Name = "non_str_name";
    String batsmen2Runs = "non_str_score";
    String batsmen2Balls = "non_str_ball";
    String bowlingTeamName = "current_bowl_team";
    String bowler1Name = "bowler_name";
    String bowler1Overs = "bowler_overs";
    String runrate = "match_current_runrate";
    String bowler1Runs = "bowler_runs";
    String bowler1Wickets = "bowlers_wickets";
    String bowlEr="Eco";
    String teamBkey="teamBKey";
    String info="msg";

    String match_key,inn,type,day;
    LinearLayout layout;
Advertise advertise;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hud_main);
        Intent i=getIntent();
        match_key= i.getStringExtra("key");
        Log.i("HUD KEY", HUD_KEY);
        type = i.getStringExtra("type");
        inn=i.getStringExtra("innings");
        day=i.getStringExtra("day");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.mHandler = new Handler();

        this.mHandler.postDelayed(m_Runnable, 1000 * 30);
        advertise=new Advertise();
        layout= (LinearLayout)findViewById(R.id.add);
        advertise.Banner(layout, this);


        battingTeamNameHUD = (TextView) findViewById(R.id.battingTeamNameHUD);
        battingTeamScoreHUD = (TextView) findViewById(R.id.battingTeamScoreHUD);
        battingTeamOversHUD = (TextView) findViewById(R.id.battingTeamOversHUD);
        battingTeamRRHUD = (TextView) findViewById(R.id.battingTeamRRHUD);
        man=(TextView)findViewById(R.id.manof);
        batsmenOneNameHUD = (TextView) findViewById(R.id.batsmenOneNameHUD);
        batsmenOneRunsHUD = (TextView) findViewById(R.id.batsmenOneRunsHUD);

        batsmenTwoNameHUD = (TextView) findViewById(R.id.batsmenTwoNameHUD);
        batsmenTwoRunsHUD = (TextView) findViewById(R.id.batsmenTwoRunsHUD);

        bowlerOneNameHUD = (TextView) findViewById(R.id.bowlerOneNameHUD);
        bowler1OverHudTv = (TextView) findViewById(R.id.bowler1OverHudTv);
        bowler1RunsHudTv = (TextView) findViewById(R.id.bowler1RunsHudTv);
        bowler1WicketsHudTv = (TextView) findViewById(R.id.bowler1WicketsHudTv);
        bowler1ECNHudTv = (TextView) findViewById(R.id.bowler1ECNHudTv);
        screenmsg=(TextView)findViewById(R.id.screen);
        screenmsg2=(TextView)findViewById(R.id.bre);

        if(LiveScoree.breakreason.equalsIgnoreCase("NO")) {
            screenmsg2.setVisibility(View.GONE);
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("drinks_break")) {
            screenmsg2.setText("Status: " + "Drink Break");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("innings_break"))
        {
            screenmsg2.setText("Status: " + "Innings Break");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("lunch_break"))
        {
            screenmsg2.setText("Status: " + "Lunch Break");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("in_play"))
        {
            screenmsg2.setText("Status: " + "Play");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("rain_delay"))
        {
            screenmsg2.setText("Status: " + "Rain Delay");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("stumps"))
        {
            screenmsg2.setText("Status: " + "Stumps");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("bad_light"))
        {
            screenmsg2.setText("Status: " + "Bad Lights");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("crowd_trouble"))
        {
            screenmsg2.setText("Status: " + "Crowd Trouble");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("bad_pitch_condition"))
        {
            screenmsg2.setText("Status: " + "Bad Pitch");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("player_injured"))
        {
            screenmsg2.setText("Status: " + "Player Injured");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("floodlight_failure"))
        {
            screenmsg2.setText("Status: " + "Flood Light Failure");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("ball_change"))
        {
            screenmsg2.setText("Status: " + "Ball Change");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("scheduled")) {
            screenmsg2.setText("Status: " + "Scheduled");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("start_delayed"))
        {
            screenmsg2.setText("Status: " + "Match delayed by a wet outfield");
        }
        else
        {

            screenmsg2.setVisibility(View.GONE);
        }
        if(LiveScoree.mom.equalsIgnoreCase("No"))
        {
            man.setVisibility(View.GONE);
        }
        else
        {
            man.setText("Man of the Match: "+LiveScoree.mom);
        }
        LinearLayout ll;
        ll = (LinearLayout) findViewById(R.id.mainHUDWrapper);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeCallbacks(m_Runnable);
                Intent i=new Intent(getApplicationContext(),FullCardTesting.class);
                i.putExtra("key",match_key);
                i.putExtra("type",type);
                i.putExtra("day",day);
                i.putExtra("innings",inn);
                startActivity(i);
                finish();

            }
        });
        if(CommonMethods.isNetworkAvailable(HUDActivity.this)) {
            new FetchLiveScore().execute();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();


        }

    }
    private final Runnable m_Runnable = new Runnable() {
        public void run()

        {
            if (CommonMethods.isNetworkAvailable(HUDActivity.this)) {
                refreshCounter=0;
                new FetchLiveScore().execute();

            }
            else
            {

                Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
            }

            HUDActivity.this.mHandler.postDelayed(m_Runnable, 1000*30);
        }
    };
    private class FetchLiveScore extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(refreshCounter==0) {
                Toast.makeText(getApplicationContext(), "Auto Refresh", Toast.LENGTH_LONG).show();
            }
            else {
                CommonMethods.showProgressDialog(HUDActivity.this);

            }
        }

        @Override
        protected Void doInBackground(String... params) {

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
                    JSONObject jsonObj = null;

                    jsonObj = new JSONObject(jsonStr);


                    // String currRunRate = jsonObj.getString(CurrentRunRate);
                    JSONArray contacts = null;
                    contacts = jsonObj.getJSONArray("Toqeer");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String batTeamScores = c.getString(batTeamScore);
                        String bat1Name = c.getString(batsmen1Name);
                        String bat1Runs = c.getString(batsmen1Runs);
                        String bat1Balls = c.getString(batsmen1Balls);
                        String bat2Name = c.getString(batsmen2Name);
                        String bat2Runs = c.getString(batsmen2Runs);
                        String bat2Balls = c.getString(batsmen2Balls);
                        String bow1Name = c.getString(bowler1Name);
                        String bow1Overs = c.getString(bowler1Overs);
                        String bow1Runs = c.getString(bowler1Runs);
                        String bow1Wickets = c.getString(bowler1Wickets);
                        String bow1Er = c.getString(bowlEr);
                        String runrate2 = c.getString(runrate);
                        String infomsg = c.getString(info);
                        String batTeamName = c.getString(battingTeamName);
                        String batTeamScoreAndOvers = batTeamScores;
                        String[] parts = batTeamScoreAndOvers.split(" ");

                            String Overs = parts[1] + " " + parts[2];
                            String batTeamOvers = Overs;
                            String Runs = parts[0];
                            String batTeamScore = Runs;


                            String batsmen1_RUNS_BALLS = bat1Runs + " (" + bat1Balls + ")";
                            String batsmen2_RUNS_BALLS = bat2Runs + " (" + bat2Balls + ")";
                            battingTeamNameHUD.setText(batTeamName);
                            battingTeamScoreHUD.setText(batTeamScore);
                            battingTeamOversHUD.setText(batTeamOvers);
                            battingTeamRRHUD.setText(runrate2);
                            screenmsg.setText(infomsg);
                            batsmenOneNameHUD.setText(bat1Name);
                            batsmenOneRunsHUD.setText(batsmen1_RUNS_BALLS);
                            batsmenTwoNameHUD.setText(bat2Name);
                            batsmenTwoRunsHUD.setText(batsmen2_RUNS_BALLS);
                            bowlerOneNameHUD.setText(bow1Name);
                            bowler1OverHudTv.setText(bow1Overs);
                            bowler1RunsHudTv.setText(bow1Runs);
                            bowler1WicketsHudTv.setText(bow1Wickets);
                            bowler1ECNHudTv.setText(bow1Er);

                            CommonMethods.hideProgressDialog();

                        }



                }catch(JSONException e){
                    e.printStackTrace();

                    Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();

                }

            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(m_Runnable);
        Intent i=new Intent(getApplicationContext(),LiveList.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(m_Runnable);
        // android.os.Process.killProcess(android.os.Process.myPid());
        Log.d("On Called", "Hud Destory");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(m_Runnable);
        // android.os.Process.killProcess(android.os.Process.myPid());
        Log.d("On Called", "Hud Pause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(m_Runnable);
        Log.d("On Called", "Hud Stop");
    }
}
