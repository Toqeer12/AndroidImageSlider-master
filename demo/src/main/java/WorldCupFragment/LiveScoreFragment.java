package WorldCupFragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.koherent.pdlapps.cricketworldcup2015live.R;

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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.LiveScore;
import adapter.ListScoreAdapter;
import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 9/21/2015.
 */
public class LiveScoreFragment extends Fragment {


    String jsonStr;
    public String url;
    public static String teamAname;
    public static String teamBname;

    ProgressDialog pDialog;
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
    String bowler1Maid = "bowlers_maiden";
    String bowler1Runs = "bowler_runs";
    String bowler1Wickets = "bowlers_wickets";
    String teamAkey = "teamAKey";
    String teamBkey = "teamBKey";
    String link = "Link";
    String linnk;
    String info = "msg";
        public static String mom;
    Thread myThread;
    boolean running;
    int cnt;
    int refreshCounter = 1;


    //String link = "LINK_TO_SCORECARD";

    String  key,datte,live;

    private ArrayList<LiveScore> liveScoreList;
    private ListScoreAdapter listScoreAdapter;
    private ListView listView;
    private View view;
    ServiceHandler sh;
    private MenuDrawer mDrawer;

    LinearLayout layout;
    Button btn, back, refresh;
    TextView home,comentry;
    String teamName1, teamName2;
    Handler mHandler;
    Thread thread;
    String match_key, type, inn,runstr,breakreason;
    JSONArray contacts = null;

    public LiveScoreFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        url= Constants.DemoAct;
        View view = inflater.inflate(R.layout.livescorefrag, container,
                false);
        liveScoreList = new ArrayList<LiveScore>();
        key=getArguments().getString("key");
        datte=getArguments().getString("date");
        live=getArguments().getString("Live");
        listView=(ListView)view.findViewById(R.id.livefrag);
        comentry=(TextView)view.findViewById(R.id.comentry);

        Log.d("Frag",key+""+live+datte);
        if(CommonMethods.isNetworkAvailable(getActivity())) {
        //   new FetchLiveScore().execute();
        }
        else {
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
                new FetchLiveScore().execute();
            }
            else
            {
                Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();
            }
            mHandler.postDelayed(m_Runnable, 1000*30);
        }

    };//runnable




    @Override
    public void onStop() {
        super.onStop();
//        mHandler.removeCallbacks(m_Runnable);
        // android.os.Process.killProcess(android.os.Process.myPid());
        Log.d("On Called", "Stop");
    }
    private class FetchLiveScore extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            liveScoreList.clear();
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
                        String bat1Name = c.getString(batsmen1Name);
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
                        linnk=c.getString(link);
                        String cominfo=c.getString(info);
                        String key=c.getString("match_key");
                        String run=c.getString("run_st");
                        String day=c.getString("Day");
                        runstr=c.getString("run_str");
                        breakreason=c.getString("break");
                        comentry.setText(linnk);
                        teamAname=c.getString("teamAName");
                        teamBname=c.getString("teamBName");
                        mom=c.getString("manofmatch");
                        liveScoreList.clear();
                        liveScoreList.add(new LiveScore(bothTeamsNames, tossStatus, batTeamScores,
                                bat1Name, bat1Runs, bat1Balls, bat2Name, bat2Runs, bat2Balls,
                                bow1Name, bow1Overs, bow1Maid, bow1Runs, bow1Wickets,
                                bowTeamName, batTeamName, team1key, team2key, current_innings, linnk, cominfo, run,key,day,runstr,breakreason,teamAname,teamBname,mom));


                        listScoreAdapter = new ListScoreAdapter(getActivity(), liveScoreList);

                        listView.setAdapter(listScoreAdapter);

//                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                                LiveScore item = liveScoreList.get(position);
//                                Intent i = new Intent(getActivity(), FullCardTesting.class);
//                                i.putExtra("key", item.getKey());
//                                i.putExtra("innings",item.getInnings());
//                                i.putExtra("type",type);
//                                startActivity(i);
//
//                         //       overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
//                                mHandler.removeCallbacks(m_Runnable);
//
//                                // } else {
//                                //  }
//                            }
//                        });

                        CommonMethods.hideProgressDialog();

                    }
                }catch(JSONException e){
                    e.printStackTrace();

                }


            }
        }
    }
}
