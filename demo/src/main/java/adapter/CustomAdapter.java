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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import Model.Items;

/**
 * Created by T.A on 12/05/2015.
 */
public class CustomAdapter extends BaseAdapter {

    ArrayList<Items> object;
    Context context;
    private static LayoutInflater inflater = null;

    public CustomAdapter(Context context, ArrayList<Items> object) {
        this.context = context;
        this.object = object;

        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return object.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView team;
        TextView played;

        TextView win;

        TextView series;

        TextView lose;

        TextView loc;
        TextView date;
        TextView month;
        TextView team1;TextView team2;
    }
    public  class image
    {

        ImageView img;
        ImageView img2;
    }

    public static String getFormattedTime(String match_time){
        String your_format2 = null ;
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        try {

            Date date2 = sdf2.parse(match_time);
            System.out.println(date2);
            your_format2 = new SimpleDateFormat("hh:mm a").format(date2);
//		    String [] splitted = your_format.split(" ");
            System.out.println(your_format2);    //The second part of the splitted string, i.e time
            // Now you can set the TextView here


        } catch (Exception e) {
            e.printStackTrace(); //date format error
        }

        return your_format2;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        image imgholder=new image();

        LinearLayout top;
        LinearLayout bottom;
        LinearLayout left;
        View rowView;
        String a;
        rowView = inflater.inflate(R.layout.fixturedemo, null);

        imgholder.img=(ImageView)rowView.findViewById(R.id.team1image2);
        imgholder.img2=(ImageView)rowView.findViewById(R.id.team1image3);

//        bottom=(LinearLayout)rowView.findViewById(R.id.innerlinear2);
//        bottom.getBackground().setAlpha(230);

        holder.team = (TextView) rowView.findViewById(R.id.datetime);
        holder.month = (TextView) rowView.findViewById(R.id.month);
        holder.date = (TextView) rowView.findViewById(R.id.date);
        holder.loc=(TextView)rowView.findViewById(R.id.sat);
        holder.series=(TextView)rowView.findViewById(R.id.match2);
        holder.team1=(TextView)rowView.findViewById(R.id.team1fix);
        holder.team2=(TextView)rowView.findViewById(R.id.team2fix);

        Items temp = object.get(position);


        holder.team1.setText(temp.teamtwo);
        holder.team2.setText(temp.teamone);
        String [] splitted = new String[0];
        String your_format = null ;
        String your_format2 = null ;
        String your_format3 = null ;
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        Log.d("Time zone", "=" + tz.getDisplayName());

        Date date2 = null;
        Date date3 = null;
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        try {

             date2= sdf2.parse(temp.datetime);
            System.out.println(date2);
            your_format2 = new SimpleDateFormat("kk:mm").format(date2);
//		    String [] splitted = your_format.split(" ");
            TimeZone gmtTime = TimeZone.getTimeZone("GMT");
            sdf2.setTimeZone(gmtTime);
            your_format3 = new SimpleDateFormat("kk:mm").format(sdf2.parse(temp.datetime));
           Log.d("GMT Time: ", your_format3);
            System.out.println(your_format2);    //The second part of the splitted string, i.e time
            // Now you can set the TextView here


        } catch (Exception e) {
            e.printStackTrace(); //date format error
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        try {

            Date date = sdf.parse(temp.datetime);
            System.out.println(date);
            your_format = new SimpleDateFormat("MMM dd").format(date);
             splitted = your_format.split(" ");
            System.out.println(splitted[0]);    //The second part of the splitted string, i.e time
            System.out.println(splitted[1]);
        } catch (Exception e) {
            e.printStackTrace(); //date format error
        }

if(tz.getDisplayName().equalsIgnoreCase("Pakistan Standard Time")) {
    holder.team.setText(your_format2 + "   PST");
}
        else if(tz.getDisplayName().equalsIgnoreCase("India Standard Time"))
{
    holder.team.setText(your_format2 + "   IST");
}
else if(tz.getDisplayName().equalsIgnoreCase("Gulf Standard Time"))
{
    holder.team.setText(your_format2 + "   GST");
}
else if(tz.getDisplayName().equalsIgnoreCase("Western Indonesia Time"))
{
    holder.team.setText(your_format2 + "   WIT");
}
else if(tz.getDisplayName().equalsIgnoreCase("Pacific Standard Time"))
{
    holder.team.setText(your_format2 + "   PST");
}
else if(tz.getDisplayName().equalsIgnoreCase("Bangladesh Standard Time"))
{
    holder.team.setText(your_format2 + "   BST");
}
        else {

          holder.team.setText(your_format3+ "   GMT");
        }
//        String a[]=temp.teamtwo.split(" ",3);
//
//        holder.played.setText(temp.teamone);
//        holder.win.setText(temp.teamtwo);
        holder.date.setText(splitted[1]);
        holder.month.setText(splitted[0]);
        holder.loc.setText(temp.venue);
        holder.series.setText(temp.series);
        Log.d("ABC",temp.teamone);
        //String a=temp.teamone.trim();
       // Toast.makeText(context,temp.teamtwo.toString(),Toast.LENGTH_LONG).show();
        if(temp.teamA.equalsIgnoreCase("NEP"))
        {
            imgholder.img.setImageResource(R.drawable.nipal);

        }
        else if(temp.teamB.equalsIgnoreCase("NEP"))
        {
            imgholder.img2.setImageResource(R.drawable.nipal);

        }

        else if(temp.teamA.equalsIgnoreCase("PAK"))
        {
            //Log.d("ABCdddd",a);
            imgholder.img.setImageResource(R.drawable.pakistan);
        }

        else if(temp.teamA.equalsIgnoreCase("DD"))
        {
            imgholder.img.setImageResource(R.drawable.delhi_daredevils);

        }
        else if(temp.teamA.equalsIgnoreCase("Zim"))
        {
            imgholder.img.setImageResource(R.drawable.zimbabwe);

        }
        else if(temp.teamA.equalsIgnoreCase("KXIP"))
        {
            imgholder.img.setImageResource(R.drawable.kings_punjab);

        }
        else if(temp.teamA.equalsIgnoreCase("MI"))
        {
            imgholder.img.setImageResource(R.drawable.mumbai_indians);

        }
        else if(temp.teamone.equalsIgnoreCase("SRH"))
        {
            imgholder.img.setImageResource(R.drawable.sunrisers_hyderabad);

        }
        else if(temp.teamA.equalsIgnoreCase("RR"))
        {
            imgholder.img.setImageResource(R.drawable.rajasthan_royals);

        }
        else if(temp.teamA.equalsIgnoreCase("RCB"))
        {
            imgholder.img.setImageResource(R.drawable.royal_challengers_bangalore);

        }
        else if(temp.teamA.equalsIgnoreCase("ENG"))
        {
            imgholder.img.setImageResource(R.drawable.england);

        }
        else if(temp.teamA.equalsIgnoreCase("CSK"))
        {
            imgholder.img.setImageResource(R.drawable.chennai_super_kings);

        }
        else if(temp.teamA.equalsIgnoreCase("IND"))
        {
            imgholder.img.setImageResource(R.drawable.india);

        }
        else if(temp.teamA.equalsIgnoreCase("BAN"))
        {
            imgholder.img.setImageResource(R.drawable.bangladesh);

        }
        else if(temp.teamA.equalsIgnoreCase("AUS"))
        {
            imgholder.img.setImageResource(R.drawable.australia);

        }
        else if(temp.teamA.equalsIgnoreCase("AFN"))
        {
            imgholder.img.setImageResource(R.drawable.afghanistan);

        }
        else if(temp.teamA.equalsIgnoreCase("WI"))
        {
            imgholder.img.setImageResource(R.drawable.west_indies);

        }
        else if(temp.teamA.equalsIgnoreCase("US"))
        {
            imgholder.img.setImageResource(R.drawable.united_states);

        }
        else if(temp.teamA.equalsIgnoreCase("KKR"))
        {
            imgholder.img.setImageResource(R.drawable.kolkata_knight_riders);

        }
        else if(temp.teamA.equalsIgnoreCase("TBC"))
        {
            imgholder.img.setImageResource(R.drawable.tbd);

        }
        else if(temp.teamA.equalsIgnoreCase("SL"))
        {
            imgholder.img.setImageResource(R.drawable.sri_lanka);

        }
        else if(temp.teamA.equalsIgnoreCase("rsa"))
        {
            imgholder.img.setImageResource(R.drawable.south_africa);

        }
        else if(temp.teamA.equalsIgnoreCase("AFG"))
        {
            imgholder.img.setImageResource(R.drawable.afghanistan);

        }
        if(temp.teamB.equalsIgnoreCase("PAK"))
        {
            //Log.d("ABCdddd",a);
            imgholder.img2.setImageResource(R.drawable.pakistan);
        }
       else if(temp.teamB.equalsIgnoreCase("rsa"))
        {
            //Log.d("ABCdddd",a);
            imgholder.img2.setImageResource(R.drawable.south_africa);
        }
        else if(temp.teamB.equalsIgnoreCase("DD"))
        {
            imgholder.img2.setImageResource(R.drawable.delhi_daredevils);

        }
        else if(temp.teamB.equalsIgnoreCase("Zim"))
        {
            imgholder.img2.setImageResource(R.drawable.zimbabwe);

        }
        else if(temp.teamB.equalsIgnoreCase("KXIP"))
        {
            imgholder.img2.setImageResource(R.drawable.kings_punjab);

        }
        else if(temp.teamB.equalsIgnoreCase("KKR"))
        {
            imgholder.img2.setImageResource(R.drawable.kolkata_knight_riders);

        }
        else if(temp.teamB.equalsIgnoreCase("MI"))
        {
            imgholder.img2.setImageResource(R.drawable.mumbai_indians);

        }
        else if(temp.teamB.equalsIgnoreCase("SRH"))
        {
            imgholder.img2.setImageResource(R.drawable.sunrisers_hyderabad);

        }
        else if(temp.teamB.equalsIgnoreCase("RR"))
        {
            imgholder.img2.setImageResource(R.drawable.rajasthan_royals);

        }
        else if(temp.teamB.equalsIgnoreCase("RCB"))
        {
            imgholder.img2.setImageResource(R.drawable.royal_challengers_bangalore);

        }
        else if(temp.teamB.equalsIgnoreCase("ENG"))
        {
            imgholder.img2.setImageResource(R.drawable.england);

        }
        else if(temp.teamB.equalsIgnoreCase("CSK"))
        {
            imgholder.img2.setImageResource(R.drawable.chennai_super_kings);

        }
        else if(temp.teamB.equalsIgnoreCase("IND"))
        {
            imgholder.img2.setImageResource(R.drawable.india);

        }
        else if(temp.teamB.equalsIgnoreCase("BAN"))
        {
            imgholder.img2.setImageResource(R.drawable.bangladesh);

        }
        else if(temp.teamB.equalsIgnoreCase("AUS"))
        {
            imgholder.img2.setImageResource(R.drawable.australia);

        }
        else if(temp.teamB.equalsIgnoreCase("AFG"))
        {
            imgholder.img2.setImageResource(R.drawable.afghanistan);

        }
        else if(temp.teamB.equalsIgnoreCase("WI"))
        {
            imgholder.img2.setImageResource(R.drawable.west_indies);

        }
        else if(temp.teamB.equalsIgnoreCase("US"))
        {
            imgholder.img2.setImageResource(R.drawable.united_states);

        }
        else if(temp.teamA.equalsIgnoreCase("CAN"))
        {
            imgholder.img.setImageResource(R.drawable.canada);

        }
        else if(temp.teamB.equalsIgnoreCase("CAN"))
        {
            imgholder.img2.setImageResource(R.drawable.canada);

        }
        else if(temp.teamA.equalsIgnoreCase("IRE"))
        {
            imgholder.img.setImageResource(R.drawable.ireland);

        }
        else if(temp.teamA.equalsIgnoreCase("TBC"))
        {
            imgholder.img.setImageResource(R.drawable.tbd);

        }
        else if(temp.teamB.equalsIgnoreCase("IRE"))
        {
            imgholder.img2.setImageResource(R.drawable.ireland);

        }

        else if(temp.teamB.equalsIgnoreCase("SL"))
        {
            imgholder.img2.setImageResource(R.drawable.sri_lanka);

        }
        else if(temp.teamA.equalsIgnoreCase("SCT"))
        {
            imgholder.img.setImageResource(R.drawable.scotland);

        }
        else if(temp.teamB.equalsIgnoreCase("SCT"))
        {
            imgholder.img2.setImageResource(R.drawable.scotland);

        }
        else if(temp.teamA.equalsIgnoreCase("KEN"))
        {
            imgholder.img.setImageResource(R.drawable.kenya);

        }
        else if(temp.teamB.equalsIgnoreCase("KEN"))
        {
            imgholder.img2.setImageResource(R.drawable.kenya);

        }
        else if(temp.teamA.equalsIgnoreCase("Nl"))
        {
            imgholder.img.setImageResource(R.drawable.netherlands);

        }
        else if(temp.teamB.equalsIgnoreCase("Nl"))
        {
            imgholder.img2.setImageResource(R.drawable.netherlands);

        }


        else if(temp.teamA.equalsIgnoreCase("NZ"))
        {
            imgholder.img.setImageResource(R.drawable.new_zealand);

        }
        else if(temp.teamB.equalsIgnoreCase("NZ"))
        {
            imgholder.img2.setImageResource(R.drawable.new_zealand);

        }
        else if(temp.teamB.equalsIgnoreCase("TBC"))
        {
            imgholder.img2.setImageResource(R.drawable.tbd);

        }

//        holder.tv.setText(result[position]);
        return rowView;

    }
}