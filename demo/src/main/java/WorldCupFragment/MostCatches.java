package WorldCupFragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.koherent.pdlapps.cricketworldcup2015live.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.BestBowlingModel;
import adapter.MostCatchesAdapter;
import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 9/21/2015.
 */
public class MostCatches extends Fragment {

    Intent i;
    String id;

    String jsonStr;
    ListView list;

    Button back;
    JSONArray contacts = null;
    ServiceHandler sh;
    List<BestBowlingModel> Object;
    public String url= Constants.mostcatches;
    MostCatchesAdapter adapter;
    public MostCatches() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Object = new ArrayList<BestBowlingModel>();
        id = "2";
        View view = inflater.inflate(R.layout.mostcatches, container,
                false);
        list=(ListView)view.findViewById(R.id.mostcatch);
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
                    contacts = jsonObj.getJSONArray("MostCatches");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);


                        String playername = c.getString("Name");
                        String match = c.getString("Matches");
                        String catches = c.getString("Catches");

                        // Phone node is JSON Object

//                            title.setVisibility(View.GONE);
                        Object.add(new BestBowlingModel(playername,match, catches));
                        // tmp hashmap for single contact
                        Log.d("Response: ", "> " + playername);

                        adapter = new MostCatchesAdapter(getActivity(), Object);
                        list.setAdapter(adapter);
                        CommonMethods.hideProgressDialog();

                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();


                }
            }
            else
            {

                CommonMethods.hideProgressDialog();

            }
        }
    }
}
