package WorldCupFragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.koherent.pdlapps.cricketworldcup2015live.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.PointTable;
import adapter.PointTableAdapter;
import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 9/16/2015.
 */
public class PointTableFragment extends Fragment {
    LinearLayout layoutTeamOne;
    LinearLayout layoutTeamTwo;
    Button tabTeamOne;
    Button tabTeamTwo;
    int tabOneCounter = 1;
    int tabTwoCounter = 0;
    private static String url = Constants.pointable;
    JSONArray contacts = null;
    JSONArray contacts2 = null;
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
    PointTableAdapter adapter;
    PointTableAdapter adapter2;
    ArrayList<PointTable> itemobject;
    ArrayList<PointTable> itemobject2;
    ListView teamOneListView;
    ListView teamTwoListView;
    public PointTableFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemobject=new ArrayList<PointTable>();
        itemobject2=new ArrayList<PointTable>();
        View view = inflater.inflate(R.layout.pointtable, container,
                false);
        layoutTeamOne = (LinearLayout) view.findViewById(R.id.layout_TeamOne);
        layoutTeamTwo = (LinearLayout) view.findViewById(R.id.layout_TeamTwo);
        teamOneListView = (ListView) view.findViewById(R.id.teamOneListView);
        teamTwoListView = (ListView) view.findViewById(R.id.teamTwoListView);
        tabTeamOne = (Button) view.findViewById(R.id.button_TeamOne);
        tabTeamOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tabOneCounter==0) {
                    tabTeamOne.setBackgroundColor(Color.parseColor("#000031"));
                    tabTeamTwo.setBackgroundColor(Color.TRANSPARENT);
                    layoutTeamTwo.setVisibility(View.GONE);
                    layoutTeamOne.setVisibility(View.VISIBLE);
                    tabOneCounter=1;
                    tabTwoCounter=0;
                }
            }
        });

        tabTeamTwo = (Button) view.findViewById(R.id.button_TeamTwo);
        tabTeamTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tabTwoCounter==0) {
                    tabTeamOne.setBackgroundColor(Color.TRANSPARENT);
                    tabTeamTwo.setBackgroundColor(Color.parseColor("#000031"));
                    layoutTeamOne.setVisibility(View.GONE);
                    layoutTeamTwo.setVisibility(View.VISIBLE);
                    tabTwoCounter=1;
                    tabOneCounter=0;
                }
            }
        });

        if(CommonMethods.isNetworkAvailable(getActivity())) {

            new GetContacts().execute();
        }
        else
        {
            Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();

        }
    return view;
    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            itemobject.clear();
            itemobject2.clear();
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
            super.onPostExecute(result);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray("PoolA");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String country = c.getString("CountryName");
                        String p = c.getString("P");
                        String w = c.getString("W");
                        String l = c.getString("L");
                        String t = c.getString("T");
                        String nr = c.getString("NR");
                        String pts = c.getString("Pts");
                        String nrr = c.getString("NRR");
                        // Phone node is JSON Object

//                            title.setVisibility(View.GONE);
                            itemobject.add(new PointTable(country, p, w, l,t, nr, pts, nrr));
                            // tmp hashmap for single contact
                            Log.d("Response: ", "> " + country);

                            adapter = new PointTableAdapter(getActivity(), itemobject);
                        teamOneListView.setAdapter(adapter);
                            CommonMethods.hideProgressDialog();

                    }


                    contacts2 = jsonObj.getJSONArray("PoolB");

                    // looping through All Contacts
                    for (int i = 0; i < contacts2.length(); i++) {
                        JSONObject c = contacts2.getJSONObject(i);

                        String country = c.getString("CountryName");
                        String p = c.getString("P");
                        String w = c.getString("W");
                        String l = c.getString("L");
                        String t = c.getString("T");
                        String nr = c.getString("NR");
                        String pts = c.getString("Pts");
                        String nrr = c.getString("NRR");
                        // Phone node is JSON Object

//                        title.setVisibility(View.GONE);
                        itemobject2.add(new PointTable(country, p, w, l,t, nr, pts, nrr));
                        // tmp hashmap for single contact
//                        Log.d("Response: ", "> " + country);

                        adapter2 = new PointTableAdapter(getActivity(), itemobject2);
                        teamTwoListView.setAdapter(adapter2);
                        CommonMethods.hideProgressDialog();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();


                }
            }
            else
            {CommonMethods.hideProgressDialog();

            }
        }
    }
}
