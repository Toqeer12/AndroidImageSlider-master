package com.koherent.pdlapps.cricketworldcup2015live;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import adapter.FullCardPagerTestAdapter;
import adapter.PagerAdapterFallofWickets;
import adapter.PagerAdapterFallofWicketsTest;

/**
 * Created by TOQEER on 9/9/2015.
 */
public class FallOFWickets extends FragmentActivity {

    public static PagerSlidingTabStrip tabs;
    public static ViewPager pager;
    public static TextView team1, team2;
    Button back;
    Intent i;
    LinearLayout layout;
    Advertise advertise;
    public static String key, type, test,current,inn,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fall_wickets);
        layout= (LinearLayout)findViewById(R.id.add);
        advertise = new Advertise();
        advertise.Banner(layout, this);
        team1 = (TextView) findViewById(R.id.team1NameScorefallwic);
        team2 = (TextView) findViewById(R.id.team2NameScorefallwic);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        back = (Button) findViewById(R.id.back);
        i = getIntent();
        key = i.getStringExtra("key");
        type = i.getStringExtra("type");
        inn=i.getStringExtra("innings");
        day=i.getStringExtra("day");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
       // String a="engaus_2015_test_01";
       current= key.substring(12, 16);
        Log.d("TestResponse", key.substring(12, 16));
        pager = (ViewPager) findViewById(R.id.viewpagerfallwickets);
        if(inn.equalsIgnoreCase("1"))
        {
            FullCardPagerTestAdapter.PAGE_COUNT=2;
        }
        else
        {
            FullCardPagerTestAdapter.PAGE_COUNT=4;
        }
        if (current.equalsIgnoreCase("test")) {

            pager.setAdapter(new PagerAdapterFallofWicketsTest(getSupportFragmentManager()));

            tabs.setViewPager(pager);
        } else {

            pager.setAdapter(new PagerAdapterFallofWickets(getSupportFragmentManager()));

            tabs.setViewPager(pager);

        }



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
