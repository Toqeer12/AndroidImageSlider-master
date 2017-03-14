package PageFragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.koherent.pdlapps.cricketworldcup2015live.PlayerRankDetail;
import com.koherent.pdlapps.cricketworldcup2015live.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.Playeritem;
import adapter.PlayerAdapter;
import adapter.ServiceHandler;
import utilities.CommonMethods;

/**
 * Created by TOQEER on 8/18/2015.
 */
public class FragmentPlayerTest extends Fragment {
    JSONArray Test = null;
    JSONArray ODI = null;
    String jsonStr;
    JSONArray T20 = null;
    ServiceHandler sh;
    String url= PlayerRankDetail.url;
    public static ArrayList<Playeritem> itemobject;
    public static ArrayList<Playeritem> itemobject2;
    public static ArrayList<Playeritem> itemobject3;
    ListView playerlistView;
    public static PlayerAdapter adapter;

//    Advertise advertise;
    public FragmentPlayerTest() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemobject = new ArrayList<Playeritem>();
        itemobject2=new ArrayList<Playeritem>();
        itemobject3=new ArrayList<Playeritem>();
        View view = inflater.inflate(R.layout.fragment_batting, container,
                false);
//        advertise=new Advertise();
//        advertise.InterstitialAd(getActivity());

        playerlistView=(ListView)view.findViewById(R.id.fragmentlist);

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
            itemobject2.clear();
            itemobject3.clear();
            CommonMethods.hideProgressDialog();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    ODI = jsonObj.getJSONArray("odi");
                    for (int i = 0; i < ODI.length(); i++) {
                        JSONObject c = ODI.getJSONObject(i);

                        String name = c.getString("Name");
                        String rank = c.getString("Rank");
                        String country = c.getString("Country");
                        String rating = c.getString("Rating");

                        itemobject.add(new Playeritem(rank, name, country,rating));

                        adapter=new PlayerAdapter(getActivity(),itemobject);
//
                        FragmentPlayerODI.playerodi.setAdapter(adapter);
                    }

                    Test = jsonObj.getJSONArray("test");

                    // looping through Allt Contacts
                    for (int i = 0; i < Test.length(); i++) {
                        JSONObject c = Test.getJSONObject(i);

                        String name = c.getString("Name");
                        String rank = c.getString("Rank");
                        String country = c.getString("Country");
                        String rating = c.getString("Rating");
                        itemobject2.add(new Playeritem(rank, name, country,rating));
                        adapter=new PlayerAdapter(getActivity(),itemobject2);
                        playerlistView.setAdapter(adapter);
                    }


                    T20 = jsonObj.getJSONArray("t20");


                    // looping through All Contacts
                    for (int i = 0; i < T20.length(); i++) {
                        JSONObject c = T20.getJSONObject(i);


                        String name = c.getString("Name");
                        String rank = c.getString("Rank");
                        String country = c.getString("Country");
                        String rating = c.getString("Rating");

                        itemobject3.add(new Playeritem(rank, name, country,rating));
                        adapter=new PlayerAdapter(getActivity(),itemobject3);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
