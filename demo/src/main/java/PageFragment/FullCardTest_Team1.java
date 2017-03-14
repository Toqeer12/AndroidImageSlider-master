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

import com.koherent.pdlapps.cricketworldcup2015live.FullCardTesting;
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

import Model.InningsOneBatting;
import adapter.CustomBattingAdapter;
import adapter.FullCardPagerTestAdapter;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 9/9/2015.
 */
public class FullCardTest_Team1 extends Fragment{
    public static ListView battingTeamOneListView;

    int refreshCounter =1;
    String jsonStr;
    String url ;
    TextView teamOneNameTV;
    TextView teamTwoNameTv;

    String run_str;
    ArrayList<InningsOneBatting> battingTeamOneList;

    ArrayList<InningsOneBatting> battingTeamThreeList;
    ArrayList<InningsOneBatting> battingTeamFoutList;
    Handler mHandler;
    String nb,bb,wb,lb,total;
    public static String name;
    //Advertise advertise;
    ArrayList<InningsOneBatting> battingTeamTwoList;
    public static CustomBattingAdapter adapterBattingTeamOne;
    CustomBattingAdapter adapterBattingTeamTwo;
    public static CustomBattingAdapter adapterBattingTeamThree;
    public static CustomBattingAdapter adapterBattingTeamfout;
    public FullCardTest_Team1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        url= Constants.TestFullCard;
        battingTeamOneList = new ArrayList<InningsOneBatting>();
        battingTeamTwoList = new ArrayList<InningsOneBatting>();


        View view = inflater.inflate(
                R.layout.team1test_list, container,
                false);
//        advertise=new Advertise();
//        advertise.InterstitialAd(getActivity());
        this.mHandler = new Handler();

        this.mHandler.postDelayed(m_Runnable,1000*30);
        battingTeamOneListView=(ListView)view.findViewById(R.id.teamOnetestListView2);
        if(CommonMethods.isNetworkAvailable(getActivity())) {
            Log.d("Fragment ","Fragment1");
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
                nameValuePairs.add(new BasicNameValuePair("match_key", FullCardTesting.key));
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
                    JSONArray batsman3 = null;
                    JSONArray bowler3 = null;
                    JSONArray batsman4 = null;
                    JSONArray bowler4 = null;
                    JSONArray team1 = null;
                    JSONArray team2 = null;
                    String idd=null;
                    batsman1 = jsonObj.getJSONArray("Batsman");

                    battingTeamOneList.add(new InningsOneBatting("Batsman", "R", "B", "4s", "6s"
                            , "SR", "",""));
                    for (int i = 0; i < batsman1.length(); i++) {

                        JSONObject c = batsman1.getJSONObject(i);
                         idd = c.getString("Id");
                        if (idd.equalsIgnoreCase("0")) {
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
                            FullCardTesting.team1.setText(run_str);

                            name = c.getString("teamname");

                            //  String dismissed=c.getString("dismissed");

                            String a[] = extra.split(" ");
                            nb = a[0];
                            wb = a[1];
                            bb = a[2];
                            lb = a[3];
                            total = a[4];
                            Log.d("String Response", total);
                            Log.d("Daani", balls);

                            FullCardPagerTestAdapter.tabTitles[0] = name;
                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                            FullCardPagerTestAdapter.tabTitles[2] = name;
                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                            battingTeamOneList.add(new InningsOneBatting(batsmanname, batrun, balls, batfour, batsix
                                    , sr, batmanstatus,""));

                        }
                    }
                    battingTeamOneList.add(new InningsOneBatting("Extra", nb, wb, bb, lb
                            , total, "",""));
                    battingTeamOneList.add(new InningsOneBatting(" ", "", " ", " ", " "
                            , " ", " ",""));

                    battingTeamOneList.add(new InningsOneBatting("Bowler", "", "R", "O", "W"
                            , "E", "",""));
                    bowler = jsonObj.getJSONArray("Bowler1");
                    for (int i = 0; i < bowler.length(); i++) {

                        JSONObject cc = bowler.getJSONObject(i);
                         idd = cc.getString("Id");
                        if (idd.equalsIgnoreCase("0")) {
                            Log.d("String Response", "acb");

                        } else {

                            String bowl = cc.getString("Bowlsman_Name");
                            String bowlrun = cc.getString("Runs");
                            String overs = cc.getString("Overs");
                            String wicket = cc.getString("Wickets");
                            String economy = cc.getString("Ecn");
                            run_str = cc.getString("run_str");
                            String name = cc.getString("teamname");
                            FullCardPagerTestAdapter.tabTitles[1] = name;
                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                            FullCardPagerTestAdapter.tabTitles[3] = name;
                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                            battingTeamOneList.add(new InningsOneBatting(bowl, "", bowlrun, overs, wicket
                                    , economy, "",""));
                        }

                    }
//
//                    adapterBattingTeamOne = new CustomBattingAdapter(getActivity(), battingTeamOneList);
//                    battingTeamOneListView.setAdapter(adapterBattingTeamOne);

                    battingTeamTwoList.add(new InningsOneBatting("Batsman", "R", "B", "4s", "6s"
                            , "SR", "",""));
                    batsman2 = jsonObj.getJSONArray("Batsman2");
                    for (int i = 0; i < batsman2.length(); i++) {
                        JSONObject c = batsman2.getJSONObject(i);
                         idd = c.getString("Id");
                        if (idd.equalsIgnoreCase("0")) {
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
                            FullCardTesting.team2.setText(run_str);
                            String name = c.getString("teamname");
                            Log.d("String Response", extra);
                            String a[] = extra.split(" ");
                            nb = a[0];
                            wb = a[1];
                            bb = a[2];
                            lb = a[3];
                            total = a[4];
                            Log.d("String Response", total);
                            //    tabTeamTwo.setText(name);
//
                            FullCardPagerTestAdapter.tabTitles[1] = name;
                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                            FullCardPagerTestAdapter.tabTitles[3] = name;
                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                            battingTeamTwoList.add(new InningsOneBatting(batsmanname, batrun, balls, batfour, batsix
                                    , sr, batmanstatus,""));

                        }

                    }
                    battingTeamTwoList.add(new InningsOneBatting("Extra", nb, wb, bb, lb
                            , total, "",""));
                    battingTeamTwoList.add(new InningsOneBatting(" ", "", " ", " ", " "
                            , " ", " ",""));

                    battingTeamTwoList.add(new InningsOneBatting("Bowler", "", "R", "O", "W"
                            , "E", "",""));


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

//                            FullCardPagerAdapter.tabTitles[0] = name;
                            FullCardPagerTestAdapter.tabTitles[0] = name;
                            //
                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                            FullCardPagerTestAdapter.tabTitles[2] = name;
                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                        }
                    }


