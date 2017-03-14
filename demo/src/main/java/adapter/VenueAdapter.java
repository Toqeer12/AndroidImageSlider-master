package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koherent.pdlapps.cricketworldcup2015live.R;
import java.util.List;

/**
 * Created by T.A on 16/05/2015.
 */
public class VenueAdapter extends BaseAdapter {


    List<Integer> Image;
    List<String>   country;
    Context context;
    private static LayoutInflater inflater = null;
    public VenueAdapter(Context context, List<Integer> Image, List<String> country) {
        this.context = context;
        this.Image = Image;
        this.country=country;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return Image.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;
        ImageView img;
        TextView text;
        rowView = inflater.inflate(R.layout.venueadapter, null);
        img=(ImageView)rowView.findViewById(R.id.imageee);
        text=(TextView)rowView.findViewById(R.id.titlevenue);
//
        text.setText(country.get(i));
        img.setImageResource(Image.get(i));
        return  rowView;
    }

}
