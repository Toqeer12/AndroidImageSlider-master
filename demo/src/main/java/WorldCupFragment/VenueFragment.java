package WorldCupFragment;

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

import Model.venueitem;
import adapter.ServiceHandler;
import adapter.VenueDetailAdapter;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 9/16/2015.
 */
public class VenueFragment extends Fragment {
    String venuee = "Venue";
    String City = "City";
    String Link = "Link";
    JSONArray contacts = null;
    ServiceHandler sh;
    ArrayList<venueitem> itemobject;
    String jsonStr;
    JSONArray arr = null;
    VenueDetailAdapter adapter;
    private ListView listView;
    String url;
    public VenueFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.venufrag, container,
                false);
        listView=(ListView)view.findViewById(R.id.listView);
        url = Constants.Wc;
        itemobject = new ArrayList<venueitem>();
        if(CommonMethods.isNetworkAvailable(getActivity())) {

            new GetVenue().execute();
        }
        else
        {
            Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();

        }
        return view;
    }
    private class GetVenue extends AsyncTask<Void, Void, Void> {

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

            Log.d("Response: ", "> " + jsonStr);


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

                        String ven = c.getString(venuee);
                        String city = c.getString(City);
                        String link = c.getString(Link);


                        // Phone node is JSON Object
                        Log.d("Response: ", "> " + ven+""+city+link);
                        itemobject.add(new venueitem(ven, city, link));
                        // tmp hashmap for single contact

                        adapter = new VenueDetailAdapter(getActivity(), itemobject);
                        listView.setAdapter(adapter);

                        CommonMethods.hideProgressDialog();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
