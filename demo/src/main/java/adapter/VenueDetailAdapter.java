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
import Model.venueitem;

/**
 * Created by T.A on 20/05/2015.
 */
public class VenueDetailAdapter extends BaseAdapter {

    private ImageLoader imageLoader;
    Context context;
    ArrayList<venueitem> itemobject;
    private static LayoutInflater inflater = null;
    public VenueDetailAdapter(Context context, ArrayList<venueitem> itemobject) {
        this.context = context;
        this.itemobject = itemobject;
        imageLoader = new ImageLoader(context.getApplicationContext());
        imageLoader.clearCache();
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return itemobject.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public class Holder {
        TextView ven;
        TextView city;





    }
    public  class image
    {

        ImageView img;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;

        Holder holder = new Holder();
        image imgholder=new image();

        rowView = inflater.inflate(R.layout.venuedetail, null);

        imgholder.img=(ImageView)rowView.findViewById(R.id.venimg);
        holder.ven = (TextView) rowView.findViewById(R.id.ve);
        holder.city = (TextView) rowView.findViewById(R.id.cit);
        venueitem temp = itemobject.get(i);
        Log.d("", temp.venuee);
        imageLoader.DisplayImage(temp.link,  imgholder.img);
        holder.ven.setText(temp.venuee);
        holder.city.setText(temp.city);

        return  rowView;
    }
}
