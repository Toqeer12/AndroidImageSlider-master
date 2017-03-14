package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import net.simonvt.menudrawer.MenuDrawer;

import java.util.ArrayList;
import java.util.List;

import adapter.RankingContentAdapter;
import utilities.Constants;


/**
 * Created by T.A on 14/05/2015.
 */
public class Ranking extends Activity {



    Button btn;
    Button fix,news,main,gallery,venue,live,share,rank,player,record,moreapps,worldcup,recent_result;
    ImageView testmach,odi,twenty;
    private MenuDrawer mDrawer;
    ConnectionDetector cd;
    LinearLayout layout;
    Advertise advertise;
    RankingContentAdapter adapter;
    List<String> rankingobject;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawer = MenuDrawer.attach(this);
        mDrawer.setContentView(R.layout.ranking);
        mDrawer.setMenuView(R.layout.activity_behind_left_simple);
        rankingobject = new ArrayList<String>();
        rankingobject.add("Top Teams Ranking");
        rankingobject.add("Top Batsman Ranking");
        rankingobject.add("Top Bowler Ranking");
        rankingobject.add("Top Allrounder Ranking");
        advertise=new Advertise();
        layout= (LinearLayout)findViewById(R.id.add);
        advertise.Banner(layout, this);


        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            showAlertDialog(Ranking.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        listView=(ListView)findViewById(R.id.searchlist);


        moreapps=(Button)findViewById(R.id.mapps);
        fix=(Button)findViewById(R.id.fixture);
        news=(Button)findViewById(R.id.news);
        recent_result=(Button)findViewById(R.id.label);
        gallery=(Button)findViewById(R.id.gallery);
        rank=(Button)findViewById(R.id.rank);
        main=(Button)findViewById(R.id.home);
        venue=(Button)findViewById(R.id.venue);
        live=(Button)findViewById(R.id.lscore);
        record=(Button)findViewById(R.id.record);
        player=(Button)findViewById(R.id.pprofile);
        share=(Button)findViewById(R.id.share);
        worldcup=(Button)findViewById(R.id.wc);

        btn=(Button)findViewById(R.id.title_bar_left_menu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             mDrawer.openMenu();
            }
        });
        recent_result.setOnClickListener(new View.OnClickListener() {
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
                Intent i=new Intent(getApplicationContext(),Fixture.class);
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
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Ranking.class);
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
        venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Venues.class);
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
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LiveList.class);
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
        record.setVisibility(View.GONE);
        adapter=new RankingContentAdapter(Ranking.this,rankingobject);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent intent=new Intent(getApplicationContext(),TeamDetail.class);
                    intent.putExtra("Team", Constants.Team_Ranking_URL);
                    startActivity(intent);
                }
                if(position==1)
                {
                    Intent intent=new Intent(getApplicationContext(),PlayerRankDetail.class);
                    intent.putExtra("Team", "Batting");
                    startActivity(intent);
                }
                if(position==2)
                {
                    Intent intent=new Intent(getApplicationContext(),PlayerRankDetail.class);
                    intent.putExtra("Team","Bowling");
                    startActivity(intent);
                }
                if(position==3)
                {
                    Intent intent=new Intent(getApplicationContext(),PlayerRankDetail.class);
                    intent.putExtra("Team","Allrounder");
                    startActivity(intent);
                }
            }
        });

       // record.setVisibility(View.GONE);
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
            //record.setVisibility(View.VISIBLE);
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
