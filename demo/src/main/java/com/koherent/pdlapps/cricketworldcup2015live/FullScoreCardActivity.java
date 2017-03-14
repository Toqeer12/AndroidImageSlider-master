package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.simonvt.menudrawer.MenuDrawer;

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
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.InningsOneBatting;
import Model.LiveScore;
import Model.MatchDetails;
import utilities.CommonMethods;
import utilities.Constants;
import adapter.CustomBattingAdapter;
import adapter.ServiceHandler;


public class FullScoreCardActivity extends Activity {
    private static String FULL_SCORECARD_KEY = "Card";
    private static String FULL_SCORECARD_KEY_LIST_1 = "listone";
    private static String FULL_SCORECARD_KEY_LIST_2 = "listtwo";
    private MenuDrawer mDrawer;
    String run_str;

    Button btn,refresh;
    Button fix,news,rank,gallery,venue,live,share,home,player,record,moreapps;
    ListView battingTeamOneListView;
    ListView battingTeamTwoListView;
    //JSONArray arr = null;
    Button back,hud;
    String jsonStr;
    String url ;
    ProgressDialog pDialog;
    //JSONArray contacts = null;


    LinearLayout layout;
    //String id1 = "INNINGS_ONE_FALL_OF_WICKETS";

    ArrayList<InningsOneBatting> battingTeamOneList;
    ArrayList<InningsOneBatting> battingTeamTwoList;

    CustomBattingAdapter adapterBattingTeamOne;
    CustomBattingAdapter adapterBattingTeamTwo;

    LinearLayout layoutTeamOne;
    LinearLayout layoutTeamTwo;
    Button tabTeamOne;
    Button tabTeamTwo;
    int tabOneCounter = 1;
    int tabTwoCounter = 0;

    int refreshCounter =1;
    MatchDetails mMatchDetails;
    TextView teamOneNameTV;
    TextView teamTwoNameTv;
    TextView mainOverTv;
    TextView mainRequiredRunRateTv;
    TextView matchStatusTV;
    Bundle b;
    TextView inningsOneTeamScoreTV;
    TextView inningsTwoTeamScoreTV;
    LiveScore liveScore;
    Thread myThread;
    boolean running;
    int cnt;
    Handler  mHandler;
    Thread thread;
    String jsonString;
    String bat="a";
    String bowl="b";
    String nb,bb,wb,lb,total;
    String info="null";
    ServiceHandler sh;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.activity_full_score_card);
        mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        layout= (LinearLayout)findViewById(R.id.add);
        this.mHandler = new Handler();

        this.mHandler.postDelayed(m_Runnable,1000*30);
