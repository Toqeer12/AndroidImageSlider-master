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
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.simonvt.menudrawer.MenuDrawer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import Model.galleryItem;
import adapter.GalleryAdapter;
import adapter.ServiceHandler;
import utilities.CommonMethods;
import utilities.Constants;

/**
 * Created by T.A on 14/05/2015.
 */
public class PictureGallery extends Activity {



    private static final String GALLERY_KEY = "gallery";
    Button btn;
    Button fix, news,main,rank,venue,live,share,gallery,player,record,moreapps,worldcup,recent_result;
    private MenuDrawer mDrawer;
    JSONArray contacts = null;
    ArrayList<galleryItem> galleryobject ;
    String url = Constants.Gallery_Pic_URL;


    private ListView listView;
    GalleryAdapter adapter;
    String jsonStr;
    ConnectionDetector cd;
    ServiceHandler sh;
    LinearLayout layout;
    Advertise advertise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        galleryobject = new ArrayList<galleryItem>();
        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            showAlertDialog(PictureGallery.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }

        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.picturegallery);
        mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        listView = (ListView)findViewById(R.id.listView);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        layout= (LinearLayout)findViewById(R.id.add);
        advertise = new Advertise();
        advertise.Banner(layout, this);
        fix = (Button) findViewById(R.id.fixture);
        news = (Button) findViewById(R.id.news);
        main=(Button)findViewById(R.id.home);
        rank=(Button)findViewById(R.id.rank);
        recent_result=(Button)findViewById(R.id.label);
        record=(Button)findViewById(R.id.record);
        venue=(Button)findViewById(R.id.venue);
        moreapps=(Button)findViewById(R.id.mapps);
        live=(Button)findViewById(R.id.lscore);
        share=(Button)findViewById(R.id.share);
        gallery=(Button)findViewById(R.id.gallery);
        btn = (Button) findViewById(R.id.title_bar_left_menu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openMenu();

            }
        });
        player=(Button)findViewById(R.id.pprofile);
        worldcup=(Button)findViewById(R.id.wc);
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                  home.setBackground(getDrawable(R.drawable.home_pressed));
                Intent i=new Intent(getApplicationContext(),History.class);
                startActivity(i);
                finish();

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
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Player_Stats.class);
                startActivity(i);
                finish();


            }
        });

        record.setVisibility(View.GONE);        moreapps.setVisibility(View.GONE);

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PictureGallery.class);
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
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), News.class);
                startActivity(i);
                finish();

            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
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
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), LiveList.class);
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
        if(CommonMethods.isNetworkAvailable(PictureGallery.this)) {
            new GetGallery().execute();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(PictureGallery.this);
            Gson gson = new Gson();
            String json1 = sharedPrefs.getString(GALLERY_KEY, null);
            //String json2 = sharedPrefs.getString(LIST_2_KEY, null);
            Type type = new TypeToken<ArrayList<galleryItem>>() {}.getType();
            galleryobject = gson.fromJson(json1, type);

            adapter = new GalleryAdapter(PictureGallery.this, galleryobject);
            listView.setAdapter(adapter);
            //battingTeamTwoList = gson.fromJson(json2, type);
        }
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

    private class GetGallery extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressbar
            CommonMethods.showProgressDialog(PictureGallery.this);

        }

        @Override
        protected Void doInBackground(String... Url) {
            try {
                sh = new ServiceHandler();

                // Making a request to url and getting response
                jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
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

                    contacts = jsonObj.getJSONArray("Toqeer");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String titlee = c.getString("Title");
                        String image_Ur = c.getString("Image_Url");

                        galleryobject.add(new galleryItem(titlee, image_Ur));
                        adapter = new GalleryAdapter(PictureGallery.this, galleryobject);
                        listView.setAdapter(adapter);


                    }

                }
                catch (Exception ex)
                {


                }


            }
            // Close progressbar
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