//                    adapterBattingTeamTwo = new CustomBattingAdapter(getActivity(), battingTeamTwoList);
//                    FullCardTest_Team2.battingTeamTwoListView.setAdapter(adapterBattingTeamTwo);
                    if (idd.equalsIgnoreCase("0")){

//                            FullCardPagerAdapter.tabTitles[0] = battingTeamTwoList.get(0).getName();
//                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                        adapterBattingTeamOne=new CustomBattingAdapter(getActivity(),battingTeamTwoList);
                        battingTeamOneListView.setAdapter(adapterBattingTeamOne);

//                            FullCardPagerAdapter.tabTitles[1] = battingTeamOneList.get(1).getName();
//                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                        adapterBattingTeamTwo=new CustomBattingAdapter(getActivity(),battingTeamOneList);
                        FullCard_Team2.battingTeamTwoListView.setAdapter(adapterBattingTeamTwo);

                    }else{
//                             FullCardPagerAdapter.tabTitles[0] = battingTeamTwoList.get(0).getName();
//                             FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                        adapterBattingTeamOne=new CustomBattingAdapter(getActivity(),battingTeamTwoList);
                        battingTeamOneListView.setAdapter(adapterBattingTeamOne);

//                        FullCardPagerAdapter.tabTitles[1] = battingTeamOneList.get(1).getName();
//                        FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                        adapterBattingTeamTwo=new CustomBattingAdapter(getActivity(),battingTeamOneList);
                        FullCard_Team2.battingTeamTwoListView.setAdapter(adapterBattingTeamTwo);
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
                battingTeamOneList.clear();
                Toast.makeText(getActivity(),"Yet to Play",Toast.LENGTH_LONG).show();
                CommonMethods.hideProgressDialog();
//                mHandler.removeCallbacks(m_Runnable);
            }


        }
    }
}
