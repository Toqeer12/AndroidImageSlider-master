package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import net.simonvt.menudrawer.MenuDrawer;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import ImageDownloader.ImageLoader;
import Model.newsitem;
import adapter.VenueAdapter;
import utilities.AlertDialogManager;

/**
 * Created by T.A on 16/05/2015.
 */
public class Venues extends Activity {
    GridView gridView;
    ArrayList<newsitem> newsobject;
    NodeList nodelist;
    ProgressDialog pDialog;
    private ListView listView;
    String uae="http://pdlapps.com/crictoday/uae.php";
    String aus="http://pdlapps.com/crictoday/aus.php";
    String pak="http://pdlapps.com/crictoday/pak.php";
    String ban="http://pdlapps.com/crictoday/Ban.php";
    String wi="http://pdlapps.com/crictoday/wi.php";
    String sa="http://pdlapps.com/crictoday/sa.php";
    String nz="http://pdlapps.com/crictoday/nz.php";
    String sl="http://pdlapps.com/crictoday/sl.php";
    String zim="http://pdlapps.com/crictoday/zim.php";
    String eng="http://pdlapps.com/crictoday/eng.php";
    String in="http://pdlapps.com/crictoday/in.php";
    AlertDialogManager alert = new AlertDialogManager();
    private MenuDrawer mDrawer;
    Button btn;
    Button live, news, rank, gallery, main,fix,share,venue,player,record,moreapps,worldcup,recent_match;
    private ImageLoader imageLoader;
    List<Integer> image;
    List<String> country;
    VenueAdapter adapter;
    ConnectionDetector cd;
    LinearLayout layout;
    Advertise advertise;
//    private RevMobAdsListener revmobListener;
    private static String APPLICATION_ID = "55f2c11dcb19f0d50c2f3462";

//    private RevMob revmob;
//    RevMobAdsListener revmobListener = new RevMobAdsListener() {
//
//        // Required
//        @Override
//        public void onRevMobSessionIsStarted() {
//            Log.d("[Toqeer", "RevMob session is started");
//
//        }
//
//        @Override
//        public void onRevMobSessionNotStarted(String message) {
//            Log.d("Toqeer", "RevMob session failed to start");
//        }
//
//        // Optional
//        @Override
//        public void onRevMobAdDisplayed() {
//            Log.d("Toqeer", "onAdDisplayed");
//        }
//
//        @Override
//        public void onRevMobAdReceived() {
//            Log.d("Toqeer", "onAdReceived");
//        }
//
//        @Override
//        public void onRevMobAdNotReceived(String message) {
//            Log.d("Toqeer", "onAdNotReceived");
//        }
//
//        @Override
//        public void onRevMobAdDismissed() {
//            Log.d("Toqeer", "onAdDismissed");
//        }
//
//        @Override
//        public void onRevMobAdClicked() {
//            Log.d("Toqeer", "onAdClicked");
//        }
//    };
//
//
//
//    void showBanner() {
//        final RevMobBanner banner = revmob.createBanner(this, revmobListener);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("Toqeer","Inthread");
//                ViewGroup view = (ViewGroup) findViewById(R.id.add);
//                view.addView(banner);
//            }
//        });
//    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        image = new ArrayList<Integer>();
        country = new ArrayList<String>();
        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.venue);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        listView = (ListView) findViewById(R.id.venueView);

        advertise = new Advertise();
        layout= (LinearLayout)findViewById(R.id.add);
        advertise.Banner(layout,this);
        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            showAlertDialog(Venues.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }

        main = (Button) findViewById(R.id.home);
        recent_match = (Button) findViewById(R.id.label);
        worldcup = (Button) findViewById(R.id.wc);
        news = (Button) findViewById(R.id.news);
        rank = (Button) findViewById(R.id.rank);
        gallery = (Button) findViewById(R.id.gallery);
        venue = (Button) findViewById(R.id.venue);
        moreapps = (Button) findViewById(R.id.mapps);
        fix = (Button) findViewById(R.id.fixture);
        player = (Button) findViewById(R.id.pprofile);
        live = (Button) findViewById(R.id.lscore);
        record = (Button) findViewById(R.id.record);
        share = (Button) findViewById(R.id.share);
        btn = (Button) findViewById(R.id.title_bar_left_menu);
        image.add(R.drawable.aus_small);
        country.add("Australia");
        image.add(R.drawable.ban_small);
        country.add("Bangladesh");
        image.add(R.drawable.eng_small);
        country.add("England");
        image.add(R.drawable.ind_small);
        country.add("India");
        image.add(R.drawable.nz_small);
        country.add("New Zealand");
        image.add(R.drawable.pak_small);
        country.add("Pakistan");
        image.add(R.drawable.sa_small);
        country.add("South Africa");
        image.add(R.drawable.sl_small);
        country.add("Sri Lanka");
        image.add(R.drawable.uae_small);
        country.add("UAE");
        image.add(R.drawable.zim_smal);
        country.add("Zimbabwe");
        record.setVisibility(View.GONE);
        adapter = new VenueAdapter(getApplicationContext(), image, country);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                if (arg2 == 0) {
                    Intent i = new Intent(getApplicationContext(), VenueDetail.class);
                    i.putExtra("url", aus);
                    startActivity(i);
                } else if (arg2 == 1) {
                    Intent i = new Intent(getApplicationContext(), VenueDetail.class);
                    i.putExtra("url", ban);
                    startActivity(i);
                } else if (arg2 == 2) {
                    Intent i = new Intent(getApplicationContext(), VenueDetail.class);
                    i.putExtra("url", eng);
                    startActivity(i);
                } else if (arg2 == 3) {
                    Intent i = new Intent(getApplicationContext(), VenueDetail.class);
                    i.putExtra("url", in);
                    startActivity(i);
                } else if (arg2 == 4) {
                    Intent i = new Intent(getApplicationContext(), VenueDetail.class);
                    i.putExtra("url", nz);
                    startActivity(i);
                } else if (arg2 == 5) {
                    Intent i = new Intent(getApplicationContext(), VenueDetail.class);
                    i.putExtra("url", pak);
                    startActivity(i);
                } else if (arg2 == 6) {
                    Intent i = new Intent(getApplicationContext(), VenueDetail.class);
                    i.putExtra("url", sa);
                    startActivity(i);
                } else if (arg2 == 7) {
                    Intent i = new Intent(getApplicationContext(), VenueDetail.class);
                    i.putExtra("url", sl);
                    startActivity(i);
                } else if (arg2 == 8) {
                    Intent i = new Intent(getApplicationContext(), VenueDetail.class);
                    i.putExtra("url", uae);
                    startActivity(i);
                } else if (arg2 == 9) {
                    Intent i = new Intent(getApplicationContext(), VenueDetail.class);
                    i.putExtra("url", zim);
                    startActivity(i);
                }


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
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LiveList.class);
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
      //  record.setVisibility(View.GONE);
        venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Venues.class);
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
        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Fixture.class);
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
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openMenu();

            }
        });
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
        if (checkBoxValue1) {
            worldcup.setVisibility(View.VISIBLE);

            fix.setVisibility(View.GONE);
            recent_match.setVisibility(View.GONE);
            live.setVisibility(View.GONE);

        } else {

            worldcup.setVisibility(View.GONE);
            fix.setVisibility(View.VISIBLE);
            recent_match.setVisibility(View.VISIBLE);
            live.setVisibility(View.VISIBLE);


        }
        if(checkBoxValue2)
        {

            fix.setVisibility(View.VISIBLE);
            recent_match.setVisibility(View.VISIBLE);
            live.setVisibility(View.VISIBLE);
        }
        else {
            fix.setVisibility(View.GONE);
            recent_match.setVisibility(View.GONE);
            live.setVisibility(View.GONE);
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
