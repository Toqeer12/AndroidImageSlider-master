package PageFragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.koherent.pdlapps.cricketworldcup2015live.FallOFWickets;
import com.koherent.pdlapps.cricketworldcup2015live.R;

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

import Model.fallwicketsitem;
import adapter.FallOFWicketAdapter;
import adapter.PagerAdapterFallofWicketsTest;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 9/9/2015.
 */
public class FallOFWickets_Team1 extends Fragment {
    public static ListView battingTeamOneListView;

    int refreshCounter =1;
    String jsonStr;
    String url ;
    TextView teamOneNameTV;
    TextView teamTwoNameTv;

    String run_str;
    ArrayList<fallwicketsitem> battingTeamOneList;
    ArrayList<fallwicketsitem> battingTeamTwoList;
    Handler mHandler;
    public static String name,name2;
//    Advertise advertise;
    public static FallOFWicketAdapter adapterBattingTeamOne;
    public static FallOFWicketAdapter adapterBattingTeamTwo;
    public FallOFWickets_Team1() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        url= Constants.Full_card;
        battingTeamOneList = new ArrayList<fallwicketsitem>();
        battingTeamTwoList = new ArrayList<fallwicketsitem>();


        View view = inflater.inflate(R.layout.team1_list, container,
                false);
      //  advertise=new Advertise();
//        advertise.InterstitialAd(getActivity());
        this.mHandler = new Handler();

        this.mHandler.postDelayed(m_Runnable,1000*30);
        battingTeamOneListView=(ListView)view.findViewById(R.id.teamOneListView2);
        if(CommonMethods.isNetworkAvailable(getActivity())) {

            new GetScoreAutoCardDetails().execute();
        }
        else
        {
            Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();
        }


