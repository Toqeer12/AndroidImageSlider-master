package com.koherent.pdlapps.cricketworldcup2015live;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by TOQEER on 8/24/2015.
 */
public class TestActivity extends Activity {
    JSONArray contacts = null;
    Intent i;
    Button back;
    ServiceHandler sh;
    String jsonStr;
    JSONArray jsonArray = null;
    String match_key;
    String url = Constants.Squard;
    String final_format,final_format2;
    TextView textViewTime,textViewsec,teamA,teamB,tossinfo;
    long different;
    String your_format = null;
    String your_format2 = null;
    ConnectionDetector cd;
    String your_format4 = null;
    String key,datte,live;

    Advertise advertise;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdown);
        back=(Button)findViewById(R.id.back);
        i=getIntent();
        key=i.getStringExtra("key");
        datte=i.getStringExtra("date");
        live=i.getStringExtra("Live");
        Log.d("Date Get",datte);
//        revmob = RevMob.startWithListener(this, revmobListener);
//        showBanner();

        layout= (LinearLayout)findViewById(R.id.add);
        advertise = new Advertise();
        advertise.Banner(layout,this);
        teamA=(TextView)findViewById(R.id.teamA);
        teamB=(TextView)findViewById(R.id.teamB);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewsec=(TextView)findViewById(R.id.textViewsec);
        tossinfo=(TextView)findViewById(R.id.tossinfo);
        textViewTime.setText("00:00:00");

        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            showAlertDialog(TestActivity.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }


        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");

        int hour=c.get(Calendar.HOUR_OF_DAY);
        int min=c.get(Calendar.MINUTE);
        int sec=c.get(Calendar.SECOND);
        String str=hour+":"+min+":"+sec;
        String strDate = sdf2.format(c.getTime());
        Log.d("Date System", strDate);

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(live.equalsIgnoreCase("wc")) {
                    i = new Intent(getApplicationContext(), WorldCup.class);
                    savePreferences("add", true);
                    startActivity(i);
                    finish();
                }else
                {
                    i = new Intent(getApplicationContext(), LiveList.class);
                    savePreferences("add", true);
                    startActivity(i);
                    finish();
                }
            }
        });
        try {

            Date date2 = sdf2.parse(datte);
            Log.d("DateTime", "" + date2);
            your_format2 = new SimpleDateFormat("kk:mm:ss").format(date2);
            Log.d("DateTime", your_format2);
        } catch (Exception e) {
            e.printStackTrace(); //date format error
        }
        try {

            Date date = sdf2.parse(datte);
            System.out.println(date);
            your_format = new SimpleDateFormat("yyyy-MM-dd").format(date);
            String[] splitted = your_format.split(" ");
            Log.d("DateTime", your_format);    //The second part of the splitted string, i.e time

        } catch (Exception e) {
            e.printStackTrace(); //date format error
        }

        try {

            Date date4 = sdf2.parse(strDate);
            System.out.println(date4);
            your_format4 = new SimpleDateFormat("yyyy-MM-dd").format(date4);
            String[] splitted = your_format4.split(" ");
            Log.d("DateTime", your_format4);    //The second part of the splitted string, i.e time

        } catch (Exception e) {
            e.printStackTrace();
        }
        final_format2=your_format4+" "+str ;
        final_format = your_format + " " + your_format2;
        Log.d("DateTimeSTr",str);
        Log.d("DateTime", final_format2);
        Log.d("DateTime", final_format);
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        try {
            Date date1 = simpleDateFormat.parse(final_format2);
            Date date2 = simpleDateFormat.parse(final_format);
            printDifference(date1, date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }



    public void printDifference(Date startDate, Date endDate) {

        //milliseconds
        different = endDate.getTime() - startDate.getTime();
        final CounterClass timer = new CounterClass(different, 1000);
        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
       Log.d("DateTime : ","" + different);

        if(different==0)
        {
             i=new Intent(getApplicationContext(),LiveScoree.class);
            i.putExtra("key",key);
            i.putExtra("Live",live);
            startActivity(i);
            finish();
        }
        else if (different<0)
        {
             i=new Intent(getApplicationContext(),LiveScoree.class);
            i.putExtra("key",key);
            i.putExtra("Live",live);
            startActivity(i);
            finish();
        }
        else if(different<1800000)
        {
            if(CommonMethods.isNetworkAvailable(TestActivity.this)) {
                  new FetchLiveScore().execute();
                timer.start();
            }
            else
            {

            }
        }
        else {
            timer.start();

        }
    }

    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            Intent i=new Intent(getApplicationContext(),LiveScoree.class);
            i.putExtra("key",key);
            i.putExtra("Live",live);
            startActivity(i);
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));//, TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            String sec=String.format("%02d",TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            textViewTime.setText(hms);
            textViewsec.setText(sec);
        }
    }




    private class FetchLiveScore extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

                CommonMethods.showProgressDialog(TestActivity.this);
            }


        @Override
        protected Void doInBackground(String... params) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("match_key", key));
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
                    contacts = jsonObj.getJSONArray("Toqeer");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String toss = c.getString("Vs");
//                        if(toss.equalsIgnoreCase(""))
//                        {
//                            tossinfo.setText("No Toss Yet");
//                        }
//                        else {
                            tossinfo.setText(toss);

                        String teamAsquard=c.getString("TeamASquard");
                        String teamBsquard=c.getString("TeamBSquard");
                        String teamAname=c.getString("teamAName");
                        String teamBname=c.getString("teamBName");
                        teamA.setText(teamAname+" :"+ teamAsquard);
                        teamB.setText(teamBname+" :"+ teamBsquard);
                    }
                    CommonMethods.hideProgressDialog();

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if(status != null)
            // Setting alert dialog icon
            //    alertDialog.setIcon((status) ? R.drawable.icon : R.drawable.icon);

            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

        // Showing Alert Message
        alertDialog.show();
    }
    private void savePreferences(String key, boolean  value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}
