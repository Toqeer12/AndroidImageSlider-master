package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koherent.pdlapps.cricketworldcup2015live.R;

import java.util.ArrayList;

import Model.LiveScore;


/**
 * Created by Danii on 5/14/2015.
 */
public class ListScoreAdapter extends BaseAdapter {

    LinearLayout linearLayout;
    ArrayList<LiveScore> object;
    Context context;
    private static LayoutInflater inflater = null;

    View view;

    public ListScoreAdapter(Context context, ArrayList<LiveScore> object) {
        this.context = context;
        this.object = object;

        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return object.size();
    }

    @Override
    public Object getItem(int position) {

        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        view = inflater.inflate(R.layout.custom_list_view_livescore,null);

/*
        linearLayout = (LinearLayout) view.findViewById(R.id.customListView);
        linearLayout.getBackground().setAlpha(230);
*/
        TextView bothTeamsTv = (TextView) view.findViewById(R.id.teamsTvId);
        TextView day = (TextView) view.findViewById(R.id.day);
        TextView tossTv = (TextView) view.findViewById(R.id.tossTvId);
        TextView team1ScoreTv = (TextView) view.findViewById(R.id.team1ScoreTvId);
        TextView batsmen1Tv = (TextView) view.findViewById(R.id.Batsmen1TvId);
        TextView batsmen2Tv = (TextView) view.findViewById(R.id.Batsmen2TvId);
        TextView innings = (TextView) view.findViewById(R.id.inningsTvId);
        TextView teamAname = (TextView) view.findViewById(R.id.teamAname);
        TextView teamBname = (TextView) view.findViewById(R.id.teamBna);
        TextView bowler1NameTv = (TextView) view.findViewById(R.id.Bowler1TvId);
        TextView bowler1OversTv = (TextView) view.findViewById(R.id.bowler1OverTv);
        TextView bowler1MaidTv = (TextView) view.findViewById(R.id.bowler1MaidTv);
        TextView bowler1RunsTv = (TextView) view.findViewById(R.id.bowler1RunsTv);
        TextView bowler1WicketsTv = (TextView) view.findViewById(R.id.bowler1WicketsTv);
        TextView breakreas = (TextView) view.findViewById(R.id.breakreas);
          TextView manofmatch = (TextView) view.findViewById(R.id.man);
//        TextView bowler2MaidTv = (TextView) view.findViewById(R.id.bowler2MaidTv);
//        TextView bowler2RunsTv = (TextView) view.findViewById(R.id.bowler2RunsTv);
//        TextView bowler2WicketsTv = (TextView) view.findViewById(R.id.bowler2WicketsTv);
//
       TextView prevOversTv = (TextView) view.findViewById(R.id.prevOversTvId);

        ImageView batFlagimageView = (ImageView) view.findViewById(R.id.team1FlagImgId);
        ImageView bowFlagimageView = (ImageView) view.findViewById(R.id.team2FlagImgId);

        LiveScore liveScore = object.get(position);


        if(liveScore.getMom().equalsIgnoreCase("No"))
        {
            manofmatch.setVisibility(View.GONE);
        }
            else {
            manofmatch.setText("Man of the Match "+liveScore.getMom());
        }
        String b1Name = liveScore.getBatsMan1();
        String b1Runs = liveScore.getBatsMan1Runs();
        String b1Balls = liveScore.getBatsMan1Balls();
        String b1DATA = b1Name+" :  "+b1Runs+" ("+b1Balls+")";

        String b2Name = liveScore.getBatsMan2();
        String b2Runs = liveScore.getBatsMan2Runs();
        String b2Balls = liveScore.getBatsMan2Balls();
        String b2DATA = b2Name+" :  "+b2Runs+" ("+b2Balls+")";

        String batTeamName = liveScore.getBatTeamName();
        String bowTeamName = liveScore.getBowTeamName();
        String teamAkey=liveScore.getTeamAkey();
        String teamBkey=liveScore.getTeamBkey();
        Log.d("", "" + bowTeamName);
//        if(batTeamName.equals("Kolkata:")) {
//            batFlagimageView.setImageResource(R.drawable.kolkata_knight_riders);
//        }else if(batTeamName.equals("Mumbai:")){
//            batFlagimageView.setImageResource(R.drawable.mumbai_indians);
//
//        }
        Log.d("Break",liveScore.getBreakreason());

        if(liveScore.getBreakreason().equalsIgnoreCase("NO")) {
            breakreas.setVisibility(View.GONE);
        }
       else if(liveScore.getBreakreason().equalsIgnoreCase("drinks_break")) {
            breakreas.setText("Status: " + "Drink Break");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("innings_break"))
        {
            breakreas.setText("Status: " + "Innings Break");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("lunch_break"))
        {
            breakreas.setText("Status: " + "Lunch Break");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("in_play"))
        {
            breakreas.setText("Status: " + "Play");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("rain_delay"))
        {
            breakreas.setText("Status: " + "Rain Delay");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("stumps"))
        {
            breakreas.setText("Status: " + "Stumps");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("bad_light"))
        {
            breakreas.setText("Status: " + "Bad Lights");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("crowd_trouble"))
        {
            breakreas.setText("Status: " + "Crowd Trouble");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("bad_pitch_condition"))
        {
            breakreas.setText("Status: " + "Bad Pitch");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("player_injured"))
        {
            breakreas.setText("Status: " + "Player Injured");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("floodlight_failure"))
        {
            breakreas.setText("Status: " + "Flood Light Failure");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("ball_change"))
        {
            breakreas.setText("Status: " + "Ball Change");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("scheduled"))
        {
            breakreas.setText("Status: " + "Scheduled");
        }
        else if(liveScore.getBreakreason().equalsIgnoreCase("start_delayed"))
        {
            breakreas.setText("Status: " + "Match delayed by a wet outfield");
        }
        else
        {

            breakreas.setVisibility(View.GONE);
        }
        bothTeamsTv.setText(liveScore.getBothTeams().toString());
        tossTv.setText(liveScore.getToss().toString());
        team1ScoreTv.setText(liveScore.getBatTeamScore().toString());
        batsmen1Tv.setText(b1DATA);
        batsmen2Tv.setText(b2DATA);

        bowler1NameTv.setText(liveScore.getBow1Name().toString());
        bowler1OversTv.setText(liveScore.getBow1Over().toString());
        bowler1MaidTv.setText(liveScore.getBow1Maid().toString());
        bowler1RunsTv.setText(liveScore.getBow1Runs().toString());
        bowler1WicketsTv.setText(liveScore.getBow1Wickets().toString());
        if(liveScore.getInfo().toString().equalsIgnoreCase("No"))
        {
            prevOversTv.setVisibility(View.GONE);
        }
        else
        {
            prevOversTv.setText(liveScore.getInfo().toString());
        }
        innings.setText("Innings "+liveScore.getInnings().toString());
        if(liveScore.getDayy().equalsIgnoreCase("0")) {
            day.setVisibility(View.GONE);
        }
        else
        {
            day.setText("Day: " + liveScore.getDayy());
        }
//

        if(batTeamName.equalsIgnoreCase("England"))
        {
            teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.england);

        }
        else if(batTeamName.equalsIgnoreCase("Nepal"))
        {
            teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.nipal);

        }
        else if(batTeamName.equalsIgnoreCase("New Zealand"))
