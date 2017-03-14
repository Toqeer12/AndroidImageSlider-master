package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koherent.pdlapps.cricketworldcup2015live.R;

import java.util.ArrayList;

import ImageDownloader.ImageLoader;
import Model.galleryItem;

/**
 * Created by T.A on 14/05/2015.
 */
public class GalleryAdapter extends BaseAdapter {

    ArrayList<galleryItem> object;
    Context context;
    private ViewScaler mViewScaler;

    private static LayoutInflater inflater = null;
    private ImageLoader imageLoader;
    public GalleryAdapter(Context context, ArrayList<galleryItem> object) {
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
        TextView title;

    }
    public  class image
    {

        ImageView img;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        image imgholder=new image();

        View rowView;
        rowView = inflater.inflate(R.layout.galleryadapter, null);

        imgholder.img=(ImageView)rowView.findViewById(R.id.galleryimage);



        holder.title = (TextView) rowView.findViewById(R.id.gallerytitle);

        galleryItem temp = object.get(position);

        holder.title.setText(temp.title);
        imageLoader.DisplayImage(temp.image,  imgholder.img);
//        holder.tv.setText(result[position]);
        return rowView;

    }
}
