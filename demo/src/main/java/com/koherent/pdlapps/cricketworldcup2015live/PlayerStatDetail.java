package com.koherent.pdlapps.cricketworldcup2015live;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

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

import ImageDownloader.ImageLoader;
import utilities.CommonMethods;
import utilities.Constants;
import adapter.PlayerStatTabAdapter;
import adapter.ServiceHandler;

/**
 * Created by TOQEER on 8/17/2015.
 */
public class PlayerStatDetail extends FragmentActivity {
    private ImageLoader imageLoader;
    Button back;
    Intent i;
    ServiceHandler sh;
    String jsonStr;
    JSONArray jsonArray = null;

    public static String name;
//    public static String pl_role;
//    public static String bating_style;
//    public static String bowling_style;
    public static String pprofile;
    public static String TestBatMatch;
    public static String TestBatInnings;
    public static String TestBatNotOut;
    public static String TestBatRun;
    public static String TestBatAvg;
    public static String TestBatHighest;
    public static String TestBatFaced;
    public static String TestBatSr;
    public static String TestBatundered;
    public static String TestBatFifity;
    public static String TestBatFour;
    public static String TestBatSix;
    public static String OdiBatMatch;
    public static String OdiBatInnings;
    public static String OdiBatNotOut;
    public static String OdiBatRun;
    public static String OdiBatAvg;
    public static String OdiBatHighest;
    public static String OdiBatBallFaced;
    public static String OdiBatSr;
    public static String OdiBatundered;
    public static String OdiBatFifity;
    public static String OdiBatFour;
    public static String OdiBatSix;
    public static String t20BatMatch;
    public static String t20BatInnings;
    public static String t20BatNotOut;
    public static String t20BatRun;
    public static String t20BatAvg;
    public static String t20BatHighest;
    public static String t20BatBallFaced;
    public static String t20BatSr;
    public static String t20Batundered;
    public static String t20BatFifity;
    public static String t20BatFour;
    public static String t20BatSix;

    public static String TestBowlmatch;
    public static String TestBowlInnings;
    public static String TestBowlRuns;
    public static String TestBowls;
    public static String TestBowlWickts;
    public static String TestBBI;
    public static String TestBBM;
    public static String TestBowlAvg;
    public static String TestBowlEcn;
    public static String TestBowlSr;
    public static String TestBowl4w;
    public static String TestBowl5w;
    public static String TestBowl10w;

    public static String OdiBowlmatch;
    public static String OdiBowlInnings;
    public static String OdiBowls;

    public static String OdiBowlRuns;
    public static String OdiBowlWickts;
    public static String OdiBBI;
    public static String OdiBBM;
    public static String OdiBowlAvg;
    public static String OdiBowlEcn;
    public static String OdiBowlSr;
    public static String OdiBowl4w;
    public static String OdiBowl5w;
    public static String OdiBowl10w;

    public static String T20Bowlmatch;
    public static String T20BowlInnings;
    public static String T20BowlRuns;
    public static String T20BowlWickts;
    public static String T20Bowls;

    public static String T20BBI;
    public static String T20BBM;
    public static String T20BowlAvg;
    public static String T20BowlEcn;
    public static String T20BowlSr;
    public static String T20Bowl4w;
    public static String T20Bowl5w;
    public static String T20Bowl10w;

   public static String url= Constants.PlayerStatDetail_URL;
   public static String pl_key;
    ImageView cover;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_stat_layout);
        back=(Button)findViewById(R.id.back);

        title=(TextView)findViewById(R.id.title);

        i=getIntent();
        pl_key=i.getStringExtra("key");
        Log.d("Player Key", pl_key);
        String coverimage= i.getStringExtra("cover");
        pprofile=i.getStringExtra("pprofile");
        Log.d("Player profile", pprofile);

        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(new PlayerStatTabAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip)findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    i=new Intent(getApplicationContext(),Player_Stats.class);
                        startActivity(i);
                finish();
            }

            });




        if(CommonMethods.isNetworkAvailable(getApplicationContext())) {
         //   new GetPlayerState().execute();
        }
        else {
            Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();

        }
    }
    private class GetPlayerState extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressbar
            CommonMethods.showProgressDialog(getApplicationContext());

        }

        @Override
        protected Void doInBackground(String... Url) {
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost(url);
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
                        1);

                nameValuePairs.add(new BasicNameValuePair("PID", pl_key));


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
                Log.d("Player Name",jsonStr);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
            return null;

        }

        @Override
        protected void onPostExecute(Void args) {


            CommonMethods.hideProgressDialog();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    jsonArray = jsonObj.getJSONArray("Toqeer");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);

                        name= c.getString("Name");
                        title.setText(name);
