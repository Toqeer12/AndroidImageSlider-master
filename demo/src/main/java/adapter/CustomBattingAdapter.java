package adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koherent.pdlapps.cricketworldcup2015live.R;

import java.util.ArrayList;

import Model.InningsOneBatting;

/**
 * Created by Danii on 5/20/2015.
 */
public class CustomBattingAdapter extends BaseAdapter {

    ArrayList<InningsOneBatting> mInningsBattingsList;
    Context context;
    private static LayoutInflater inflater=null;
    InningsOneBatting mInningsBatting;


    public CustomBattingAdapter(Context c, ArrayList<InningsOneBatting> inningsOneBattings){

        mInningsBattingsList = inningsOneBattings;
        context = c;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mInningsBattingsList.size();
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
        //return null;
        View rowView;
        rowView =inflater.inflate(R.layout.custom_batsmen_list_layout, null);

        LinearLayout layoutCustomMain;
        layoutCustomMain = (LinearLayout) rowView.findViewById(R.id.customLayoutMain);
        if(position % 2 == 0) {
            // set some color
            layoutCustomMain.setBackgroundColor(Color.parseColor("#80555555"));
        }



        TextView batsmenNameTV= (TextView) rowView.findViewById(R.id.batsmenName);
        TextView batsmenRunsTV= (TextView) rowView.findViewById(R.id.batsmenRuns);
        TextView batsmenBallsTV= (TextView) rowView.findViewById(R.id.batsmenBalls);
        TextView batsmen4sTV= (TextView) rowView.findViewById(R.id.batsmen4S);
        TextView batsmen6sTV= (TextView) rowView.findViewById(R.id.batsmen6S);
        TextView batsmenSR_TV= (TextView) rowView.findViewById(R.id.batsmenSR);
        TextView statuss=(TextView)rowView.findViewById(R.id.custom_batsmen_list_LastRowTV);
        TextView batsmen_list_lastRow= (TextView) rowView.findViewById(R.id.custom_batsmen_list_LastRowTV);

    mInningsBatting = mInningsBattingsList.get(position);

        String batsmenNameANDstatus = mInningsBatting.getBATSMEN_1_NAME();
        if(batsmenNameANDstatus.equalsIgnoreCase("Bowler")){

            layoutCustomMain.setBackgroundColor(Color.parseColor("#555555"));
            batsmenNameTV.setTypeface(Typeface.DEFAULT_BOLD);
        }
        String status=mInningsBatting.getBATSMEN_1_STATUS();

            String bName = mInningsBatting.getBATSMEN_1_NAME();
        String extrasDet = mInningsBatting.getInningsOneExtras();
//        if(bName!=("")){


        batsmenNameTV.setText(mInningsBatting.getBATSMEN_1_NAME());
            batsmenRunsTV.setText(mInningsBatting.getBATSMEN_1_RUNS());
            batsmenBallsTV.setText(mInningsBatting.getBATSMEN_1_BALLS());
            batsmen4sTV.setText(mInningsBatting.getBATSMEN_1_4S());
            batsmen6sTV.setText(mInningsBatting.getBATSMEN_1_6S());
            batsmenSR_TV.setText(mInningsBatting.getBATSMEN_1_SR());
            batsmen_list_lastRow.setText(mInningsBatting.getInningsOneExtras());



        String batRuns = mInningsBatting.getBATSMEN_1_RUNS();


        statuss.setText(mInningsBatting.getBatmanstatus());
        if(mInningsBatting.getBatmanstatus().equalsIgnoreCase("Not Out"))
        {
            layoutCustomMain.setBackgroundColor(Color.parseColor("#555555"));
            statuss.setTypeface(Typeface.DEFAULT_BOLD);
        }



        return rowView;
    }
}
