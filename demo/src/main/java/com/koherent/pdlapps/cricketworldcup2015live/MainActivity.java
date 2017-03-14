package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import net.simonvt.menudrawer.MenuDrawer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import adapter.ServiceHandler;
import utilities.AlertDialogManager;
import utilities.CommonMethods;
import utilities.Constants;


public class MainActivity extends Activity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    double version;

    //Registration of the receivers
    private SliderLayout mDemoSlider;
    AsyncTask<Void, Void, Void> mRegisterTask;

    // Alert dialog manager
    AlertDialogManager alert = new AlertDialogManager();

    // Connection detector
    ConnectionDetector cd;
    Button btn;
    Button fix,news,rank,gallery,venue,live,share,home,player,record,moreapps,recent_match,wcup;
    private MenuDrawer mDrawer;
    TextView tv;
    RelativeLayout lay;
    String jsonStr;
    String url= Constants.Notification;
    ServiceHandler sh;
    String jsonString;
    JSONArray contacts = null;
    public static String wc,sp;
    Advertise advertise;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//

        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
          showAlertDialog(MainActivity.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }


        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.activity_main);
        mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        lay = (RelativeLayout) findViewById(R.id.layout2);
        tv = (TextView) findViewById(R.id.marquee);
        tv.setSelected(true);
        advertise=new Advertise();
        layout= (LinearLayout)findViewById(R.id.add);
        advertise.Banner(layout, this);
        lay.setVisibility(View.GONE);
        recent_match=(Button)findViewById(R.id.label);
        fix = (Button) findViewById(R.id.fixture);
        wcup = (Button) findViewById(R.id.wc);
        record = (Button) findViewById(R.id.record);
        news = (Button) findViewById(R.id.news);
        rank = (Button) findViewById(R.id.rank);
        gallery = (Button) findViewById(R.id.gallery);
        venue = (Button) findViewById(R.id.venue);
        live = (Button) findViewById(R.id.lscore);
        share = (Button) findViewById(R.id.share);
        home = (Button) findViewById(R.id.home);
        moreapps = (Button) findViewById(R.id.mapps);
        player = (Button) findViewById(R.id.pprofile);
        btn = (Button) findViewById(R.id.title_bar_left_menu);

        recent_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Recent_Result.class);
                startActivity(i);
                finish();
            }
        });
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                  home.setBackground(getDrawable(R.drawable.home_pressed));
                Intent i = new Intent(getApplicationContext(), History.class);
                startActivity(i);
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openMenu();

            }
        });
        moreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MoreApps.class);
                startActivity(i);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                  home.setBackground(getDrawable(R.drawable.home_pressed));
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Player_Stats.class);
                startActivity(i);
                finish();
            }
        });
        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Fixture.class);
                startActivity(i);
                finish();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareBody = "https://play.google.com/store/apps/details?id=com.koherent.pdlapps.cricketworldcup2015live&hl=en";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "CricToday App");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.CricDroid)));
            }
        });
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LiveList.class);
                savePreferences("add",false);
                startActivity(i);
                finish();
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), News.class);
                startActivity(i);
                finish();
            }
        });
        wcup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), WorldCup.class);
                startActivity(i);
                finish();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PictureGallery.class);
                startActivity(i);
                finish();
            }
        });
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Ranking.class);
                startActivity(i);
                finish();
            }
        });
        venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Venues.class);
                startActivity(i);
                finish();
            }
        });
        moreapps.setVisibility(View.GONE);
        record.setVisibility(View.GONE);
        wcup.setVisibility(View.GONE);

        if(CommonMethods.isNetworkAvailable(MainActivity.this)) {
            new MainGet().execute();
        }
        else {
//            if (!cd.isConnectingToInternet()) {
//                // Internet Connection is not present
//                alert.showAlertDialog(MainActivity.this,
//                        "Internet Connection Error",
//                        "Please connect to working Internet connection", false);
//                // stop executing code by return
//                return;
//            }
        }
        HashMap<String, Integer> url_maps = new HashMap<String, Integer>();

        url_maps.put("LIVE SCORES", R.drawable.live);
        url_maps.put("FIXTURES", R.drawable.fixtures);
        url_maps.put("RECENT MATCHES", R.drawable.recent_matches);
        url_maps.put("RANKING", R.drawable.rankings);
        url_maps.put("HISTORY", R.drawable.historys);
        url_maps.put("NEWS", R.drawable.newss);
        url_maps.put("VENUES", R.drawable.venues);
        url_maps.put("PICTURE GALLERY", R.drawable.picture_gallery);

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        // registerReceivers();



        //Register for push!

    }


    /**
     * Will check main Activity intent and if it contains any PushWoosh data, will clear it
     */

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
//        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
   //     Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
        if(slider.getBundle().get("extra").equals("HISTORY"))
        {
            Intent i=new Intent(getApplicationContext(),History.class);
            startActivity(i);

        }
        else if(slider.getBundle().get("extra").equals("RANKING"))
        {
            Intent i=new Intent(getApplicationContext(),Ranking.class);
            startActivity(i);

        }
        else if(slider.getBundle().get("extra").equals("NEWS"))
        {
            Intent i=new Intent(getApplicationContext(),News.class);
            startActivity(i);

        }
        else if(slider.getBundle().get("extra").equals("PICTURE GALLERY"))
        {
            Intent i=new Intent(getApplicationContext(),PictureGallery.class);
            startActivity(i);

        }
        else if(slider.getBundle().get("extra").equals("VENUES"))
        {
            Intent i=new Intent(getApplicationContext(),Venues.class);
            startActivity(i);

        }
        else if(slider.getBundle().get("extra").equals("RECENT MATCHES"))
        {
            Intent i=new Intent(getApplicationContext(),Recent_Result.class);
            startActivity(i);

        }
        else if(slider.getBundle().get("extra").equals("FIXTURES"))
        {
            Intent i=new Intent(getApplicationContext(),Fixture.class);
            startActivity(i);

        }
        else if(slider.getBundle().get("extra").equals("LIVE SCORES"))
        {
            Intent i=new Intent(getApplicationContext(),LiveList.class);
            startActivity(i);
        }
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void onBackPressed() {


        finish();
    }

    private class MainGet extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog


            CommonMethods.showProgressDialog(MainActivity.this);

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            sh = new ServiceHandler();

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("REsponse", "" + jsonStr);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            CommonMethods.hideProgressDialog();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray("Toqeer");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String status=c.getString("Status");
                        String text = c.getString("Text");
                        String tab=c.getString("Tab");
                        wc= c.getString("Wc");
                        sp=c.getString("Sp");
                        String pg=c.getString("PG");

                        wcup.setText(tab.toString());
                        if(pg.equalsIgnoreCase("true"))
                        {savePreferences("pg",true);
                            gallery.setVisibility(View.GONE);
                        }
                                                else
                        {savePreferences("pg",false);
                            gallery.setVisibility(View.VISIBLE);
                        }
                        if(wc.equalsIgnoreCase("true"))
                        {   savePreferences("wc",true);

                            wcup.setVisibility(View.VISIBLE);

                            fix.setVisibility(View.GONE);
                            recent_match.setVisibility(View.GONE);
                            live.setVisibility(View.GONE);

                        }
                        else
                        {
                            savePreferences("wc",false);

                            wcup.setVisibility(View.GONE);
                            fix.setVisibility(View.VISIBLE);
                            recent_match.setVisibility(View.VISIBLE);
                            live.setVisibility(View.VISIBLE);


                        }

                        if(sp.equalsIgnoreCase("true"))
                        {
                            savePreferences("sp",true);

                            fix.setVisibility(View.VISIBLE);
                            recent_match.setVisibility(View.VISIBLE);
                            live.setVisibility(View.VISIBLE);
                        }
                        else {
                            savePreferences("sp",false);
                            fix.setVisibility(View.GONE);
                            recent_match.setVisibility(View.GONE);
                            live.setVisibility(View.GONE);
                        }

                       version=c.getDouble("versioncode");
                        Log.d("Version",""+version);
                        if(!(version < 50) )
                        {
                            lay.setVisibility(View.VISIBLE);
                            tv.setText(text);
                            tv.setSelected(true);
                        }


                    }
                    Log.d("REsponse", "" + jsonStr);
                }

                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
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
