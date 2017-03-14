package com.koherent.pdlapps.cricketworldcup2015live;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import org.json.JSONArray;

import adapter.OverByOverAdapter;
import adapter.OverByOverPagerAdapter;

/**
 * Created by TOQEER on 9/14/2015.
 */
public class OverByOver extends FragmentActivity {
    String jsonStr;
    public String url;
    JSONArray contacts = null;
    String match_key;
    public static PagerSlidingTabStrip tabs,tabs2;
    public static ViewPager pager;

    Button back,refresh;
    Intent i;
    public static String key, type, test,current,inn,day;
    Advertise advertise;
    LinearLayout layout;
    public static TextView team1, team2,dayday,breakk,runst,winstr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overbyover);
        pager = (ViewPager) findViewById(R.id.viewpager2);
        back=(Button)findViewById(R.id.back);
        team1 = (TextView) findViewById(R.id.team1NameScore);
        team2 = (TextView) findViewById(R.id.team2NameScore);
        refresh=(Button)findViewById(R.id.refresh);





        layout= (LinearLayout)findViewById(R.id.add);
        advertise = new Advertise();
        advertise.Banner(layout, this);
        i = getIntent();
        key = i.getStringExtra("key");
        type = i.getStringExtra("type");
        inn=i.getStringExtra("innings");
        day=i.getStringExtra("day");
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs2);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if(inn.equalsIgnoreCase("1"))
        {
            OverByOverPagerAdapter.PAGE_COUNT=2;
        }
        else {
            OverByOverPagerAdapter.PAGE_COUNT=4;
        }
        current= key.substring(12, 16);
        if (current.equalsIgnoreCase("test")) {

            pager.setAdapter(new OverByOverPagerAdapter(getSupportFragmentManager()));

            tabs.setViewPager(pager);
        } else {

            pager.setAdapter(new OverByOverAdapter(getSupportFragmentManager()));

            tabs.setViewPager(pager);
        }




        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), OverByOver.class);
                i.putExtra("key", key);
                i.putExtra("type", type);
                i.putExtra("innings",inn);

                i.putExtra("day",day);
                startActivity(i);
                finish();
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FullCardTesting.class);
                i.putExtra("key", key);
                i.putExtra("type", type);
                i.putExtra("innings",inn);
                i.putExtra("day",day);
                startActivity(i);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });

    }
}
