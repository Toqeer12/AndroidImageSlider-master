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

import Model.PointTable;

/**
 * Created by TOQEER on 9/19/2015.
 */
public class PointTableAdapter extends BaseAdapter {

    ArrayList<PointTable> object;
    Context context;
    private static LayoutInflater inflater = null;

    public PointTableAdapter(Context context, ArrayList<PointTable> object) {
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
        TextView country;
        TextView p;

        TextView l;

        TextView t;

        TextView nr;
        TextView w;
        TextView pts;
        TextView nrr;


    }
    public  class image
    {

        ImageView img;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        image imgholder=new image();

        LinearLayout top;
        LinearLayout bottom;
        LinearLayout left;
        View rowView;

        rowView = inflater.inflate(R.layout.pointableadapter, null);
        holder.p = (TextView) rowView.findViewById(R.id.p);
        holder.w = (TextView) rowView.findViewById(R.id.w);
        holder.l = (TextView) rowView.findViewById(R.id.l);
        holder.t=(TextView)rowView.findViewById(R.id.t);
        holder.nrr=(TextView)rowView.findViewById(R.id.nrr);
        holder.nr=(TextView)rowView.findViewById(R.id.nr);
        holder.country=(TextView)rowView.findViewById(R.id.country);
        holder.pts=(TextView)rowView.findViewById(R.id.pts);
        imgholder.img=(ImageView)rowView.findViewById(R.id.fl);
        PointTable temp = object.get(position);

        Log.d("", temp.getCountry());



        holder.country.setText(temp.getCountry());
        holder.p.setText(temp.getP());
        holder.t.setText(temp.getT());
        holder.w.setText(temp.getW());
        holder.l.setText(temp.getL());
        holder.pts.setText(temp.getPts());
        holder.nr.setText(temp.getNr());
        holder.nrr.setText(temp.getNrr());


        if(temp.getCountry().equalsIgnoreCase("India"))
        {
            //Log.d("ABCdddd",a);
            imgholder.img.setImageResource(R.drawable.ind_small);
        }

        else if(temp.getCountry().equalsIgnoreCase("Pakistan"))
        {
            imgholder.img.setImageResource(R.drawable.pak_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("England"))
        {
            imgholder.img.setImageResource(R.drawable.eng_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("Sri Lanka"))
        {
            imgholder.img.setImageResource(R.drawable.sl_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("West Indies"))
        {
            imgholder.img.setImageResource(R.drawable.wi_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("Zimbabwe"))
        {
            imgholder.img.setImageResource(R.drawable.zim_smal);

        }
        else if(temp.getCountry().equalsIgnoreCase("Bangladesh"))
        {
            imgholder.img.setImageResource(R.drawable.ban_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Afghanistan"))
        {
            imgholder.img.setImageResource(R.drawable.afg_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Australia")) {
            imgholder.img.setImageResource(R.drawable.aus_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("New Zealand"))
        {
            imgholder.img.setImageResource(R.drawable.nz_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("South Africa"))
        {
            imgholder.img.setImageResource(R.drawable.sa_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Ireland"))
        {
            imgholder.img.setImageResource(R.drawable.ire_small);

        }



        else if(temp.getCountry().equalsIgnoreCase("Scotland"))
        {
            imgholder.img.setImageResource(R.drawable.sct_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Kenya"))
        {
            imgholder.img.setImageResource(R.drawable.ken_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Netherlands"))
        {
            imgholder.img.setImageResource(R.drawable.neth_small);

        }
        return rowView;
    }
}
