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

import com.koherent.pdlapps.cricketworldcup2015live.LiveScoree;
import com.koherent.pdlapps.cricketworldcup2015live.OverByOver;
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
import adapter.OverAdapter;
import adapter.OverByOverAdapter;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 9/14/2015.
 */
public class OverTeam1 extends Fragment {
    public static ListView battingTeamOneListView;

    int refreshCounter =1;
    String jsonStr;
    String url ;
    TextView teamOneNameTV;
    TextView teamTwoNameTv;
    OverAdapter adapter;
    String run_str;
    ArrayList<fallwicketsitem> battingTeamOneList;
    ArrayList<fallwicketsitem> battingTeamTwoList;
    Handler mHandler;
    public static String name;
    //Advertise advertise;

    public OverTeam1() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        url= Constants.ScoreBall;
        battingTeamOneList = new ArrayList<fallwicketsitem>();
        battingTeamTwoList = new ArrayList<fallwicketsitem>();


        View view = inflater.inflate(R.layout.overteam1, container,
                false);
//        advertise=new Advertise();
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
                Log.d("match response", OverByOver.key+""+LiveScoree.teamAname+""+LiveScoree.teamBname);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("match_key", OverByOver.key));
                nameValuePairs.add(new BasicNameValuePair("team1", LiveScoree.teamAname));
                nameValuePairs.add(new BasicNameValuePair("team2", LiveScoree.teamBname));
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
                Log.d("match response", OverByOver.key+""+LiveScoree.teamAname+""+LiveScoree.teamBname);
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
                Log.d("Post ", "IN IF");
                try {
                    JSONObject jsonObj = null;
                    JSONArray batsman2 = null;
                    jsonObj = new JSONObject(jsonStr);
                    JSONArray team = null;
                    JSONArray team2=null;
                    JSONArray runstr=null;
                    JSONArray runstr2=null;

                    // String currRunRate = jsonObj.getString(CurrentRunRate);
                    JSONArray batsman1 = null;

                    runstr = jsonObj.getJSONArray("run_str1");
                    for (int i = 0; i < runstr.length(); i++) {

                        JSONObject cc = runstr.getJSONObject(i);
                        String score=cc.getString("run_str");
                        if(score.equalsIgnoreCase("0"))
                        {
                            OverByOver.team1.setVisibility(View.GONE);
                        }
                        else {
                            OverByOver.team1.setText(score);
                        }
                    }


                    team = jsonObj.getJSONArray("Team1");

                    for (int i = 0; i < team.length(); i++) {

                        JSONObject cc = team.getJSONObject(i);
                        String score = cc.getString("Ball1");
                        String score1 = cc.getString("Ball2");
                        String score2= cc.getString("Ball3");
                        String score3 = cc.getString("Ball4");
                        String score4 = cc.getString("Ball5");
                        String score5 = cc.getString("Ball6");
                        String overno=cc.getString("overno");
                        name = cc.getString("team_name");

                            if(name.equalsIgnoreCase("0")) {
                                OverByOverAdapter.tabTitles[1] = LiveScoree.teamAname;
                                OverByOver.tabs.setViewPager(OverByOver.pager);
                            }
                        else
                            {
                                OverByOverAdapter.tabTitles[1] = name;
                                OverByOver.tabs.setViewPager(OverByOver.pager);
                            }




                            battingTeamOneList.add(new fallwicketsitem(score,score1,score2,score3,score4,score5,overno));
                        }

                        adapter=new OverAdapter(getActivity(),battingTeamOneList);
                        battingTeamOneListView.setAdapter(adapter);
                    runstr2 = jsonObj.getJSONArray("run_str2");
             Log.d("Length",""+runstr2.length());
                        for (int i = 0; i < runstr2.length(); i++) {

                            JSONObject cc = runstr2.getJSONObject(i);

                            String score = cc.getString("run_str");
                            if(score.equalsIgnoreCase("0"))
                            {
                                OverByOver.team2.setVisibility(View.GONE);
                            }
                            else {
                                OverByOver.team2.setText(score);
                            }

                    }
                    team2 = jsonObj.getJSONArray("Team2");
                    
                    for (int i = 0; i < team2.length(); i++) {

                        JSONObject cc = team2.getJSONObject(i);
                        String score = cc.getString("Ball1");
                        String score1 = cc.getString("Ball2");
                        String score2= cc.getString("Ball3");
                        String score3 = cc.getString("Ball4");
                        String score4 = cc.getString("Ball5");
                        String score5 = cc.getString("Ball6");
                        String overno = cc.getString("overno");
                        name = cc.getString("team_name");

                        if(name.equalsIgnoreCase("0")) {
                            OverByOverAdapter.tabTitles[0] = LiveScoree.teamBname;
                            OverByOver.tabs.setViewPager(OverByOver.pager);
                        }
                        else
                        {
                            OverByOverAdapter.tabTitles[0] = name;
                            OverByOver.tabs.setViewPager(OverByOver.pager);
                        }

                        battingTeamTwoList.add(new fallwicketsitem(score,score1,score2,score3,score4,score5,overno));
                    }

                    adapter=new OverAdapter(getActivity(),battingTeamTwoList);
                    OverTeam2.battingTeamTwoListView.setAdapter(adapter);
                    if (name.equalsIgnoreCase("0")){

//                            FullCardPagerAdapter.tabTitles[0] = battingTeamTwoList.get(0).getName();
//                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
//                        OverByOverAdapter.tabTitles[0] = LiveScoree.teamAname;
//                        OverByOver.tabs.setViewPager(OverByOver.pager);
//                        OverByOverAdapter.tabTitles[0] = name;
//                        OverByOver.tabs.setViewPager(OverByOver.pager);
//                        OverByOverAdapter.tabTitles[1] = LiveScoree.teamAname;
//                        OverByOver.tabs.setViewPager(OverByOver.pager);
//                        OverByOverAdapter.tabTitles[1] = name;
//                        OverByOver.tabs.setViewPager(OverByOver.pager);
                        adapter=new OverAdapter(getActivity(),battingTeamTwoList);
                        battingTeamOneListView.setAdapter(adapter);


                        adapter=new OverAdapter(getActivity(),battingTeamOneList);
                        OverTeam2.battingTeamTwoListView.setAdapter(adapter);

                    }else{
//                             FullCardPagerAdapter.tabTitles[0] = battingTeamTwoList.get(0).getName();
//                             FullCardTesting.tabs.setViewPager(FullCardTesting.pager);

//                        OverByOverAdapter.tabTitles[1] = LiveScoree.teamAname;
//                        OverByOver.tabs.setViewPager(OverByOver.pager);
//                        OverByOverAdapter.tabTitles[1] = name;
//                        OverByOver.tabs.setViewPager(OverByOver.pager);
//                        OverByOverAdapter.tabTitles[0] = LiveScoree.teamAname;
//                        OverByOver.tabs.setViewPager(OverByOver.pager);
//                        OverByOverAdapter.tabTitles[0] = name;
//                        OverByOver.tabs.setViewPager(OverByOver.pager);
                        adapter=new OverAdapter(getActivity(),battingTeamTwoList);
                        battingTeamOneListView.setAdapter(adapter);


                        adapter=new OverAdapter(getActivity(),battingTeamOneList);
                        OverTeam2.battingTeamTwoListView.setAdapter(adapter);
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
