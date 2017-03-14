package PageFragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.koherent.pdlapps.cricketworldcup2015live.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.TeamItem;
import adapter.ServiceHandler;
import adapter.TeamAdapter;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 8/17/2015.
 */
public class FragmentTest extends Fragment {
    JSONArray contacts = null;


    String jsonStr;
    JSONArray arr = null;
    private ProgressDialog pDialog;

    String url= Constants.Team_Ranking_URL;
    JSONArray Test = null;
    JSONArray ODI = null;
    JSONArray T20 = null;
    ServiceHandler sh;
     TeamAdapter adapter;
    //Advertise advertise;
    ArrayList<TeamItem> itemobject;
    public static ListView listViewTest;
    public FragmentTest() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemobject = new ArrayList<TeamItem>();

        View view = inflater.inflate(R.layout.fragment_test, container,
                false);
//        advertise=new Advertise();
//        advertise.InterstitialAd(getActivity());
        listViewTest=(ListView)view.findViewById(R.id.fragmentlistplayer);

        if(CommonMethods.isNetworkAvailable(getActivity())) {
        new GetTeamDetail().execute();
        }
        else
        {
            Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();
        }
        return view;
    }
    private class GetTeamDetail extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
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

            Log.d("Response: ", "> " + jsonStr);


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            itemobject.clear();
            CommonMethods.hideProgressDialog();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    T20 = jsonObj.getJSONArray("test");
                    for (int i = 0; i < T20.length(); i++) {
                        JSONObject c = T20.getJSONObject(i);
                        String id=c.getString("Id");
                        String teamm = c.getString("Team");
                        String matchh = c.getString("Matches");
                        String pointss = c.getString("Points");
                        String ratingg = c.getString("Rating");


//                        // Phone node is JSON Object
//                        Log.d("Response: ", "> " + teamm );
                        itemobject.add(new TeamItem(id,teamm, matchh, pointss,ratingg));
                        // tmp hashmap for single contact

                        adapter=new TeamAdapter(getActivity(),itemobject);
                        listViewTest.setAdapter(adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