//		advertise = new Advertise();
//        advertise.Banner(layout, this);
        back=(Button)findViewById(R.id.back);
        hud=(Button)findViewById(R.id.hud);
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
        refresh=(Button)findViewById(R.id.refresh);
        btn=(Button)findViewById(R.id.title_bar_left_menu);
        player=(Button)findViewById(R.id.pprofile);

            Intent i=getIntent();
        key=   i.getStringExtra("key");

        FULL_SCORECARD_KEY = "Card";
        FULL_SCORECARD_KEY_LIST_1 = "listone";
        FULL_SCORECARD_KEY_LIST_2 = "listtwo";

        FULL_SCORECARD_KEY = FULL_SCORECARD_KEY+key;

        FULL_SCORECARD_KEY_LIST_1 = FULL_SCORECARD_KEY_LIST_1 + FULL_SCORECARD_KEY;
        FULL_SCORECARD_KEY_LIST_2 = FULL_SCORECARD_KEY_LIST_2 + FULL_SCORECARD_KEY;



        url= Constants.Full_card;
        battingTeamOneList = new ArrayList<InningsOneBatting>();
        battingTeamTwoList = new ArrayList<InningsOneBatting>();
        battingTeamOneListView = (ListView) findViewById(R.id.teamOneListView);
        battingTeamTwoListView = (ListView) findViewById(R.id.teamTwoListView);


        layoutTeamOne = (LinearLayout) findViewById(R.id.layout_TeamOne);
        layoutTeamTwo = (LinearLayout) findViewById(R.id.layout_TeamTwo);


        tabTeamOne = (Button) findViewById(R.id.button_TeamOne);
        tabTeamOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tabOneCounter==0) {
                    tabTeamOne.setBackgroundColor(Color.parseColor("#000031"));
                    tabTeamTwo.setBackgroundColor(Color.TRANSPARENT);
                    layoutTeamTwo.setVisibility(View.GONE);
                    layoutTeamOne.setVisibility(View.VISIBLE);

                    tabOneCounter=1;
                    tabTwoCounter=0;
                }
            }
        });

        tabTeamTwo = (Button) findViewById(R.id.button_TeamTwo);
        tabTeamTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tabTwoCounter==0) {
                    tabTeamOne.setBackgroundColor(Color.TRANSPARENT);
                    tabTeamTwo.setBackgroundColor(Color.parseColor("#000031"));
                    layoutTeamOne.setVisibility(View.GONE);
                    layoutTeamTwo.setVisibility(View.VISIBLE);

                    tabTwoCounter=1;
                    tabOneCounter=0;
                }
            }
        });

        teamOneNameTV = (TextView) findViewById(R.id.team1NameScore);
        teamTwoNameTv = (TextView) findViewById(R.id.teamTwoNameScore);

        matchStatusTV  = (TextView) findViewById(R.id.matchStatus);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mHandler.removeCallbacks(m_Runnable);
                Intent i=new Intent(getApplicationContext(),LiveList.class);

                startActivity(i);

                finish();
            }
        });

        hud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Bundle b = new Bundle();
                Intent i = new Intent(getApplicationContext(), HUDActivity.class);

                i.putExtra("key",key);

                mHandler.removeCallbacks(m_Runnable);


                startActivity(i);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                    finish();

            }
        });

        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                  home.setBackground(getDrawable(R.drawable.home_pressed));
                Intent i=new Intent(getApplicationContext(),History.class);
                mHandler.removeCallbacks(m_Runnable);
                startActivity(i);
                finish();

            }
        });
        moreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MoreApps.class);
                mHandler.removeCallbacks(m_Runnable);

                startActivity(i);

                finish();
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                  home.setBackground(getDrawable(R.drawable.home_pressed));
                Intent i=new Intent(getApplicationContext(),Player_Stats.class);
                mHandler.removeCallbacks(m_Runnable);
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
                mHandler.removeCallbacks(m_Runnable);
                startActivity(i);
                finish();

            }
        });
        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Fixture.class);
                mHandler.removeCallbacks(m_Runnable);
                startActivity(i);
                finish();

            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareBody = "https://play.google.com/store/apps/details?id=com.koherent.pdlapps.cricketworldcup2015";                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "CricExtra App");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                mHandler.removeCallbacks(m_Runnable);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.CricDroid)));
            }
        });
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LiveList.class);
                mHandler.removeCallbacks(m_Runnable);
                startActivity(i);
                finish();

            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),News.class);
                mHandler.removeCallbacks(m_Runnable);
                startActivity(i);
                finish();

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),PictureGallery.class);
                mHandler.removeCallbacks(m_Runnable);
                startActivity(i);
                finish();

            }
        });
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Ranking.class);
                mHandler.removeCallbacks(m_Runnable);
                startActivity(i);
                finish();

            }
        });
        venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Venues.class);
                mHandler.removeCallbacks(m_Runnable);
                startActivity(i);
                finish();

            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CommonMethods.isNetworkAvailable(FullScoreCardActivity.this)) {
                    refreshCounter=1;
                  new GetScoreAutoCardDetails().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
                }
            }
        });

        if(CommonMethods.isNetworkAvailable(FullScoreCardActivity.this)) {

               new GetScoreAutoCardDetails().execute();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();

        }
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            if(CommonMethods.isNetworkAvailable(FullScoreCardActivity.this)) {
                refreshCounter=0;
                new GetScoreAutoCardDetails().execute();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();
            }

            FullScoreCardActivity.this.mHandler.postDelayed(m_Runnable, 1000*30);
        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(m_Runnable);
        // android.os.Process.killProcess(android.os.Process.myPid());
        Log.d("On Called", "full Destory");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(m_Runnable);
        // android.os.Process.killProcess(android.os.Process.myPid());
        Log.d("On Called", "full Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("On Called", "full resmue");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("On Called", "full restart");
        if(CommonMethods.isNetworkAvailable(FullScoreCardActivity.this)) {

            new GetScoreAutoCardDetails().execute();
        }
        else
        {

        }
    }

    @Override
    public void onStop() {
        super.onStop();
       mHandler.removeCallbacks(m_Runnable);
       // android.os.Process.killProcess(android.os.Process.myPid());
        Log.d("On Called", "full Stop");
    }

    public class GetScoreAutoCardDetails extends AsyncTask<Void,Void,Void> {

        private String bat,bowl;
        Handler  mHandler;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(refreshCounter==0) {
                // CommonMethods.showProgressDialog(LiveScoree.this);
               Toast.makeText(getApplicationContext(), "Auto Refresh", Toast.LENGTH_LONG).show();

            }
            else {
                CommonMethods.showProgressDialog(FullScoreCardActivity.this);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("match_key", key));
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
            Log.d("Response: ", "> " + jsonStr);
//
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            battingTeamOneList.clear();
            battingTeamTwoList.clear();
            if (!jsonStr.equalsIgnoreCase("null")) {
                Log.d("Post ","IN IF");
                try {
                    JSONObject jsonObj = null;

                    jsonObj = new JSONObject(jsonStr);
                    // String currRunRate = jsonObj.getString(CurrentRunRate);
                    JSONArray batsman1 = null;
                    JSONArray bowler = null;
                    JSONArray batsman2 = null;
                    JSONArray bowler2 = null;

                    battingTeamOneList.add(new InningsOneBatting(run_str, "", "","",""
                            ,"","",""));
                    batsman1 = jsonObj.getJSONArray("Batsman");


                        for (int i = 0; i < batsman1.length(); i++) {

                            JSONObject c = batsman1.getJSONObject(i);

                            String id = c.getString("Id");
                            if (id.equalsIgnoreCase("0")) {
                                Log.d("String Response", "acb");

                            } else {
                                String batsmanname = c.getString("Batsman_Name");
                                String batrun = c.getString("Runs");
                                String batsix = c.getString("Sixs");
                                String batfour = c.getString("Fours");
                                String balls = c.getString("Balls");
                                String sr = c.getString("Strike_Rate");
                                String extra = c.getString("extra");
                                String batmanstatus = c.getString("dismissed");
                                run_str = c.getString("run_str");
                                teamTwoNameTv.setText(run_str);

                                String name = c.getString("teamname");

                                //  String dismissed=c.getString("dismissed");
                                Log.d("String Response", extra);
                                String a[] = extra.split(" ");
                                nb = a[0];
                                wb = a[1];
                                bb = a[2];
                                lb = a[3];
                                total = a[4];
                                Log.d("String Response", total);
                                Log.d("Daani", balls);

                                tabTeamOne.setText(name);
                                battingTeamOneList.add(new InningsOneBatting(batsmanname, batrun, balls, batfour, batsix
                                        , sr, batmanstatus,""));

                            }
                        }
                    battingTeamOneList.add(new InningsOneBatting("Extra", nb, wb,bb,lb
                            ,total,"",""));
                    battingTeamOneList.add(new InningsOneBatting("Bowler","", "R","O","W"
                            ,"E","",""));
                        bowler = jsonObj.getJSONArray("Bowler1");
                        for (int i = 0; i < bowler.length(); i++) {

                            JSONObject cc = bowler.getJSONObject(i);
                            String id = cc.getString("Id");
                            if (id.equalsIgnoreCase("0")) {
                                Log.d("String Response", "acb");

                            } else {
                                String bowl = cc.getString("Bowlsman_Name");
                                String bowlrun = cc.getString("Runs");
                                String overs = cc.getString("Overs");
                                String wicket = cc.getString("Wickets");
                                String economy = cc.getString("Ecn");
                                run_str = cc.getString("run_str");
                                String name = cc.getString("teamname");
                                tabTeamTwo.setText(name);
                                battingTeamOneList.add(new InningsOneBatting(bowl, "", bowlrun, overs, wicket
                                        , economy, "",""));

                            }
                        }
                        adapterBattingTeamOne = new CustomBattingAdapter(FullScoreCardActivity.this, battingTeamOneList);
                        battingTeamOneListView.setAdapter(adapterBattingTeamOne);
                         battingTeamTwoList.add(new InningsOneBatting(run_str, "", "", "", ""
                            , "", "",""));
                        batsman2 = jsonObj.getJSONArray("Batsman2");
                        for (int i = 0; i < batsman2.length(); i++) {
                            JSONObject c = batsman2.getJSONObject(i);
                            String id = c.getString("Id");
                            if (id.equalsIgnoreCase("0")) {
                                Log.d("String Response", "acb");

                            } else {
                                String batsmanname = c.getString("Batsman_Name");
                                String batrun = c.getString("Runs");
                                String batsix = c.getString("Sixs");
                                String batfour = c.getString("Fours");
                                String balls = c.getString("Balls");
                                String sr = c.getString("Strike_Rate");
                                String extra = c.getString("extra");
                                String batmanstatus = c.getString("dismissed");
                                run_str = c.getString("run_str");
                                teamOneNameTV.setText(run_str);
                                String name = c.getString("teamname");
                                Log.d("String Response", extra);
                                String a[] = extra.split(" ");
                                nb = a[0];
                                wb = a[1];
                                bb = a[2];
                                lb = a[3];
                                total = a[4];
                                Log.d("String Response", total);
                                    tabTeamTwo.setText(name);
                                battingTeamTwoList.add(new InningsOneBatting(batsmanname, batrun, balls, batfour, batsix
                                        , sr, batmanstatus,""));

                            }
                        }

                    battingTeamTwoList.add(new InningsOneBatting("Extra", nb, wb,bb,lb
                            ,total,"",""));
                    battingTeamTwoList.add(new InningsOneBatting("Bowler","", "R","O","W"
                            ,"E","",""));


                        bowler2 = jsonObj.getJSONArray("Bowler2");
                        for (int i = 0; i < bowler2.length(); i++) {
                            JSONObject cc = bowler2.getJSONObject(i);
                            String id = cc.getString("Id");
                            if (id.equalsIgnoreCase("0")) {
                                Log.d("String Response", "acb");

                            } else {
                                String bowl = cc.getString("Bowlsman_Name");
                                String bowlrun = cc.getString("Runs");
                                String overs = cc.getString("Overs");
                                String wicket = cc.getString("Wickets");
                                String economy = cc.getString("Ecn");
                                run_str = cc.getString("run_str");

                                String name = cc.getString("teamname");


                                battingTeamTwoList.add(new InningsOneBatting(bowl, "", bowlrun, overs, wicket
                                        , economy, "",""));

                                    tabTeamOne.setText(name);


                            }

                        }

                        adapterBattingTeamTwo=new CustomBattingAdapter(FullScoreCardActivity.this,battingTeamTwoList);
                        battingTeamTwoListView.setAdapter(adapterBattingTeamTwo);

                        CommonMethods.hideProgressDialog();

            }



                catch (Exception e) {
                    e.printStackTrace();


                }


            }

            else
            {
                layoutTeamTwo.setVisibility(View.GONE);
                layoutTeamOne.setVisibility(View.GONE);
                //  tabTeamTwo.setText("yet to Bat");
                battingTeamTwoList.clear();
                Toast.makeText(FullScoreCardActivity.this,"Yet to Play",Toast.LENGTH_LONG).show();
                CommonMethods.hideProgressDialog();
//                mHandler.removeCallbacks(m_Runnable);
            }


        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(null);
        Intent i=new Intent(getApplicationContext(),LiveList.class);
        startActivity(i);
        finish();
        Log.d("On Called", "back");
    }

}