//        if(batTeamName.equalsIgnoreCase("Pak:"))
        {
//            //Log.d("ABCdddd",a);
            teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.new_zealand);
        }
        else if(batTeamName.equalsIgnoreCase("Pakistan"))
        {
            teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.pakistan);
        }
        else if(batTeamName.equalsIgnoreCase("Zimbabwe"))
        {
            teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.zimbabwe);
        }
        else if(batTeamName.equalsIgnoreCase("Australia"))
        {
            teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.australia);
        }
        else if(batTeamName.equalsIgnoreCase("India"))
        {
            teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.india);
        }
        else if(batTeamName.equalsIgnoreCase("Afghanistan"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.afghanistan);
        }
        else if(batTeamName.equalsIgnoreCase("Ireland"))
        {
            teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.ireland);
        }
        else if(batTeamName.equalsIgnoreCase("Netherlands"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.netherlands);
        }
        else if(batTeamName.equalsIgnoreCase("Scotland"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.scotland);
        }
        else if(batTeamName.equalsIgnoreCase("South Africa"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.south_africa);
        }
        else if(batTeamName.equalsIgnoreCase("Sri Lanka"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.sri_lanka);
        }
        else if(batTeamName.equalsIgnoreCase("United"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.uae);
        }
        else if(batTeamName.equalsIgnoreCase("West Indies"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.west_indies);
        }
        else if(batTeamName.equalsIgnoreCase("Canada"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.canada);
        }
        else if(batTeamName.equalsIgnoreCase("kenya"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.kenya);
        }
        //ipl teams
        else if(batTeamName.equalsIgnoreCase("chennai super kings"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.chennai_super_kings);
        }
        else if(batTeamName.equalsIgnoreCase("delhi daredevils"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.delhi_daredevils);
        }
        else if(batTeamName.equalsIgnoreCase("bangladesh"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.bangladesh);
        }
        else if(batTeamName.equalsIgnoreCase("kolkata knight riders"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.kolkata_knight_riders);
        }
        else if(batTeamName.equalsIgnoreCase("mumbai indians"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.mumbai_indians);
        }
        else if(batTeamName.equalsIgnoreCase("rajasthan royals"))
        {teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.rajasthan_royals);
        }
        else if(batTeamName.equalsIgnoreCase("royal challengers bangalore"))
        {
            teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.royal_challengers_bangalore);
        }
        else if(batTeamName.equalsIgnoreCase("sunrisers hyderabad"))
        {
            teamAname.setText(batTeamName);
            batFlagimageView.setImageResource(R.drawable.sunrisers_hyderabad);
        }


        if (bowTeamName.equalsIgnoreCase("new zealand"))
