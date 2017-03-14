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
import Model.CountryItem;

/**
 * Created by TOQEER on 8/17/2015.
 */
public class SearchCountryAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    ArrayList<CountryItem> countrylist;
    private ImageLoader imageLoader;
    Context mContext;
    public SearchCountryAdapter(Context mContext, ArrayList<CountryItem> countrylist) {
        this.mContext=mContext;
        this.countrylist=countrylist;
        imageLoader = new ImageLoader(mContext);
        imageLoader.clearCache();
        inflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return countrylist.size();
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
        TextView title;


    }
    public class ImageHolder {
        ImageView imageflag;


    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = new Holder();
        ImageHolder image_holder=new ImageHolder();

        View rowView;

        rowView = inflater.inflate(R.layout.search_player_adapter, null);
        holder.title = (TextView) rowView.findViewById(R.id.searchtitle);
        image_holder.imageflag=(ImageView)rowView.findViewById(R.id.flag);

        CountryItem temp=countrylist.get(i);
//        Log.d("Adapter Response", temp.getFlag());
        holder.title.setText(temp.getCountry());
    //    imageLoader.DisplayFlagImage(temp.flag, image_holder.imageflag);
//        image_holder.imageflag.setImageResource(flag.get(i));


        if(temp.getCountry().equalsIgnoreCase("India"))
        {
            //Log.d("ABCdddd",a);
            image_holder.imageflag.setImageResource(R.drawable.ind_small);
        }

        else if(temp.getCountry().equalsIgnoreCase("Pakistan"))
        {
            image_holder.imageflag.setImageResource(R.drawable.pak_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("England"))
        {
            image_holder.imageflag.setImageResource(R.drawable.eng_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("Sri Lanka"))
        {
            image_holder.imageflag.setImageResource(R.drawable.sl_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("West Indies"))
        {
            image_holder.imageflag.setImageResource(R.drawable.wi_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("Zimbabwe"))
        {
            image_holder.imageflag.setImageResource(R.drawable.zim_smal);

        }
        else if(temp.getCountry().equalsIgnoreCase("Bangladesh"))
        {
            image_holder.imageflag.setImageResource(R.drawable.ban_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Afghanistan"))
        {
            image_holder.imageflag.setImageResource(R.drawable.afg_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Australia")) {
            image_holder.imageflag.setImageResource(R.drawable.aus_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("Canada")) {
            image_holder.imageflag.setImageResource(R.drawable.can_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("New Zealand"))
        {
            image_holder.imageflag.setImageResource(R.drawable.nz_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("South Africa"))
        {
            image_holder.imageflag.setImageResource(R.drawable.sa_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Ireland"))
        {
            image_holder.imageflag.setImageResource(R.drawable.ire_small);

        }



        else if(temp.getCountry().equalsIgnoreCase("Scotland"))
        {
            image_holder.imageflag.setImageResource(R.drawable.sct_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Kenya"))
        {
            image_holder.imageflag.setImageResource(R.drawable.ken_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Netherlands"))
        {
            image_holder.imageflag.setImageResource(R.drawable.neth_small);

        }

        return rowView;
    }

}
