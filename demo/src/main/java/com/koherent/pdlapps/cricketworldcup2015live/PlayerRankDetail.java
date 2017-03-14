package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;

import net.simonvt.menudrawer.MenuDrawer;

import org.json.JSONArray;

import java.util.ArrayList;

import Model.Playeritem;
import adapter.PlayerAdapter;
import adapter.PlayerFragmentPagerAdapter;
import adapter.ServiceHandler;
import utilities.Constants;


/**
 * Created by T.A on 18/05/2015.
 */
public class PlayerRankDetail extends FragmentActivity {


    Button btn,back;
    Button fix,news,rank,gallery,venue,live,share,home,player,record,moreapps;
    private MenuDrawer mDrawer;
    String jsonStr;
    JSONArray arr = null;
    private ProgressDialog pDialog;
    public static String url;
    JSONArray Test = null;
    JSONArray ODI = null;
    JSONArray T20 = null;
    LinearLayout layout;
    Advertise advertise;
    ServiceHandler sh;
    public static ArrayList<Playeritem> itemobject;

    public static PlayerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.playerankdetail);
        mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(new PlayerFragmentPagerAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        advertise=new Advertise();
        layout= (LinearLayout)findViewById(R.id.add);
        advertise.Banner(layout, this);
        Intent i=getIntent();
        String a= i.getStringExtra("Team");
        if (a.equalsIgnoreCase("Batting"))
        {
            url=Constants.Bating_Ranking_URL;
           // Toast.makeText(getApplicationContext(), Constants.Bating_Ranking_URL,Toast.LENGTH_LONG).show();
        }
        else if(a.equalsIgnoreCase("Bowling"))
        {url=Constants.Bowling_Ranking_URL;
          //  Toast.makeText(getApplicationContext(), Constants.Bowling_Ranking_URL,Toast.LENGTH_LONG).show();

        }
        else
        {
            url=Constants.AllRounder_Ranking_URL;
           // Toast.makeText(getApplicationContext(), Constants.AllRounder_Ranking_URL, Toast.LENGTH_LONG).show();

        }
        moreapps=(Button)findViewById(R.id.mapps);
        fix = (Button) findViewById(R.id.fixture);
        news = (Button) findViewById(R.id.news);
        rank = (Button) findViewById(R.id.rank);
        gallery=(Button)findViewById(R.id.gallery);
        record=(Button)findViewById(R.id.record);
        player=(Button)findViewById(R.id.pprofile);
        venue=(Button)findViewById(R.id.venue);
        home=(Button)findViewById(R.id.home);
        live=(Button)findViewById(R.id.lscore);
        news=(Button)findViewById(R.id.news);
        share=(Button)findViewById(R.id.share);
        back=(Button)findViewById(R.id.back);

        moreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MoreApps.class);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                  home.setBackground(getDrawable(R.drawable.home_pressed));
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
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
        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Fixture.class);
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
                Intent i=new Intent(getApplicationContext(),LiveList.class);
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
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),PictureGallery.class);
                startActivity(i);
                finish();
            }
        });
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Ranking.class);
                startActivity(i);
                finish();
            }
        });
        venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Venues.class);
                startActivity(i);
                finish();
            }
        });
    }


}
