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

import ImageDownloader.ImageLoader;
import Model.TeamItem;

/**
 * Created by T.A on 14/05/2015.
 */
public class TeamAdapter extends BaseAdapter {
    private ImageLoader imageLoader;
    ArrayList<TeamItem> object;
    Context context;
    private static LayoutInflater inflater = null;

    public TeamAdapter(Context context, ArrayList<TeamItem> object) {
        this.context = context;
        this.object = object;
//        imageLoader = new ImageLoader(context.getApplicationContext());
//        imageLoader.clearCache();
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
        TextView teaam;
        TextView match;

        TextView point;
        TextView serial;
        TextView rating;


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


        View rowView;

        rowView = inflater.inflate(R.layout.teamdetailadapter, null);


        imgholder.img=(ImageView)rowView.findViewById(R.id.abc);
        holder.teaam = (TextView) rowView.findViewById(R.id.teama);
        holder.match = (TextView) rowView.findViewById(R.id.matchh);
        holder.point = (TextView) rowView.findViewById(R.id.pointt);
        holder.rating=(TextView)rowView.findViewById(R.id.ranki);
        holder.serial=(TextView)rowView.findViewById(R.id.ranum);
        TeamItem temp = object.get(position);
        Log.d("",temp.team);
        holder.teaam.setText(temp.team);
//
//
        holder.match.setText(temp.matches);
        holder.point.setText(temp.rating);
        holder.rating.setText(temp.points);
        holder.serial.setText(temp.id);
        //String a=temp.teamone.trim();

        if(temp.team.equalsIgnoreCase("India"))
        {
            //Log.d("ABCdddd",a);
            imgholder.img.setImageResource(R.drawable.ind_small);
        }

        else if(temp.team.equalsIgnoreCase("Pakistan"))
        {
            imgholder.img.setImageResource(R.drawable.pak_small);

        }
        else if(temp.team.equalsIgnoreCase("England"))
        {
            imgholder.img.setImageResource(R.drawable.eng_small);

        }
        else if(temp.team.equalsIgnoreCase("Sri Lanka"))
        {
            imgholder.img.setImageResource(R.drawable.sl_small);

        }
        else if(temp.team.equalsIgnoreCase("West Indies"))
        {
            imgholder.img.setImageResource(R.drawable.wi_small);

        }
        else if(temp.team.equalsIgnoreCase("Zimbabwe"))
        {
            imgholder.img.setImageResource(R.drawable.zim_smal);

        }
        else if(temp.team.equalsIgnoreCase("Bangladesh"))
        {
            imgholder.img.setImageResource(R.drawable.ban_small);

        }

        else if(temp.team.equalsIgnoreCase("Afghanistan"))
        {
           imgholder.img.setImageResource(R.drawable.afg_small);

        }

        else if(temp.team.equalsIgnoreCase("Australia")) {
            imgholder.img.setImageResource(R.drawable.aus_small);

        }

        else if(temp.team.equalsIgnoreCase("New Zealand"))
        {
            imgholder.img.setImageResource(R.drawable.nz_small);

        }
        else if(temp.team.equalsIgnoreCase("South Africa"))
        {
            imgholder.img.setImageResource(R.drawable.sa_small);

        }

        else if(temp.team.equalsIgnoreCase("Ireland"))
        {
            imgholder.img.setImageResource(R.drawable.ire_small);

        }



        else if(temp.team.equalsIgnoreCase("Scotland"))
        {
            imgholder.img.setImageResource(R.drawable.sct_small);

        }

        else if(temp.team.equalsIgnoreCase("Kenya"))
        {
            imgholder.img.setImageResource(R.drawable.ken_small);

        }

        else if(temp.team.equalsIgnoreCase("Netherlands"))
        {
            imgholder.img.setImageResource(R.drawable.neth_small);

        }

        return rowView;

    }
}
