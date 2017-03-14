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
 * Created by TOQEER on 9/16/2015.
 */
public class FallOFWicketTest2_Team1 extends Fragment {
    public static ListView battingTeamthreeListView;

    int refreshCounter =1;
    String jsonStr;
    String url ;
    TextView teamOneNameTV;
    TextView teamTwoNameTv;

    String run_str;
    ArrayList<fallwicketsitem> battingTeamthreList;
    ArrayList<fallwicketsitem> battingTeamforList;
    Handler mHandler;
    public static String name;
    //Advertise advertise;
    public static FallOFWicketAdapter adapterBattingTeamthre;
    public static FallOFWicketAdapter adapterBattingTeamfor;
    public FallOFWicketTest2_Team1() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        url= Constants.TestFullCard;
        battingTeamthreList = new ArrayList<fallwicketsitem>();
        battingTeamforList = new ArrayList<fallwicketsitem>();


        View view = inflater.inflate(R.layout.fallwicket3, container,
                false);
//        advertise=new Advertise();
//        advertise.InterstitialAd(getActivity());
        this.mHandler = new Handler();

        this.mHandler.postDelayed(m_Runnable,1000*30);
        battingTeamthreeListView=(ListView)view.findViewById(R.id.teamthreListView2);
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

        private String bat, bowl;
        Handler mHandler;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            battingTeamthreList.clear();
            battingTeamforList.clear();
            if (refreshCounter == 0) {
                // CommonMethods.showProgressDialog(LiveScoree.this);
                Toast.makeText(getActivity(), "Auto Refresh", Toast.LENGTH_LONG).show();

            } else {
              //  CommonMethods.showProgressDialog(getActivity());
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
            battingTeamthreList.clear();
            battingTeamforList.clear();
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
                    JSONArray team1 = null;
                    JSONArray team2 = null;
                    JSONArray team3 = null;

                    battingTeamthreList.add(new fallwicketsitem("Fall of Wickets"," ", "  "
                    ));
                    int i;
                    batsman2 = jsonObj.getJSONArray("Batsman3");
                    for ( i = 0; i < batsman2.length(); i++) {
                        JSONObject c = batsman2.getJSONObject(i);
                        String id = c.getString("Id");
                        if (id.equalsIgnoreCase("0")) {
                            Log.d("String Response", "acb");

                        } else {

                            run_str = c.getString("run_str");
                            FallOFWickets.team1.setText(run_str);
                            String name = c.getString("teamname");
                            PagerAdapterFallofWicketsTest.tabTitles[2] = name;
                            FallOFWickets.tabs.setViewPager(FallOFWickets.pager);


                        }

                    }



                    team1 = jsonObj.getJSONArray("team1_wicket_2");

                    for (i = 0; i < team1.length(); i++) {
                        JSONObject dd = team1.getJSONObject(i);
                        String id = dd.getString("Id");
                        if (id.equalsIgnoreCase("0")) {
                            Log.d("String Response", "acb");

                        } else {
                            String wickets = dd.getString("Fall_of_wicket");
                            String[] splitted = wickets.split("at ");
                            Log.d("Splitted", splitted[0]);
                            String name = splitted[0];
                            Log.d("Splitted", splitted[1]);
                            String runsString = splitted[1];
                            String[] spl = runsString.split("runs, in");
                            String runs = spl[0];
                            Log.d("Splitted", runs);
                            String overStr = spl[1];
                            Log.d("Splitted2", overStr);
                            battingTeamthreList.add(new fallwicketsitem(i, runs, name, overStr
                            ));
                        }

                    }
                    adapterBattingTeamthre = new FallOFWicketAdapter(getActivity(), battingTeamthreList);
                    battingTeamthreeListView.setAdapter(adapterBattingTeamthre);


                    battingTeamforList.add(new fallwicketsitem("Fall of Wickets", " ", ""
                    ));
                    batsman2 = jsonObj.getJSONArray("Batsman4");
                    for ( i = 0; i < batsman2.length(); i++) {
                        JSONObject c = batsman2.getJSONObject(i);
                        String id = c.getString("Id");
                        if (id.equalsIgnoreCase("0")) {
                            Log.d("String Response", "acb");

                        } else {

                            run_str = c.getString("run_str");
                            FallOFWickets.team1.setText(run_str);
                            String name = c.getString("teamname");
                            PagerAdapterFallofWicketsTest.tabTitles[3] = name;
                            FallOFWickets.tabs.setViewPager(FallOFWickets.pager);


                        }

                    }

                    team3 = jsonObj.getJSONArray("team2_wicket_2");

                    for (i = 0; i < team3.length(); i++) {
                        JSONObject cccc = team3.getJSONObject(i);
                        String id = cccc.getString("Id");
                        if (id.equalsIgnoreCase("0")) {
                            Log.d("String Response", "acb");

                        } else {
                            String wickets = cccc.getString("Fall_of_wicket");
                            Log.d("Splitted", wickets);
                            String[] splitted = wickets.split(" at ");
                            Log.d("Splitted", splitted[0]);
                            String name = splitted[0];
                            Log.d("Splitted", splitted[1]);
                            String runsString = splitted[1];
                            String[] spl = runsString.split("runs, in");
                            String runs = spl[0];
                            Log.d("Splitted", runs);
                            String overStr = spl[1];
                            Log.d("Splitted", overStr);
                            battingTeamforList.add(new fallwicketsitem(i, runs, name, overStr
                            ));

                        }
                    }


                    adapterBattingTeamfor = new FallOFWicketAdapter(getActivity(), battingTeamforList);
                    FallOFWicketTest2_Team2.battingTeamforListView.setAdapter(adapterBattingTeamfor);

                    CommonMethods.hideProgressDialog();

                } catch (Exception e) {
                    e.printStackTrace();


                }


            } else {
//                layoutTeamTwo.setVisibility(View.GONE);
//                layoutTeamOne.setVisibility(View.GONE);
                //  tabTeamTwo.setText("yet to Bat");
                battingTeamforList.clear();
                Toast.makeText(getActivity(), "Yet to Play", Toast.LENGTH_LONG).show();
                CommonMethods.hideProgressDialog();
//                mHandler.removeCallbacks(m_Runnable);
            }


        }

    }
}
