package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.ListView;

import com.daimajia.slider.library.SliderLayout;

import net.simonvt.menudrawer.MenuDrawer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import ImageDownloader.ImageLoader;
import Model.newsitem;
import adapter.NewsAdapter;
import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by T.A on 13/05/2015.
 */
public class News extends Activity  {



    private static final String NEWS_KEY = "news";
    NewsAdapter adapter;
    ConnectionDetector cd;
    ArrayList<newsitem> newsobject;
    NodeList nodelist;
    ProgressDialog pDialog;
    private ListView listView;
    String url = Constants.News_URL;

    String jsonStr;
    JSONArray arr = null;
    private MenuDrawer mDrawer;
    JSONArray contacts = null;
    ServiceHandler sh;
    private SliderLayout mDemoSlider;
    Button btn;
    Button fix,news,rank,gallery,main,venue,live,share,player,record,moreapps,worldcup,recent_result,history;
    private ImageLoader imageLoader;
    Advertise advertise;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        imageLoader = new ImageLoader(this);
        imageLoader.clearCache();
        newsobject = new ArrayList<newsitem>();
        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.news);
        mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        layout= (LinearLayout)findViewById(R.id.add);
        advertise = new Advertise();
        advertise.Banner(layout, this);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        mDemoSlider.setVisibility(View.GONE);
        listView = (ListView)findViewById(R.id.listView);
        fix = (Button) findViewById(R.id.fixture);
        news = (Button) findViewById(R.id.news);
        player=(Button)findViewById(R.id.pprofile);
        rank = (Button) findViewById(R.id.rank);
        moreapps=(Button)findViewById(R.id.mapps);
        gallery=(Button)findViewById(R.id.gallery);
        record=(Button)findViewById(R.id.record);
        venue=(Button)findViewById(R.id.venue);
        main=(Button)findViewById(R.id.home);
        live=(Button)findViewById(R.id.lscore);
        news=(Button)findViewById(R.id.news);
        share=(Button)findViewById(R.id.share);
        worldcup=(Button)findViewById(R.id.wc);
        recent_result=(Button)findViewById(R.id.label);
        btn = (Button) findViewById(R.id.title_bar_left_menu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mDrawer.openMenu();

            }
        });
        venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Venues.class);
                startActivity(i);

            }
        });
        recent_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Recent_Result.class);
                startActivity(i);
                finish();
            }
        });
        moreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MoreApps.class);
                startActivity(i);
            }
        });

        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                  home.setBackground(getDrawable(R.drawable.home_pressed));
                Intent i=new Intent(getApplicationContext(),History.class);
                startActivity(i);
                finish();

            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),News.class);
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
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Ranking.class);
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
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PictureGallery.class);
                startActivity(i);
                finish();


            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
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
        worldcup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), WorldCup.class);
                startActivity(i);
                finish();

            }
        });
        if(CommonMethods.isNetworkAvailable(News.this)) {
            new GetNews().execute();
        }
        else
        {
            cd = new ConnectionDetector(getApplicationContext());

            // Check if Internet present
            if (!cd.isConnectingToInternet()) {
                // Internet Connection is not present
                showAlertDialog(News.this,
                        "Internet Connection Error",
                        "Please connect to working Internet connection", false);
                // stop executing code by return
                return;
            }


      }
        record.setVisibility(View.GONE);

     //   record.setVisibility(View.GONE);
        moreapps.setVisibility(View.GONE);
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        final boolean checkBoxValue1 = sharedPreferences.getBoolean("wc", false);
        final boolean checkBoxValue2 = sharedPreferences.getBoolean("sp", false);
        final boolean checkBoxValue3 = sharedPreferences.getBoolean("pg", false);
        if(checkBoxValue3)
        {
            gallery.setVisibility(View.GONE);
        }
        else
        {
            gallery.setVisibility(View.VISIBLE);
        }
        if(checkBoxValue1)
        {
            worldcup.setVisibility(View.VISIBLE);

            fix.setVisibility(View.GONE);
            recent_result.setVisibility(View.GONE);
            live.setVisibility(View.GONE);

        }
        else
        {

            worldcup.setVisibility(View.GONE);
           // record.setVisibility(View.VISIBLE);
            fix.setVisibility(View.VISIBLE);
            recent_result.setVisibility(View.VISIBLE);
            live.setVisibility(View.VISIBLE);


        }

        if(checkBoxValue2)
        {

            fix.setVisibility(View.VISIBLE);
            recent_result.setVisibility(View.VISIBLE);
            live.setVisibility(View.VISIBLE);
        }
        else {
            fix.setVisibility(View.GONE);
            recent_result.setVisibility(View.GONE);
            live.setVisibility(View.GONE);
        }

    }



    private class GetNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog


            CommonMethods.showProgressDialog(News.this);

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            sh = new ServiceHandler();

            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);


            Log.d("response",jsonStr);
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

                        String title = c.getString("Title");
                        String desc = c.getString("Description");
                        String image_url = c.getString("Image_Url");

                        // Phone node is JSON Object
                        Log.d("response",title+desc+image_url);
                        newsobject.add(new newsitem(title, desc, image_url));

                        adapter=new NewsAdapter(getApplicationContext(),newsobject);
                        listView.setAdapter(adapter);

/*
                        HashMap<String, String> url_maps = new HashMap<String, String>();
                        url_maps.put(title,image_url);

                        for(String name : url_maps.keySet()){
                            TextSliderView textSliderView = new TextSliderView(News.this);
                            // initialize a SliderLayout
                            textSliderView
                                    .description(name)
                                    .image(url_maps.get(name))
                                    .setScaleType(BaseSliderView.ScaleType.Fit);

                            //add your extra information
                            textSliderView.bundle(new Bundle());
                            textSliderView.getBundle()
                                    .putString("extra",name);

                            mDemoSlider.addSlider(textSliderView);
                        }
                        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
                        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                        mDemoSlider.setDuration(4000);*/

                        CommonMethods.hideProgressDialog();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();


                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
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
}
