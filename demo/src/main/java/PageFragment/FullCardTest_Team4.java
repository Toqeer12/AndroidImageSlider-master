package PageFragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.koherent.pdlapps.cricketworldcup2015live.R;

import java.util.ArrayList;

import Model.InningsOneBatting;
import adapter.CustomBattingAdapter;
import utilities.Constants;

/**
 * Created by TOQEER on 9/9/2015.
 */
public class FullCardTest_Team4 extends Fragment{


    public static ListView battingTeamfourListView;

    int refreshCounter =1;
    String jsonStr;
    String url ;
    TextView teamOneNameTV;
    TextView teamTwoNameTv;

    String run_str;

    ArrayList<InningsOneBatting> battingTeamFoutList;

    String nb,bb,wb,lb,total;
    public static String name;
    Handler mHandler;

    ArrayList<InningsOneBatting> battingTeamthreeList;
    public static CustomBattingAdapter adapterBattingTeamfour;
    public static CustomBattingAdapter adapterBattingTeamthreee;
    public FullCardTest_Team4() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        url= Constants.TestFullCard;
//        this.mHandler = new Handler();
//
//        this.mHandler.postDelayed(m_Runnable,1000*30);
        View view = inflater.inflate(R.layout.teamtest4_list, container,
                false);

//        battingTeamFoutList=new ArrayList<InningsOneBatting>();
//        battingTeamthreeList=new ArrayList<InningsOneBatting>();
        battingTeamfourListView=(ListView)view.findViewById(R.id.teamfourtestListView2);
//        battingTeamTwoListView.setAdapter(FullCard_Team1.adapterBattingTeamTwo);



//        if(CommonMethods.isNetworkAvailable(getActivity())) {
//            Log.d("Fragment ","Fragment4");
//            new GetScoreAutoCardDetails().execute();
//        }
//        else
//        {
//            Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();
//        }





        return view;


    }
  /*  private final Runnable m_Runnable = new Runnable()
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
        Handler mHandler;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(refreshCounter==0) {
                // CommonMethods.showProgressDialog(LiveScoree.this);
                Toast.makeText(getActivity(), "Auto Refresh", Toast.LENGTH_LONG).show();

            }
            else {
//                CommonMethods.showProgressDialog(getActivity());
            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("match_key", "engaus_2015_test_01"));
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
            battingTeamFoutList.clear();
            battingTeamthreeList.clear();
            if (!jsonStr.equalsIgnoreCase("null")) {
                Log.d("Post ", "IN IF");
                try {
                    JSONObject jsonObj = null;

                    jsonObj = new JSONObject(jsonStr);
                    // String currRunRate = jsonObj.getString(CurrentRunRate);
                    JSONArray batsman4 = null;
                    JSONArray bowler4 = null;
                    JSONArray batsman3 = null;
                    JSONArray bowler3 = null;
                    batsman4 = jsonObj.getJSONArray("Batsman4");

                    battingTeamFoutList.add(new InningsOneBatting("Batsman", "R", "B", "4s", "6s"
                            , "SR", ""));
                    for (int i = 0; i < batsman4.length(); i++) {

                        JSONObject c = batsman4.getJSONObject(i);
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
                            FullCardTesting.team2.setText(run_str);

                            name = c.getString("teamname");

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

                            FullCardPagerTestAdapter.tabTitles[3] = name;
                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                            battingTeamFoutList.add(new InningsOneBatting(batsmanname, batrun, balls, batfour, batsix
                                    , sr, batmanstatus));

                        }
                    }
                    battingTeamFoutList.add(new InningsOneBatting("Extra", nb, wb, bb, lb
                            , total, ""));
                    battingTeamFoutList.add(new InningsOneBatting(" ", "", " ", " ", " "
                            , " ", " "));

                    battingTeamFoutList.add(new InningsOneBatting("Bowler", "", "R", "O", "W"
                            , "E", ""));
                    bowler4 = jsonObj.getJSONArray("Bowler4");
                    for (int i = 0; i < bowler4.length(); i++) {

                        JSONObject cc = bowler4.getJSONObject(i);
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
                            FullCardPagerTestAdapter.tabTitles[2] = name;
                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);
                            battingTeamFoutList.add(new InningsOneBatting(bowl, "", bowlrun, overs, wicket
                                    , economy, ""));
                        }

                    }

                    adapterBattingTeamfour = new CustomBattingAdapter(getActivity(), battingTeamFoutList);
                    battingTeamfourListView.setAdapter(adapterBattingTeamfour);




                    CommonMethods.hideProgressDialog();
                }

                catch (Exception e) {
                    e.printStackTrace();


                }


            }

            else
            {

                battingTeamFoutList.clear();
                Toast.makeText(getActivity(),"Yet to Play",Toast.LENGTH_LONG).show();
                CommonMethods.hideProgressDialog();
            }


        }
    }*/
}
