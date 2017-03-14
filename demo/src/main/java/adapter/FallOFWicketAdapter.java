package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.koherent.pdlapps.cricketworldcup2015live.R;

import java.util.ArrayList;

import ImageDownloader.ImageLoader;
import Model.fallwicketsitem;

/**
 * Created by TOQEER on 9/9/2015.
 */
public class FallOFWicketAdapter extends BaseAdapter {

    ArrayList<fallwicketsitem> object;
    Context context;
    private static LayoutInflater inflater = null;
    private ImageLoader imageLoader;
    public FallOFWicketAdapter(Context context, ArrayList<fallwicketsitem> object) {
        this.context = context;
        this.object = object;
        imageLoader = new ImageLoader(context.getApplicationContext());
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
        TextView run;
        TextView name;
        TextView over;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();


        View rowView;

        rowView = inflater.inflate(R.layout.fallwicketsadapter, null);

        holder.name = (TextView) rowView.findViewById(R.id.name);
        holder.run = (TextView) rowView.findViewById(R.id.run);
        holder.over = (TextView) rowView.findViewById(R.id.overstre);
        fallwicketsitem temp=object.get(position);
        holder.name.setText(temp.name);
        holder.run.setText(temp.run);
        holder.over.setText(temp.over);
        return rowView;
    }
}
