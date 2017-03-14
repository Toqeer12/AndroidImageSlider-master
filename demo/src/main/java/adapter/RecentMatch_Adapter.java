package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koherent.pdlapps.cricketworldcup2015live.R;
import java.util.ArrayList;

import Model.LivelistItem;

/**
 * Created by TOQEER on 9/1/2015.
 */
public class RecentMatch_Adapter extends BaseAdapter {


    ArrayList<LivelistItem> object;
    Context context;
    private static LayoutInflater inflater = null;

    public RecentMatch_Adapter(Context context, ArrayList<LivelistItem> object) {
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
        TextView type;
        TextView status;
        TextView venue;
        TextView related_name;


    }
    public  class image
    {

        ImageView img;
        ImageView img2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        image imgholder=new image();

//        LinearLayout top;
//        LinearLayout bottom;
//        LinearLayout left;
        View rowView;

        rowView = inflater.inflate(R.layout.recent_demo, null);

        imgholder.img=(ImageView)rowView.findViewById(R.id.team1image3);
        imgholder.img2=(ImageView)rowView.findViewById(R.id.team1image2);

        holder.team = (TextView) rowView.findViewById(R.id.team1);
        holder.played = (TextView) rowView.findViewById(R.id.team2);
        holder.type = (TextView) rowView.findViewById(R.id.winteam);
        holder.status=(TextView)rowView.findViewById(R.id.tour);
        holder.venue=(TextView)rowView.findViewById(R.id.sat);
        holder.related_name=(TextView)rowView.findViewById(R.id.related_name);
        LivelistItem temp = object.get(position);

        holder.team.setText(temp.teamone);
        holder.played.setText(temp.teamtwo);
        holder.type.setText(temp.winner);
        holder.venue.setText(temp.ven);
        holder.status.setText(temp.season);
        holder.related_name.setText(temp.related);
        Log.d("ABC", temp.teamtwo + temp.teamone);
        //String a=temp.teamone.trim();

        if(temp.teamone.equalsIgnoreCase("PAKISTAN"))
        {
            //Log.d("ABCdddd",a);
            imgholder.img.setImageResource(R.drawable.pakistan);
        }
        else if(temp.teamone.equalsIgnoreCase("South Africa"))
        {
            imgholder.img.setImageResource(R.drawable.south_africa);

        }

        else if(temp.teamone.equalsIgnoreCase("Delhi Daredevils"))
        {
            imgholder.img.setImageResource(R.drawable.delhi_daredevils);

        }
        else if(temp.teamone.equalsIgnoreCase("Nipal"))
        {
            imgholder.img.setImageResource(R.drawable.nipal);

        }
        else if(temp.teamone.equalsIgnoreCase("ZIMBABWE"))
        {
            imgholder.img.setImageResource(R.drawable.zimbabwe);

        }
        else if(temp.teamone.equalsIgnoreCase("KXIP"))
        {
            imgholder.img.setImageResource(R.drawable.kings_punjab);

        }
        else if(temp.teamone.equalsIgnoreCase("MI"))
        {
            imgholder.img.setImageResource(R.drawable.mumbai_indians);

        }
        else if(temp.teamone.equalsIgnoreCase("SRH"))
        {
            imgholder.img.setImageResource(R.drawable.sunrisers_hyderabad);

        }
        else if(temp.teamone.equalsIgnoreCase("RR"))
        {
            imgholder.img.setImageResource(R.drawable.rajasthan_royals);

        }
        else if(temp.teamone.equalsIgnoreCase("RCB"))
        {
            imgholder.img.setImageResource(R.drawable.royal_challengers_bangalore);

        }
        else if(temp.teamone.equalsIgnoreCase("ENGLAND"))
        {
            imgholder.img.setImageResource(R.drawable.england);

        }
        else if(temp.teamone.equalsIgnoreCase("CSK"))
        {
            imgholder.img.setImageResource(R.drawable.chennai_super_kings);

        }
        else if(temp.teamone.equalsIgnoreCase("INDIA"))
        {
            imgholder.img.setImageResource(R.drawable.india);

        }
        else if(temp.teamone.equalsIgnoreCase("New Zealand"))
        {
            imgholder.img.setImageResource(R.drawable.new_zealand);

        }
        else if(temp.teamone.equalsIgnoreCase("BANGLADESH"))
        {
            imgholder.img.setImageResource(R.drawable.bangladesh);

        }
        else if(temp.teamone.equalsIgnoreCase("AUSTRALIA"))
        {
            imgholder.img.setImageResource(R.drawable.australia);

        }
        else if(temp.teamone.equalsIgnoreCase("AFGHANISTAN"))
        {
            imgholder.img.setImageResource(R.drawable.afghanistan);

        }
        else if(temp.teamone.equalsIgnoreCase("West Indies"))
        {
            imgholder.img.setImageResource(R.drawable.west_indies);

        }
        else if(temp.teamone.equalsIgnoreCase("United States"))
        {
            imgholder.img.setImageResource(R.drawable.united_states);

        }
        else if(temp.teamone.equalsIgnoreCase("KKR"))
        {
            imgholder.img.setImageResource(R.drawable.kolkata_knight_riders);

        }
        else if(temp.teamone.equalsIgnoreCase("TBC"))
        {
            imgholder.img.setImageResource(R.drawable.tbd);

        }
        else if(temp.teamone.equalsIgnoreCase("Sri Lanka"))
        {
            imgholder.img.setImageResource(R.drawable.sri_lanka);

        }

