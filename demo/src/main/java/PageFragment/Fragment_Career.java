package PageFragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.koherent.pdlapps.cricketworldcup2015live.PlayerStatDetail;
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
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.ServiceHandler;
import utilities.CommonMethods;

/**
 * Created by TOQEER on 8/19/2015.
 */
public class Fragment_Career extends Fragment {
    ServiceHandler sh;
    String jsonStr;
    JSONArray jsonArray = null;


    public static String Testdubet;
    public static String lastestdubet;
    public static String odidubet;
    public static String lastodidubet;
    public static String t20dubet;
    public static String lastt20dubet;
    TextView test,lasttest,odi,lastodi,t20,lastt20;
    public Fragment_Career() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_career, container,
                false);
        test=(TextView)view.findViewById(R.id.testdb);
        lasttest=(TextView)view.findViewById(R.id.lasttest);
        odi=(TextView)view.findViewById(R.id.odudebut);
        lastodi=(TextView)view.findViewById(R.id.lastodi);
        t20=(TextView)view.findViewById(R.id.t20debut);
        lastt20=(TextView)view.findViewById(R.id.lastt20);

        if(CommonMethods.isNetworkAvailable(getActivity())) {
            new GetPlayerState().execute();
        }
        else {
            Toast.makeText(getActivity(), "Internet is not Available", Toast.LENGTH_LONG).show();

        }
        return view;
    }

    private class GetPlayerState extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressbar
          //  CommonMethods.showProgressDialog(getActivity());

        }

        @Override
        protected Void doInBackground(String... Url) {
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost(PlayerStatDetail.url);
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
                        1);

                nameValuePairs.add(new BasicNameValuePair("PID", PlayerStatDetail.pl_key));


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
                Log.d("Player Name", jsonStr);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
            return null;

        }

        @Override
        protected void onPostExecute(Void args) {


            CommonMethods.hideProgressDialog();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    jsonArray = jsonObj.getJSONArray("Toqeer");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);


                        Testdubet=c.getString("TestDebut");
                        lastestdubet=c.getString("LastTestDebut");
                        odidubet=c.getString("OdiDebut");
                        lastodidubet=c.getString("LastOdiDebut");
                        t20dubet=c.getString("T20Debut");
                        lastt20dubet=c.getString("LastT20Debut");


                        test.setText(Testdubet);
                        lasttest.setText(lastestdubet);
                        odi.setText(odidubet);
                        lastodi.setText(lastodidubet);
                        t20.setText(t20dubet);
                        lastt20.setText(lastt20dubet);
                    }

                } catch (Exception ex) {


                }
            }
        }

    }
}