//                        pl_role=c.getString("Playing Role");
//                        bating_style=c.getString("Bating Style");
//                        bowling_style=c.getString("Bowling Style");
                        TestBatMatch=c.getString("Tmatch");
                        TestBatInnings=c.getString("Tinnings");
                        TestBatRun=c.getString("TRun");
                        TestBatNotOut=c.getString("TNotOut");
                        TestBatHighest=c.getString("THighest");
                        TestBatAvg=c.getString("TAvg");
                        TestBatFaced=c.getString("TBallFaced");
                        TestBatSr=c.getString("TSr");
                        TestBatundered=c.getString("Thun");
                        TestBatFifity=c.getString("Tfifity");
                        TestBatFour=c.getString("Tfours");
                        TestBatSix=c.getString("TSixes");
                        OdiBatMatch=c.getString("Omatch");
                        OdiBatInnings=c.getString("Oinnings");
                        OdiBatRun=c.getString("ORun");
                        OdiBatNotOut=c.getString("ONotOut");
                        OdiBatHighest=c.getString("OHighest");
                        OdiBatAvg=c.getString("OAvg");
                        OdiBatBallFaced=c.getString("OBallFaced");
                        OdiBatSr=c.getString("OSr");
                        OdiBatundered=c.getString("Ohun");
                        OdiBatFifity=c.getString("Ofifity");
                        OdiBatFour=c.getString("Ofours");
                        OdiBatSix=c.getString("OSixes");

                        t20BatMatch=c.getString("T20match");
                        t20BatInnings=c.getString("T20innings");
                        t20BatRun=c.getString("T20Run");
                        t20BatNotOut=c.getString("T20NotOut");
                        t20BatHighest=c.getString("T20Highest");
                        t20BatAvg=c.getString("T20Avg");
                        t20BatBallFaced=c.getString("T20BallFaced");
                        t20BatSr=c.getString("T20Sr");
                        t20Batundered=c.getString("T20hun");
                        t20BatFifity=c.getString("T20fifity");
                        t20BatFour=c.getString("T20fours");
                        t20BatSix=c.getString("T20Sixes");


                        TestBowlmatch=c.getString("TBowlmatch");
                        TestBowlInnings=c.getString("TBowlInnings");
                        TestBowls=c.getString("TBowlBalls");
                        TestBowlRuns=c.getString("TBowlRuns");
                        TestBowlWickts=c.getString("TBowlWickt");
                        TestBBI=c.getString("TBBI");
                        TestBBM=c.getString("TBBM");
                        TestBowlAvg=c.getString("TbowlAvg");
                        TestBowlEcn=c.getString("TEcn");
                        TestBowlSr=c.getString("TBowlSr");
                        TestBowl10w=c.getString("T10w");
                        TestBowl5w=c.getString("T5w");
                        TestBowl4w=c.getString("T4w");

                        OdiBowlmatch=c.getString("OBowlmatch");
                        OdiBowlInnings=c.getString("OBowlInnings");
                        OdiBowls=c.getString("OBowlBalls");
                        OdiBowlRuns=c.getString("OBowlRuns");
                        OdiBowlWickts=c.getString("OBowlWickt");
                        OdiBBI=c.getString("OBBI");
                        OdiBBM=c.getString("OBBM");
                        OdiBowlAvg=c.getString("ObowlAvg");
                        OdiBowlEcn=c.getString("OEcn");
                        OdiBowlSr=c.getString("OBowlSr");
                        OdiBowl10w=c.getString("O10w");
                        OdiBowl5w=c.getString("O5w");
                        OdiBowl4w=c.getString("O4w");

                        T20Bowlmatch=c.getString("TwentyBowlmatch");
                        T20BowlInnings=c.getString("TwentyBowlInnings");
                        T20Bowls=c.getString("TwentyBowlBalls");
                        T20BowlRuns=c.getString("TwentyBowlRuns");
                        T20BowlWickts=c.getString("TwentyBowlWickt");
                        T20BBI=c.getString("TwentyBBI");
                        T20BBM=c.getString("TwentyBBM");
                        T20BowlAvg=c.getString("TwentybowlAvg");
                        T20BowlEcn=c.getString("TwentyEcn");
                        T20BowlSr=c.getString("TwentyBowlSr");

                        T20Bowl10w=c.getString("Twenty10w");
                        T20Bowl5w=c.getString("Twenty5w");
                        T20Bowl4w=c.getString("Twenty4w");
                    }

                }
                catch (Exception ex)
                {


                }


            }
            // Close progressbar
        }
    }
    }
