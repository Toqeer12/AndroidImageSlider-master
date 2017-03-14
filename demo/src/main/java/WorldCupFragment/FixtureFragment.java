package WorldCupFragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.koherent.pdlapps.cricketworldcup2015live.Advertise;
import com.koherent.pdlapps.cricketworldcup2015live.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.Items;
import adapter.CustomAdapter;
import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 9/16/2015.
 */
public class FixtureFragment extends Fragment {
    private static String url = Constants.FixtureWc;
    JSONArray contacts = null;
    ServiceHandler sh;
    String team1 = "teamAname";
    String team2 = "teamBname";
    String series = "related_name";
    String venue = "venue";
    String abc = "start_date";
    String teamAkey="teamAkey";
    String teamBkey="teamBkey";
    String jsonStr;
    JSONArray arr = null;
    CustomAdapter adapter;
    ArrayList<Items> itemobject;
    TextView title;
    ListView list;
    Advertise advertise;
    public FixtureFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fixturewcfrag, container,
                false);
        advertise=new Advertise();
        advertise.InterstitialAd(getActivity());
        title=(TextView)view.findViewById(R.id.titled);
        list=(ListView)view.findViewById(R.id.fxView);
        itemobject = new ArrayList<Items>();
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
            itemobject.clear();
            super.onPreExecute();
            // Showing progress dialog


         //   CommonMethods.showProgressDialog(getActivity());

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

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray("Toqeer");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String team = c.getString(team1);
                        String teamtwo = c.getString(team2);
                        String ser = c.getString(series);
                        String loc = c.getString(venue);
                        String date = c.getString(abc);
                        String teamA = c.getString(teamAkey);
                        String teamB = c.getString(teamBkey);
                        String status = c.getString("status");
                        // Phone node is JSON Object
                        if (status.equalsIgnoreCase("0")) {
                            list.setVisibility(View.GONE);
                            title.setText("No match in current month!");
                            CommonMethods.hideProgressDialog();
                        }
                        // Phone node is JSON Object
                        else {
                            title.setVisibility(View.GONE);
                            itemobject.add(new Items(team, teamtwo, ser, loc, date, teamA, teamB));
                            // tmp hashmap for single contact
                            Log.d("Response: ", "> " + date);

                            adapter = new CustomAdapter(getActivity(), itemobject);
                            list.setAdapter(adapter);
                            CommonMethods.hideProgressDialog();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();


                }
            }
        }
    }
}
