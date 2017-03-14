package com.koherent.pdlapps.cricketworldcup2015live;


import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import adapter.FullCardPagerAdapter;

/**
 * Created by TOQEER on 8/26/2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class demo extends Activity {
    ViewPager Tab;
    FullCardPagerAdapter TabAdapter;
    ActionBar actionBar;
    ViewPager viewPager;
    private String[] tabs = { "Top Rated", "Games"};
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixturedemo);


//         viewPager = (ViewPager) findViewById(R.id.pager);
//        actionBar = getActionBar();
//        TabAdapter = new FullCardPagerAdapter(getSupportFragmentManager());
//
//        viewPager.setAdapter(TabAdapter);
//
//        actionBar.hide();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//
//        // Adding Tabs
//        for (String tab_name : tabs) {
//            actionBar.addTab(actionBar.newTab().setText(tab_name)
//                    .setTabListener(this));
//        }
//
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int position) {
//                // on changing the page
//                // make respected tab selected
//                actionBar.setSelectedNavigationItem(position);
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//            }
//        });
//    }
//
//    @Override
//    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//        viewPager.setCurrentItem(tab.getPosition());
//    }
//
//    @Override
//    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//    }
//
//    @Override
//    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
    }
}