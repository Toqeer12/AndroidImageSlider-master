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

import com.koherent.pdlapps.cricketworldcup2015live.R;

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

import Model.HistoryModel;
import adapter.HistoryDetailAdapter;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 9/16/2015.
 */
public class HistoryFragment extends Fragment {
    Intent i;
    String id;
    JSONArray contacts = null;
    String jsonStr;
    ListView list;
    HistoryDetailAdapter adapter;
    Button back;
    List<HistoryModel> historyObject;
    public String url= Constants.HistoryDetail;


    public HistoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        historyObject=new ArrayList<HistoryModel>();
        id="2";
        View view = inflater.inflate(R.layout.historyfragdetail, container,
                false);
        list=(ListView)view.findViewById(R.id.histView);

        if(CommonMethods.isNetworkAvailable(getActivity())) {
            new HistpryDetailFetch().execute();
        }
        else
        {

        }
        return view;
    }


    private class HistpryDetailFetch extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // CommonMethods.showProgressDialog(LiveScoree.this);

         //   CommonMethods.showProgressDialog(getActivity());


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

                nameValuePairs.add(new BasicNameValuePair("id", id));


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
                    contacts = jsonObj.getJSONArray("history");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String Winner =c.getString("Winner");
                        String runner=c.getString("Runner Up");
                        String year=c.getString("Year");
                        String winnerrun=c.getString("WinnerTeamRun");
                        String runnerrun=c.getString("RunnerUpRun");
                        historyObject.add(new HistoryModel(year,Winner,runner,winnerrun,runnerrun));
                        adapter=new HistoryDetailAdapter(getActivity(),historyObject);
                        list.setAdapter(adapter);

                        CommonMethods.hideProgressDialog();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }

        }
    }
}