        return view;

    }


    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            if(CommonMethods.isNetworkAvailable(getActivity())) {
                refreshCounter=0;
                new GetScoreAutoCardDetails().execute();
            }
            else
            {
                Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();
            }

            mHandler.postDelayed(m_Runnable, 1000*30);
        }

    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(m_Runnable);
        // android.os.Process.killProcess(android.os.Process.myPid());
        Log.d("On Called", "full Destory");
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(m_Runnable);
        // android.os.Process.killProcess(android.os.Process.myPid());
        Log.d("On Called", "full Pause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("On Called", "full resmue");
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
            battingTeamOneList.clear();
            battingTeamTwoList.clear();
            if(refreshCounter==0) {
                // CommonMethods.showProgressDialog(LiveScoree.this);
                Toast.makeText(getActivity(), "Auto Refresh", Toast.LENGTH_LONG).show();

            }
            else {
                CommonMethods.showProgressDialog(getActivity());
            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("match_key", FallOFWickets.key));
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
            String idd=null;
            if (!jsonStr.equalsIgnoreCase("null")) {
                Log.d("Post ", "IN IF");
                try {
                    JSONObject jsonObj = null;

                    jsonObj = new JSONObject(jsonStr);
                    // String currRunRate = jsonObj.getString(CurrentRunRate);
                    JSONArray batsman1 = null;
                    JSONArray bowler = null;
                    JSONArray batsman2 = null;
                    JSONArray bowler2 = null;
                    JSONArray team1=null;
                    JSONArray team2=null;
                    batsman1 = jsonObj.getJSONArray("Batsman");


                    for (int i = 0; i < batsman1.length(); i++) {

                        JSONObject c = batsman1.getJSONObject(i);
                        idd = c.getString("Id");
                        if (idd.equalsIgnoreCase("0")) {
                            Log.d("String Response", "acb");

                        } else {

                            run_str = c.getString("run_str");
                            FallOFWickets.team2.setText(run_str);
                            name = c.getString("teamname");
//                            PagerAdapterFallofWickets.tabTitles[0] = name;
//                           FallOFWickets.tabs.setViewPager(FallOFWickets.pager);

                        }
                    }

                    bowler = jsonObj.getJSONArray("Bowler1");
                    for (int i = 0; i < bowler.length(); i++) {

                        JSONObject cc = bowler.getJSONObject(i);
                        String id = cc.getString("Id");
                        if (id.equalsIgnoreCase("0")) {
                            Log.d("String Response", "acb");

                        } else {

                            run_str = cc.getString("run_str");
                             name2 = cc.getString("teamname");
//                            PagerAdapterFallofWickets.tabTitles[1] = name;
//                           FallOFWickets.tabs.setViewPager(FallOFWickets.pager);

                        }

                    }



                    battingTeamOneList.add(new fallwicketsitem("Fall of Wickets"," ", "  "
                           ));


                    team1 = jsonObj.getJSONArray("team1_wicket");
                    int i;
                    for ( i = 0; i < team1.length(); i++) {
                        JSONObject dd = team1.getJSONObject(i);
                        idd= dd.getString("Id");
                        if (idd.equalsIgnoreCase("0")) {
                            Log.d("String Response", "acb");

                        } else {
                            String wickets = dd.getString("Fall_of_wicket");
                            String [] splitted = wickets.split("at ");
                            Log.d("Splitted",splitted[0]);
                            String name=splitted[0];
                            Log.d("Splitted", splitted[1]);
                            String runsString=splitted[1];
                            String [] spl=runsString.split("runs, in");
                            String runs=spl[0];
                            Log.d("Splitted", runs);
                            String overStr=spl[1];
                            Log.d("Splitted2", overStr);
                            battingTeamOneList.add(new fallwicketsitem(i,runs, name, overStr
                                    ));
                        }

                    }
//                    adapterBattingTeamOne = new FallOFWicketAdapter(getActivity(), battingTeamOneList);
//                   battingTeamOneListView.setAdapter(adapterBattingTeamOne);

                    batsman2 = jsonObj.getJSONArray("Batsman2");
                    for ( i = 0; i < batsman2.length(); i++) {
                        JSONObject c = batsman2.getJSONObject(i);
                        String id = c.getString("Id");
                        if (id.equalsIgnoreCase("0")) {
                            Log.d("String Response", "acb");

                        } else {

                            run_str = c.getString("run_str");
                            FallOFWickets.team1.setText(run_str);
                             name = c.getString("teamname");
//                            PagerAdapterFallofWickets.tabTitles[0] = name;
//                           FallOFWickets.tabs.setViewPager(FallOFWickets.pager);


                        }

                    }

                    bowler2 = jsonObj.getJSONArray("Bowler2");
                    for ( i = 0; i < bowler2.length(); i++) {
                        JSONObject cc = bowler2.getJSONObject(i);
                        String id = cc.getString("Id");
                        if (id.equalsIgnoreCase("0")) {
                            Log.d("String Response", "acb");

                        } else {

                            run_str = cc.getString("run_str");
                             name2 = cc.getString("teamname");
//                            PagerAdapterFallofWickets.tabTitles[1] = name;
//                           FallOFWickets.tabs.setViewPager(FallOFWickets.pager);

                        }
                    }

                    battingTeamTwoList.add(new fallwicketsitem("Fall of Wickets"," ",""
                            ));

                    team2 = jsonObj.getJSONArray("team2_wicket");

                    for ( i = 0; i < team2.length(); i++) {
                        JSONObject cccc = team2.getJSONObject(i);
                        idd = cccc.getString("Id");
                        if (idd.equalsIgnoreCase("0")) {
                            Log.d("String Response", "acb");

                        } else {
                            String wickets = cccc.getString("Fall_of_wicket");
                            Log.d("Splitted",wickets);
                            String [] splitted = wickets.split(" at ");
                            Log.d("Splitted",splitted[0]);
                            String name=splitted[0];
                            Log.d("Splitted", splitted[1]);
                            String runsString=splitted[1];
                            String [] spl=runsString.split("runs, in");
                            String runs=spl[0];
                            Log.d("Splitted", runs);
                            String overStr=spl[1];
                            Log.d("Splitted", overStr);
                            battingTeamTwoList.add(new fallwicketsitem(i,runs, name, overStr
                                   ));

                        }
                    }




                    if (idd.equalsIgnoreCase("0")){

//
                        PagerAdapterFallofWicketsTest.tabTitles[1] = name;
                        FallOFWickets.tabs.setViewPager(FallOFWickets.pager);
//
                        PagerAdapterFallofWicketsTest.tabTitles[0] = name2;
                        FallOFWickets.tabs.setViewPager(FallOFWickets.pager);
                        adapterBattingTeamOne = new FallOFWicketAdapter(getActivity(), battingTeamTwoList);
                        battingTeamOneListView.setAdapter(adapterBattingTeamOne);

                        adapterBattingTeamTwo=new FallOFWicketAdapter(getActivity(),battingTeamOneList);
                 FallOFWickets_Team2.battingTeamTwoListView.setAdapter(adapterBattingTeamTwo);

                    }else{
                        PagerAdapterFallofWicketsTest.tabTitles[1] = name2;
                        FallOFWickets.tabs.setViewPager(FallOFWickets.pager);
//
                        PagerAdapterFallofWicketsTest.tabTitles[0] = name;
                        FallOFWickets.tabs.setViewPager(FallOFWickets.pager);
                        adapterBattingTeamOne = new FallOFWicketAdapter(getActivity(), battingTeamTwoList);
                        battingTeamOneListView.setAdapter(adapterBattingTeamOne);

                        adapterBattingTeamTwo=new FallOFWicketAdapter(getActivity(),battingTeamOneList);
                  FallOFWickets_Team2.battingTeamTwoListView.setAdapter(adapterBattingTeamTwo);
                    }

                    CommonMethods.hideProgressDialog();

                }



                catch (Exception e) {
                    e.printStackTrace();


                }


            }

            else
            {
//                layoutTeamTwo.setVisibility(View.GONE);
//                layoutTeamOne.setVisibility(View.GONE);
                //  tabTeamTwo.setText("yet to Bat");
                battingTeamTwoList.clear();
                Toast.makeText(getActivity(),"Yet to Play",Toast.LENGTH_LONG).show();
                CommonMethods.hideProgressDialog();
//                mHandler.removeCallbacks(m_Runnable);
            }


        }
    }

}