//        if(batTeamName.equalsIgnoreCase("Pak:"))
        {
//            //Log.d("ABCdddd",a);
            teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.new_zealand);
        }
        else if(bowTeamName.equalsIgnoreCase("england"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.england);

        }
        else if(bowTeamName.equalsIgnoreCase("nepal"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.nipal);

        }
        else if(bowTeamName.equalsIgnoreCase("pakistan"))
        {
            teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.pakistan);

        }
        else if(bowTeamName.equalsIgnoreCase("zimbabwe"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.zimbabwe);

        }
        else if(bowTeamName.equalsIgnoreCase("australia"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.australia);

        }
        else if(bowTeamName.equalsIgnoreCase("india"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.india);

        }
        else if(bowTeamName.equalsIgnoreCase("afghanistan"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.afghanistan);
        }
        else if(bowTeamName.equalsIgnoreCase("ireland"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.ireland);
        }
        else if(bowTeamName.equalsIgnoreCase("netherlands"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.netherlands);
        }
        else if(bowTeamName.equalsIgnoreCase("scotland"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.scotland);
        }
        else if(bowTeamName.equalsIgnoreCase("South Africa"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.south_africa);
        }
        else if(bowTeamName.equalsIgnoreCase("Sri Lanka"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.sri_lanka);
        }
        else if(bowTeamName.equalsIgnoreCase("uae"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.uae);
        }
        else if(bowTeamName.equalsIgnoreCase("West Indies"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.west_indies);
        }
        else if(bowTeamName.equalsIgnoreCase("canada"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.canada);
        }
        else if(bowTeamName.equalsIgnoreCase("kenya"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.kenya);
        }
        else if(bowTeamName.equalsIgnoreCase("bangladesh"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.bangladesh);
        }
        //ipl bowTeamName
        else if(bowTeamName.equalsIgnoreCase("chennai super kings"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.chennai_super_kings);
        }
        else if(bowTeamName.equalsIgnoreCase("Delhi Dsssaredevils"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.delhi_daredevils);
        }
        else if(bowTeamName.equalsIgnoreCase("Kings Punjab"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.kings_punjab);
        }
        else if(bowTeamName.equalsIgnoreCase("kolkata knight riders"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.kolkata_knight_riders);
        }
        else if(bowTeamName.equalsIgnoreCase("mumbai indians"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.mumbai_indians);
        }
        else if(bowTeamName.equalsIgnoreCase("rajasthan royals"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.rajasthan_royals);
        }
        else if(bowTeamName.equalsIgnoreCase("royal challengers bangalore"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.royal_challengers_bangalore);
        }
        else if(bowTeamName.equalsIgnoreCase("sunrisers hyderabad"))
        {teamBname.setText(bowTeamName);
            bowFlagimageView.setImageResource(R.drawable.sunrisers_hyderabad);
        }
        return view;
    }
}