        if(temp.teamtwo.equalsIgnoreCase("PAKISTAN"))
        {
            //Log.d("ABCdddd",a);
            imgholder.img2.setImageResource(R.drawable.pakistan);
        }
        else if(temp.teamtwo.equalsIgnoreCase("DD"))
        {
            imgholder.img2.setImageResource(R.drawable.delhi_daredevils);

        }
        else if(temp.teamtwo.equalsIgnoreCase("Sri Lanka"))
        {
            imgholder.img2.setImageResource(R.drawable.sri_lanka);

        }
        else if(temp.teamtwo.equalsIgnoreCase("South Africa"))
        {
            imgholder.img2.setImageResource(R.drawable.south_africa);

        }
        else if(temp.teamtwo.equalsIgnoreCase("ZIMBABWE"))
        {
            //Log.d("ABCdddd",a);
            imgholder.img2.setImageResource(R.drawable.zimbabwe);
        }

        else if(temp.teamtwo.equalsIgnoreCase("KXIP"))
        {
            imgholder.img2.setImageResource(R.drawable.kings_punjab);

        }
        else if(temp.teamtwo.equalsIgnoreCase("NIPAL"))
        {
            imgholder.img2.setImageResource(R.drawable.nipal);

        }
        else if(temp.teamtwo.equalsIgnoreCase("KKR"))
        {
            imgholder.img2.setImageResource(R.drawable.kolkata_knight_riders);

        }
        else if(temp.teamtwo.equalsIgnoreCase("MI"))
        {
            imgholder.img2.setImageResource(R.drawable.mumbai_indians);

        }
        else if(temp.teamtwo.equalsIgnoreCase("SRH"))
        {
            imgholder.img2.setImageResource(R.drawable.sunrisers_hyderabad);

        }
        else if(temp.teamtwo.equalsIgnoreCase("RR"))
        {
            imgholder.img2.setImageResource(R.drawable.rajasthan_royals);

        }
        else if(temp.teamtwo.equalsIgnoreCase("RCB"))
        {
            imgholder.img2.setImageResource(R.drawable.royal_challengers_bangalore);

        }
        else if(temp.teamtwo.equalsIgnoreCase("ENGLAND"))
        {
            imgholder.img2.setImageResource(R.drawable.england);

        }
        else if(temp.teamtwo.equalsIgnoreCase("CSK"))
        {
            imgholder.img2.setImageResource(R.drawable.chennai_super_kings);

        }
        else if(temp.teamtwo.equalsIgnoreCase("INDIA"))
        {
            imgholder.img2.setImageResource(R.drawable.india);

        }
        else if(temp.teamtwo.equalsIgnoreCase("BANLADESH"))
        {
            imgholder.img2.setImageResource(R.drawable.bangladesh);

        }
        else if(temp.teamtwo.equalsIgnoreCase("AUSTRALIA"))
        {
            imgholder.img2.setImageResource(R.drawable.australia);

        }
        else if(temp.teamtwo.equalsIgnoreCase("AFGHANISTAN"))
        {
            imgholder.img2.setImageResource(R.drawable.afghanistan);

        }
        else if(temp.teamtwo.equalsIgnoreCase("West Indies"))
        {
            imgholder.img2.setImageResource(R.drawable.west_indies);

        }
        else if(temp.teamtwo.equalsIgnoreCase("United States"))
        {
            imgholder.img2.setImageResource(R.drawable.united_states);

        }
        else if(temp.teamone.equalsIgnoreCase("CANADA"))
        {
            imgholder.img.setImageResource(R.drawable.canada);

        }
        else if(temp.teamtwo.equalsIgnoreCase("CANADA"))
        {
            imgholder.img2.setImageResource(R.drawable.canada);

        }
        else if(temp.teamone.equalsIgnoreCase("IRELAND"))
        {
            imgholder.img.setImageResource(R.drawable.ireland);

        }
        else if(temp.teamone.equalsIgnoreCase("TBC"))
        {
            imgholder.img.setImageResource(R.drawable.tbd);

        }
        else if(temp.teamtwo.equalsIgnoreCase("IRELAND"))
        {
            imgholder.img2.setImageResource(R.drawable.ireland);

        }

        else if(temp.teamone.equalsIgnoreCase("SCOTLAND"))
        {
            imgholder.img.setImageResource(R.drawable.scotland);

        }
        else if(temp.teamtwo.equalsIgnoreCase("SCOTLAND"))
        {
            imgholder.img2.setImageResource(R.drawable.scotland);

        }
        else if(temp.teamone.equalsIgnoreCase("KENYA"))
        {
            imgholder.img.setImageResource(R.drawable.kenya);

        }
        else if(temp.teamtwo.equalsIgnoreCase("KENTA"))
        {
            imgholder.img2.setImageResource(R.drawable.kenya);

        }
        else if(temp.teamone.equalsIgnoreCase("NETHERLAND"))
        {
            imgholder.img.setImageResource(R.drawable.netherlands);

        }
        else if(temp.teamtwo.equalsIgnoreCase("NETHERLAND"))
        {
            imgholder.img2.setImageResource(R.drawable.netherlands);

        }
        else if(temp.teamone.equalsIgnoreCase("New Zealand"))
        {
            imgholder.img.setImageResource(R.drawable.new_zealand);

        }
        else if(temp.teamtwo.equalsIgnoreCase("New Zealand"))
        {
            imgholder.img2.setImageResource(R.drawable.new_zealand);

        }
        else if(temp.teamtwo.equalsIgnoreCase("TBC"))
        {
            imgholder.img2.setImageResource(R.drawable.tbd);

        }

//        holder.tv.setText(result[position]);
        return rowView;

    }
}
