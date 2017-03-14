package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import net.simonvt.menudrawer.MenuDrawer;

import adapter.WorldCupAdapter;

/**
 * Created by TOQEER on 9/15/2015.
 */
public class WorldCup extends FragmentActivity {

   LinearLayout layout;
    Advertise advertise;
    WorldCupAdapter adapter;
    Button live, news, rank, gallery, main,fix,share,venue,player,record,moreapps,worldcup,recent_match;
    private MenuDrawer mDrawer;
    Button btn;
    ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.worldcup);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        advertise=new Advertise();
        layout= (LinearLayout)findViewById(R.id.add);
        advertise.Banner(layout, this);

        main = (Button) findViewById(R.id.home);
        recent_match = (Button) findViewById(R.id.label);
        worldcup=(Button)findViewById(R.id.wc);
        news = (Button) findViewById(R.id.news);
        rank = (Button) findViewById(R.id.rank);
        gallery = (Button) findViewById(R.id.gallery);
        venue = (Button) findViewById(R.id.venue);
        moreapps=(Button)findViewById(R.id.mapps);
        fix = (Button) findViewById(R.id.fixture);
        player=(Button)findViewById(R.id.pprofile);
        live = (Button) findViewById(R.id.lscore);
        record = (Button) findViewById(R.id.record);
        share=(Button)findViewById(R.id.share);
        btn = (Button) findViewById(R.id.title_bar_left_menu);
        // Locate the viewpager in activity_main.xml
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        // Set the ViewPagerAdapter into ViewPager
        viewPager.setAdapter(new WorldCupAdapter(getSupportFragmentManager()));

        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            showAlertDialog(WorldCup.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }

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
                Intent i=new Intent(getApplicationContext(),History.class);
                startActivity(i);
                finish();

            }
        });
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LiveList.class);
                startActivity(i);
                finish();
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Player_Stats.class);
                startActivity(i);
                finish();
            }
        });
      //  record.setVisibility(View.GONE);
        moreapps.setVisibility(View.GONE);
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
            recent_match.setVisibility(View.GONE);
            live.setVisibility(View.GONE);

        }
        else
        {

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
