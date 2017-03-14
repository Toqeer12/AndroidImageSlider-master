package com.koherent.pdlapps.cricketworldcup2015live;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import net.simonvt.menudrawer.MenuDrawer;

import adapter.FullCardPagerAdapter;
import adapter.FullCardPagerTestAdapter;

/**
 * Created by TOQEER on 8/28/2015.
 */
public class FullCardTesting extends FragmentActivity {
    private MenuDrawer mDrawer;
   public static PagerSlidingTabStrip tabs;
    public static ViewPager pager;
    public static TextView team1, team2,dayday,breakk,runst,winstr,man;
    Intent i;
    public static String key, type, test,current,inn,day,a;
    Button back, hud,refresh;

    Advertise advertise;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullcard);
        team1 = (TextView) findViewById(R.id.team1NameScore);
        team2 = (TextView) findViewById(R.id.team2NameScore);
        dayday = (TextView) findViewById(R.id.day);
        runst = (TextView) findViewById(R.id.runstr);
        breakk = (TextView) findViewById(R.id.breakk);
        winstr = (TextView) findViewById(R.id.winstr);
        back = (Button) findViewById(R.id.back);
        refresh=(Button) findViewById(R.id.refresh);
        hud = (Button) findViewById(R.id.menu);
        man=(TextView)findViewById(R.id.man);
        layout= (LinearLayout)findViewById(R.id.add);
        advertise = new Advertise();
        advertise.Banner(layout, this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        i = getIntent();
        key = i.getStringExtra("key");
        type = i.getStringExtra("type");
        inn=i.getStringExtra("innings");
        day=i.getStringExtra("day");
        if(day.equalsIgnoreCase("0"))
        {
            dayday.setVisibility(View.GONE);
        }
        else{
            dayday.setText("Day: "+day);
        }


        if(LiveScoree.breakreason.equalsIgnoreCase("NO")) {
            breakk.setVisibility(View.GONE);
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("drinks_break")) {
            breakk.setText("Status: " + "Drink Break");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("innings_break"))
        {
            breakk.setText("Status: " + "Innings Break");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("scheduled")) {
            breakk.setText("Status: " + "Scheduled");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("lunch_break"))
        {
            breakk.setText("Status: " + "Lunch Break");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("in_play"))
        {
            breakk.setText("Status: " + "Play");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("rain_delay"))
        {
            breakk.setText("Status: " + "Rain Delay");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("stumps"))
        {
            breakk.setText("Status: " + "Stumps");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("bad_light")) {
            breakk.setText("Status: " + "Bad Lights");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("crowd_trouble")) {
            breakk.setText("Status: " + "Crowd Trouble");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("bad_pitch_condition")) {
            breakk.setText("Status: " + "Bad Pitch");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("player_injured")) {
            breakk.setText("Status: " + "Player Injured");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("floodlight_failure")) {
            breakk.setText("Status: " + "Flood Light Failure");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("ball_change"))
        {
            breakk.setText("Status: " + "Ball Change");
        }
        else if(LiveScoree.breakreason.equalsIgnoreCase("start_delayed"))
        {
            breakk.setText("Status: " + "Match delayed by a wet outfield");
        }
        else
        {

            breakk.setVisibility(View.GONE);
        }

        if(LiveScoree.mom.equalsIgnoreCase("No"))
        {
            man.setVisibility(View.GONE);
        }
        else
        {
            man.setText("Man of the Match: "+LiveScoree.mom);
        }
        runst.setText(LiveScoree.runstr);


        winstr.setText(LiveScoree.info);
        if(inn.equalsIgnoreCase("1"))
        {
            FullCardPagerTestAdapter.PAGE_COUNT=2;
        }
        else {
            FullCardPagerTestAdapter.PAGE_COUNT=4;
        }



        current= key.substring(12, 16);
        Log.d("KeValue",current+inn);
        pager = (ViewPager) findViewById(R.id.pager);
        test = "test";
        if (current.equalsIgnoreCase("test")) {

            pager.setAdapter(new FullCardPagerTestAdapter(getSupportFragmentManager()));
            tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
            tabs.setViewPager(pager);
        } else {

            pager.setAdapter(new FullCardPagerAdapter(getSupportFragmentManager()));
             tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
            tabs.setViewPager(pager);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LiveScoree.class);
                i.putExtra("key", key);
                i.putExtra("Live", type);
                i.putExtra("innings",inn);
                i.putExtra("day",day);
                startActivity(i);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i=new Intent(getApplicationContext(),FullCardTesting.class);
                i.putExtra("key", key);
                i.putExtra("type", type);
                i.putExtra("innings",inn);
                i.putExtra("day",day);
                startActivity(i);
                finish();
            }
        });


        hud.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(FullCardTesting.this, hud);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.four)
                        {
                           Intent i = new Intent(getApplicationContext(), HUDActivity.class);

                                i.putExtra("key", key);
                                i.putExtra("type", type);
                                i.putExtra("innings",inn);
                                  i.putExtra("day",day);
                                startActivity(i);
                                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                                finish();
                        }
                        else if(item.getItemId()==R.id.fallofwickets)
                        {

                            Intent i = new Intent(getApplicationContext(), FallOFWickets.class);
                            i.putExtra("key", key);
                            i.putExtra("type", type);
                            i.putExtra("innings",inn);
                            i.putExtra("day",day);
                            startActivity(i);
                            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                            finish();
                        }

                        else if(item.getItemId()==R.id.three)
                        {

                            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                            i.putExtra("key", key);
                            i.putExtra("type", type);
                            i.putExtra("innings",inn);
                            i.putExtra("day",day);

                             startActivity(i);
                            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                            finish();
                        }
                        else if(item.getItemId()==R.id.two)
                        {
                            Intent i = new Intent(getApplicationContext(), OverByOver.class);
                            i.putExtra("key", key);
                            i.putExtra("type", type);
                            i.putExtra("innings",inn);

                            i.putExtra("day",day);
                            startActivity(i);
                            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                          //  finish();
                        }
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });
    }
}