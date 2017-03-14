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
import Model.playerstatitem;

/**
 * Created by TOQEER on 8/3/2015.
 */
public class PlayerStatAdapter extends BaseAdapter {
    ArrayList<playerstatitem> playerobject;
    Context context;
    private ImageLoader imageLoader;
    private static LayoutInflater inflater = null;
    public PlayerStatAdapter(Context context, ArrayList<playerstatitem> playerobject)
    {
            this.context=context;
            this.playerobject=playerobject;
        imageLoader = new ImageLoader(context.getApplicationContext());
        imageLoader.clearCache();
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }
    @Override
    public int getCount() {
       return playerobject.size();
    }

    @Override
    public playerstatitem getItem(int i) {
        return playerobject.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = new Holder();
        HolderImage image=new HolderImage();


        View rowView;

        rowView = inflater.inflate(R.layout.playerstatadapter, null);


        image.flag=(ImageView)rowView.findViewById(R.id.countryflag);
        holder.title = (TextView) rowView.findViewById(R.id.title);
        playerstatitem temp = playerobject.get(i);

        holder.title.setText(temp.getName());
       // imageLoader.DisplayFlagImage(temp.flag, image.flag);


        if(temp.getCountry().equalsIgnoreCase("India"))
        {
            //Log.d("ABCdddd",a);
             image.flag.setImageResource(R.drawable.ind_small);
        }

        else if(temp.getCountry().equalsIgnoreCase("Pakistan"))
        {
            image.flag.setImageResource(R.drawable.pak_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("England"))
        {
            image.flag.setImageResource(R.drawable.eng_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("Canada"))
        {
            image.flag.setImageResource(R.drawable.can_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("Sri Lanka"))
        {
            image.flag.setImageResource(R.drawable.sl_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("West Indies"))
        {
            image.flag.setImageResource(R.drawable.wi_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("Zimbabwe"))
        {
            image.flag.setImageResource(R.drawable.zim_smal);

        }
        else if(temp.getCountry().equalsIgnoreCase("Bangladesh"))
        {
            image.flag.setImageResource(R.drawable.ban_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Afghanistan"))
        {
            image.flag.setImageResource(R.drawable.afg_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Australia")) {
            image.flag.setImageResource(R.drawable.aus_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("New Zealand"))
        {
            image.flag.setImageResource(R.drawable.nz_small);

        }
        else if(temp.getCountry().equalsIgnoreCase("South Africa"))
        {
            image.flag.setImageResource(R.drawable.sa_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Ireland"))
        {
            image.flag.setImageResource(R.drawable.ire_small);

        }



        else if(temp.getCountry().equalsIgnoreCase("Scotland"))
        {
            image.flag.setImageResource(R.drawable.sct_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Kenya"))
        {
            image.flag.setImageResource(R.drawable.ken_small);

        }

        else if(temp.getCountry().equalsIgnoreCase("Netherlands"))
        {
            image.flag.setImageResource(R.drawable.neth_small);

        }
        return rowView;
    }

    public class Holder {
        TextView title;
        TextView tempcountry;
    }
    public class HolderImage {
        ImageView flag;
    }
    }
