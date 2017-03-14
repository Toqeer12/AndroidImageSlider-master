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

import com.koherent.pdlapps.cricketworldcup2015live.Advertise;
import com.koherent.pdlapps.cricketworldcup2015live.R;
import com.koherent.pdlapps.cricketworldcup2015live.TestActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.LivelistItem;
import adapter.LiveListAdapter;
import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 9/16/2015.
 */
public class LiveFragment extends Fragment{

    ListView listview;
    ArrayList<LivelistItem> livelistArray = new ArrayList<LivelistItem>();
    String team1 = "teamAname";
    String team2 = "teamBname";
    String key = "match_key";
    String date="start_date";
    //    String Check="Check";
    String status="status";
    String jsonStr;
    String statuss;
    String link;
    JSONArray arr = null;
    JSONArray contacts = null;
    ServiceHandler sh;
    LiveListAdapter adapter;
    TextView tv;
        Advertise advertise;
    LinearLayout layout;
    String url= Constants.LiveMatchDetail_URLWC;

    public LiveFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.livefragmentwc, container,
                false);
        advertise=new Advertise();
        advertise.InterstitialAd(getActivity());
        tv = (TextView)view.findViewById(R.id.marque);
        listview=(ListView)view.findViewById(R.id.livelistwc);

        if(CommonMethods.isNetworkAvailable(getActivity())) {
            new GetContacts().execute();
        }
        else
        {

        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LivelistItem livelistItem;
                livelistItem = livelistArray.get(position);
                Log.d("out IF", "" + livelistItem.getKey());
//                if(livelistItem.getCheck().equalsIgnoreCase("true"))
//                {
//                    Bundle b = new Bundle();
//
                Intent i = new Intent(getActivity(), TestActivity.class);
                i.putExtra("key", livelistItem.getKey());
                i.putExtra("date", livelistItem.getDate());
                i.putExtra("Live", "wc");
                startActivity(i);





            }


        });
        return view;
    }
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            livelistArray.clear();
            super.onPreExecute();
            // Showing progress dialog


            CommonMethods.showProgressDialog(getActivity());

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
            super.onPostExecute(result);

            Log.d("Response: ", "> " + jsonStr);
            if(jsonStr!=null)
            {


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
                        String dateformat=c.getString(date);
                        String season = c.getString("season_name");
                        String ven = c.getString("venue");
                        String related=c.getString("related_name");

                        statuss=c.getString(status);
                        if(statuss.equalsIgnoreCase("0"))
                        {
                            listview.setVisibility(View.GONE);
                            tv.setText("No Live Match Today");
                            CommonMethods.hideProgressDialog();
                        }
                        // Phone node is JSON Object
                        else {

                            tv.setVisibility(View.GONE);
                            livelistArray.add(new LivelistItem(team, teamttwo, statuss, match_key, dateformat, season, ven, related));


                            adapter = new LiveListAdapter(getActivity(), livelistArray);
                            listview.setAdapter(adapter);

                            CommonMethods.hideProgressDialog();

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();


                    //String json2 = sharedPrefs.getString(LIST_2_KEY, null);




                }

            }
        }
    }
}
