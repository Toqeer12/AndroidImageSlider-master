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
import Model.newsitem;

/**
 * Created by T.A on 13/05/2015.
 */
public class NewsAdapter extends BaseAdapter {

    ArrayList<newsitem> object;
    Context context;
    private ImageLoader imageLoader;

    private static LayoutInflater inflater = null;

    public NewsAdapter(Context context, ArrayList<newsitem> object) {
        this.context = context;
        this.object = object;
        imageLoader = new ImageLoader(context.getApplicationContext());
        imageLoader.clearCache();
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
        TextView title;
        TextView desc;



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

        rowView = inflater.inflate(R.layout.newsadpater, null);



        holder.title = (TextView) rowView.findViewById(R.id.title);
        holder.desc = (TextView) rowView.findViewById(R.id.desc);
        newsitem temp = object.get(position);
        holder.title.setText(temp.title);
        holder.desc.setText(temp.desc);
        Log.d("ABC", temp.title);



        return rowView;

    }
}
