package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

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
 * Created by TOQEER on 9/11/2015.
 */
public class HistoryDetail extends Activity {
    Intent i;
    String id;
    JSONArray contacts = null;
    String jsonStr;
    ListView list;
    HistoryDetailAdapter adapter;
    Button back;
    List<HistoryModel> historyObject;
    public String url= Constants.HistoryDetail;
    LinearLayout layout;
    Advertise advertise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historydetail);
        list=(ListView)findViewById(R.id.histView);
        back=(Button)findViewById(R.id.back);
        historyObject=new ArrayList<HistoryModel>();
        advertise=new Advertise();
        layout= (LinearLayout)findViewById(R.id.add);
        advertise.Banner(layout, this);
        i = getIntent();
        id = i.getStringExtra("Id");


        if(CommonMethods.isNetworkAvailable(HistoryDetail.this)) {
            new HistpryDetailFetch().execute();
        }
        else
        {

        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), History.class);
                i.putExtra("Id",id);
                startActivity(i);
                finish();
            }
        });
    }

    private class HistpryDetailFetch extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
                // CommonMethods.showProgressDialog(LiveScoree.this);

                CommonMethods.showProgressDialog(HistoryDetail.this);


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
                        String spl[]=winnerrun.split(" ");
                        String win=spl[0];
                        String wino=spl[1];

                        String runnerrun=c.getString("RunnerUpRun");

                        String spl3[]=runnerrun.split(" ");
                        String runs=spl3[0];
                        String runso=spl3[1];
                        historyObject.add(new HistoryModel(year,Winner,runner,win,wino,runs,runso));
                        adapter=new HistoryDetailAdapter(HistoryDetail.this,historyObject);
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
