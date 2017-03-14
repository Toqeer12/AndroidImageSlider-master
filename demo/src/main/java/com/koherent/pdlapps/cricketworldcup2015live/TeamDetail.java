package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import net.simonvt.menudrawer.MenuDrawer;

import adapter.SampleFragmentPagerAdapter;
import adapter.ServiceHandler;
import adapter.TeamAdapter;
import utilities.Constants;

/**
 * Created by T.A on 14/05/2015.
 */
public class TeamDetail extends FragmentActivity {

    TextView text;
    Button btn,back;
    Button fix,news,rank,gallery,venue,live,share,home,player,record,moreapps;
    private MenuDrawer mDrawer;
    String jsonStr;
    Advertise advertise;
    String url= Constants.Team_Ranking_URL;

    ServiceHandler sh;

    public static TeamAdapter adapter;

    ConnectionDetector cd;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.fragment_layout_two);
        mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        text=(TextView)findViewById(R.id.text);
//        revmob = RevMob.startWithListener(this, revmobListener);
//        showBanner();
        layout= (LinearLayout)findViewById(R.id.add);
        advertise = new Advertise();
        advertise.Banner(layout,this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        tabs.setViewPager(pager);

        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            showAlertDialog(TeamDetail.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }
        Intent tn=getIntent();
        String a=tn.getStringExtra("Team");
        Log.d("Team",a);

            back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Ranking.class);
                startActivity(i);
                finish();
            }
        });
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
