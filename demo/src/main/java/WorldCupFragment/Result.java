package WorldCupFragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.koherent.pdlapps.cricketworldcup2015live.LiveScoree;
import com.koherent.pdlapps.cricketworldcup2015live.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.LivelistItem;
import adapter.RecentMatch_Adapter;
import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 9/16/2015.
 */
public class Result extends Fragment {

    ListView listview;
    ArrayList<LivelistItem> livelistArray = new ArrayList<LivelistItem>();
    String team1 = "teamAname";
    String team2 = "teamBname";
    String key = "match_key";
    String date = "start_date";
    //   String season="season_name";
    String status = "status";
    String venuee = "venue";
    String jsonStr;
    String statuss;
    String link;
    JSONArray arr = null;
    JSONArray contacts = null;
    ServiceHandler sh;
    RecentMatch_Adapter adapter;
    TextView tv;

    LinearLayout layout;
    String url = Constants.Recent_Matchwc;

    public Result() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recent_matchwc, container,
                false);
        listview=(ListView)view.findViewById(R.id.recentwc);
        tv = (TextView)view.findViewById(R.id.marque);

        if(CommonMethods.isNetworkAvailable(getActivity())) {
            new GetContacts().execute();
        }
        else {
            Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();
        }

        return view;
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            livelistArray.clear();
            super.onPreExecute();
            // Showing progress dialog


        //    CommonMethods.showProgressDialog(getActivity());

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            sh = new ServiceHandler();

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            livelistArray.clear();
            super.onPostExecute(result);

            Log.d("Response: ", "> " + jsonStr);
            if (jsonStr != null) {


                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray("Toqeer");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String team = c.getString(team1);
                        String teamttwo = c.getString(team2);
                        String match_key = c.getString(key);
                        String dateformat = c.getString(date);
                        String season = c.getString("season_name");
                        String ven = c.getString("venue");
                        String winner = c.getString("MsgOther");
                        String related = c.getString("related_name");
                        statuss = c.getString(status);

                        // Phone node is JSON Object

                        livelistArray.add(new LivelistItem(team, teamttwo, statuss, match_key, dateformat, season, ven, winner, related));
                        // tmp hashmap for single contact

                        adapter = new RecentMatch_Adapter(getActivity(), livelistArray);
                        listview.setAdapter(adapter);

                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                              //  Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();
                                LivelistItem livelistItem;
                                livelistItem = livelistArray.get(position);
                                Intent i = new Intent(getActivity(),LiveScoree.class);
                                i.putExtra("key", livelistItem.getKey());
                                i.putExtra("date", livelistItem.getDate());
                                i.putExtra("Live","wc");

                                startActivity(i);
                         /*       LivelistItem livelistItem;
                                livelistItem = livelistArray.get(position);
                                Log.d("out IF", "" + livelistItem.getKey());
                                LiveScoreFragment fr = new LiveScoreFragment();
                                FragmentTransaction trans = getFragmentManager().beginTransaction();
                                Bundle args = new Bundle();

                                args.putString("key", livelistItem.getKey());
                                args.putString("date", livelistItem.getDate());
                                args.putString("Live", "Recent");
                                fr.setArguments(args);
                                trans.replace(R.id.layoutfragrecent, fr);
                                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                                trans.commit();*/


                            }


                        });
                        CommonMethods.hideProgressDialog();


                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();


                }

            } else {
                tv.setText("No Recent Match Found!");
            }

        }


    }
}

