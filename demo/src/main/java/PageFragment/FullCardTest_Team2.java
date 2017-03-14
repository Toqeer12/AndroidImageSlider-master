package PageFragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.koherent.pdlapps.cricketworldcup2015live.R;

import java.util.ArrayList;

import Model.InningsOneBatting;
import adapter.CustomBattingAdapter;
import utilities.Constants;

/**
 * Created by TOQEER on 9/9/2015.
 */
public class FullCardTest_Team2 extends Fragment{
    public static ListView battingTeamTwoListView;
    ArrayList<InningsOneBatting> battingTeamTwoList;
    Handler mHandler;
    String nb,bb,wb,lb,total;

    int refreshCounter =1;
    String jsonStr;
    String url ;
    public static String name;
    String run_str;
    public static CustomBattingAdapter adapterBattingTeamTwo;
    public FullCardTest_Team2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        url= Constants.TestFullCard;

        View view = inflater.inflate(R.layout.team2test_list, container,
                false);
        battingTeamTwoList = new ArrayList<InningsOneBatting>();
        battingTeamTwoListView=(ListView)view.findViewById(R.id.teamttwotestListView3);

//        if(CommonMethods.isNetworkAvailable(getActivity())) {
//            Log.d("Fragment ","Fragment2");
//           // new GetScoreAutoCardDetails().execute();
//        }
//        else
//        {
//            Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();
//        }

        return view;

    }


 /*   public class GetScoreAutoCardDetails extends AsyncTask<Void,Void,Void> {

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
               // CommonMethods.showProgressDialog(getActivity());
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
            battingTeamTwoList.clear();
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

                    battingTeamTwoList.add(new InningsOneBatting("Batsman", "R", "B", "4s", "6s"
                            , "SR", ""));
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
                            battingTeamTwoList.add(new InningsOneBatting(batsmanname, batrun, balls, batfour, batsix
                                    , sr, batmanstatus));

                        }

                    }
                    battingTeamTwoList.add(new InningsOneBatting("Extra", nb, wb, bb, lb
                            , total, ""));
                    battingTeamTwoList.add(new InningsOneBatting(" ", "", " ", " ", " "
                            , " ", " "));

                    battingTeamTwoList.add(new InningsOneBatting("Bowler", "", "R", "O", "W"
                            , "E", ""));


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
                                    , economy, ""));

//                            FullCardPagerAdapter.tabTitles[0] = name;
                            FullCardPagerTestAdapter.tabTitles[0] = name;
                            //
                            FullCardTesting.tabs.setViewPager(FullCardTesting.pager);

                        }
                    }

                    adapterBattingTeamTwo = new CustomBattingAdapter(getActivity(), battingTeamTwoList);
                    battingTeamTwoListView.setAdapter(adapterBattingTeamTwo);

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
    }*/
}
