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
import Model.Playeritem;

/**
 * Created by T.A on 15/05/2015.
 */
public class PlayerAdapter extends BaseAdapter {

    ArrayList<Playeritem> object;
    Context context;
    private static LayoutInflater inflater = null;
    private ImageLoader imageLoader;
    public PlayerAdapter(Context context, ArrayList<Playeritem> object) {
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
        TextView rank;
        TextView name;
        TextView rating;


    }
    public  class ImageHolder
    {

        ImageView img;
        ImageView img2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        ImageHolder imageHolder=new ImageHolder();

        View rowView;

        rowView = inflater.inflate(R.layout.playeradapter, null);

//
        holder.rank = (TextView) rowView.findViewById(R.id.rnk);
        holder.name = (TextView) rowView.findViewById(R.id.nme);
        imageHolder.img = (ImageView) rowView.findViewById(R.id.cntry);
        imageHolder.img2=(ImageView)rowView.findViewById(R.id.profile);
        holder.rating=(TextView)rowView.findViewById(R.id.rat);
        Playeritem temp = object.get(position);
        Log.d("Tean Name", temp.name);
        holder.rank.setText(temp.rank);
        holder.name.setText(temp.name);
    //    holder.country.setText(temp.country);
        holder.rating.setText(temp.rating);
/*
        if(temp.name.equalsIgnoreCase("R. Ashwin"))
        {
            imageHolder.img2.setImageResource(R.drawable.rashwin);
        }

       else if(temp.name.equalsIgnoreCase("R.A. Jadeja"))
        {
            imageHolder.img2.setImageResource(R.drawable.rjadeja);
        }
        else if(temp.name.equalsIgnoreCase("S Dhawan"))
        {
            imageHolder.img2.setImageResource(R.drawable.sdhawan);
        }
        else if(temp.name.equalsIgnoreCase("M.S. Dhoni"))
        {
            imageHolder.img2.setImageResource(R.drawable.msdhoni);
        }
        else if(temp.name.equalsIgnoreCase("V. Kohli"))
        {
            imageHolder.img2.setImageResource(R.drawable.vkholi);
        }
        else if(temp.name.equalsIgnoreCase("Mohammad Hafeez"))
        {
            imageHolder.img2.setImageResource(R.drawable.mhafeez);
        }
        else if(temp.name.equalsIgnoreCase("Shahid Afridi"))
        {
            imageHolder.img2.setImageResource(R.drawable.safridi);
        }
        else if(temp.name.equalsIgnoreCase("Saeed Ajmal"))
        {
            imageHolder.img2.setImageResource(R.drawable.sajmal);
        }
        else if(temp.name.equalsIgnoreCase("Shakib Al Hasan"))
        {
            imageHolder.img2.setImageResource(R.drawable.shasan);
        }
        else if(temp.name.equalsIgnoreCase("Imran Tahir"))
        {
            imageHolder.img2.setImageResource(R.drawable.itahir);
        }
        else if(temp.name.equalsIgnoreCase("D.W. Steyn"))
        {
            imageHolder.img2.setImageResource(R.drawable.dsteyn);
        }
        else if(temp.name.equalsIgnoreCase("J.P. Faulkner"))
        {
            imageHolder.img2.setImageResource(R.drawable.jfaulkner);
        }
        else if(temp.name.equalsIgnoreCase("S.C.J. Broad"))
        {
            imageHolder.img2.setImageResource(R.drawable.sbroad);
        }
        else if(temp.name.equalsIgnoreCase("S.R. Watson"))
        {
            imageHolder.img2.setImageResource(R.drawable.swatson);
        }
        else if(temp.name.equalsIgnoreCase("J.P. Duminy"))
        {
            imageHolder.img2.setImageResource(R.drawable.pduminy);
        }
        else if(temp.name.equalsIgnoreCase("Mohammad Nabi"))
        {
            imageHolder.img2.setImageResource(R.drawable.mnabi);
        }
        else if(temp.name.equalsIgnoreCase("S.C.J. Broad"))
        {
            imageHolder.img2.setImageResource(R.drawable.dsteyn);
        }
        else if(temp.name.equalsIgnoreCase("A.B. de Villiers"))
        {
            imageHolder.img2.setImageResource(R.drawable.dsteyn);
        }
        else if(temp.name.equalsIgnoreCase("H.M. Amla"))
        {
            imageHolder.img2.setImageResource(R.drawable.hamla);
        }
        else if(temp.name.equalsIgnoreCase("M.M. Ali"))
        {
            imageHolder.img2.setImageResource(R.drawable.mali);
        }
        else if(temp.name.equalsIgnoreCase("M.A. Starc"))
        {
            imageHolder.img2.setImageResource(R.drawable.mstarc);
        }
        else if(temp.name.equalsIgnoreCase("S.P. Narine"))
        {
            imageHolder.img2.setImageResource(R.drawable.snarine);
        }
        else if(temp.name.equalsIgnoreCase("S. Badree"))
        {
            imageHolder.img2.setImageResource(R.drawable.sbadree);
        }
        else if(temp.name.equalsIgnoreCase("S.P.Narine"))
        {
            imageHolder.img2.setImageResource(R.drawable.snarine);
        }
        else if(temp.name.equalsIgnoreCase("M.N. Samuelus"))
        {
            imageHolder.img2.setImageResource(R.drawable.msamuels);
        }
        else if(temp.name.equalsIgnoreCase("D.J.J. Bravo"))
        {
            imageHolder.img2.setImageResource(R.drawable.dbravo);
        }
        else if(temp.name.equalsIgnoreCase("C.H. Gayle"))
        {
            imageHolder.img2.setImageResource(R.drawable.cgayle);
        }
        else if(temp.name.equalsIgnoreCase("H.M.R.K.B. Herath"))
        {
            imageHolder.img2.setImageResource(R.drawable.rherath);
        }
        else if(temp.name.equalsIgnoreCase("S.M.S.M Senanayake"))
        {
            imageHolder.img2.setImageResource(R.drawable.ssenanayake);
        }
        else if(temp.name.equalsIgnoreCase("S.L. Malinga"))
        {
            imageHolder.img2.setImageResource(R.drawable.lmalinga);
        }

        else if(temp.name.equalsIgnoreCase("N.L. McCullum"))
        {
            imageHolder.img2.setImageResource(R.drawable.nmccullum);
        }
        else if(temp.name.equalsIgnoreCase("K.M.D.N. Kulasekara"))
        {
            imageHolder.img2.setImageResource(R.drawable.nkulasekara);
        }
        else if(temp.name.equalsIgnoreCase("A.D. Mathews"))
        {
            imageHolder.img2.setImageResource(R.drawable.amathews);
        }
        else if(temp.name.equalsIgnoreCase("T.M. Dilshan"))
        {
            imageHolder.img2.setImageResource(R.drawable.tdilshan);
        }
        else if(temp.name.equalsIgnoreCase("A.J. Finch"))
        {
            imageHolder.img2.setImageResource(R.drawable.afinch);
        }
        else if(temp.name.equalsIgnoreCase("E.J.G. Morgan"))
        {
            imageHolder.img2.setImageResource(R.drawable.afinch);
        }
        else if(temp.name.equalsIgnoreCase("D.A. Warner"))
        {
            imageHolder.img2.setImageResource(R.drawable.dwarner);
        }
        else if(temp.name.equalsIgnoreCase("B.B. McCullum"))
        {
            imageHolder.img2.setImageResource(R.drawable.bmccullum);
        }
        else if(temp.name.equalsIgnoreCase("R.L. Taylor"))
        {
            imageHolder.img2.setImageResource(R.drawable.rtaylor);
        }
        else if(temp.name.equalsIgnoreCase("A.D. Hales"))
        {
            imageHolder.img2.setImageResource(R.drawable.ahales);
        }
        else if(temp.name.equalsIgnoreCase("M.J. Guptill"))
        {
            imageHolder.img2.setImageResource(R.drawable.muptill);
        }
        else if(temp.name.equalsIgnoreCase("K.S. Williamson"))
        {
            imageHolder.img2.setImageResource(R.drawable.kwilliamson);
        }
        else if(temp.name.equalsIgnoreCase("M.D.K.J. Perera"))
        {
            imageHolder.img2.setImageResource(R.drawable.kperera);
        }
        else if(temp.name.equalsIgnoreCase("F. du Plessis"))
        {
            imageHolder.img2.setImageResource(R.drawable.flessis);
        }

        else if(temp.name.equalsIgnoreCase("C.J.L. Rogers"))
        {
            imageHolder.img2.setImageResource(R.drawable.clynn);
        }
        else if(temp.name.equalsIgnoreCase("G.J. Maxwell"))
        {
            imageHolder.img2.setImageResource(R.drawable.gmaxwell);
        }  else if(temp.name.equalsIgnoreCase("S.P.D. Smith"))
        {
            imageHolder.img2.setImageResource(R.drawable.gmaxwell);
        }
        else if(temp.name.equalsIgnoreCase("T.A. Boult"))
        {
            imageHolder.img2.setImageResource(R.drawable.tboult);
        }
        else if(temp.name.equalsIgnoreCase("T.G. Southee"))
        {
            imageHolder.img2.setImageResource(R.drawable.tsouthee);
        }
        else if(temp.name.equalsIgnoreCase("J.E. Root"))
        {
            imageHolder.img2.setImageResource(R.drawable.jroot);
        }*/
        if(temp.country.equalsIgnoreCase("Ind"))
        {
            //Log.d("ABCdddd",a);
            imageHolder.img.setImageResource(R.drawable.ind_small);
        }

        else if(temp.country.equalsIgnoreCase("Pak"))
        {
            imageHolder.img.setImageResource(R.drawable.pak_small);

        }
        else if(temp.country.equalsIgnoreCase("Eng"))
        {
            imageHolder.img.setImageResource(R.drawable.aus_small);

        }
        else if(temp.country.equalsIgnoreCase("SL"))
        {
            imageHolder.img.setImageResource(R.drawable.sl_small);

        }
        else if(temp.country.equalsIgnoreCase("WI"))
        {
            imageHolder.img.setImageResource(R.drawable.wi_small);

        }
        else if(temp.country.equalsIgnoreCase("ZIM"))
        {
            imageHolder.img.setImageResource(R.drawable.zim_smal);

        }
        else if(temp.country.equalsIgnoreCase("BAN"))
        {
            imageHolder.img.setImageResource(R.drawable.ban_small);

        }

        else if(temp.country.equalsIgnoreCase("Afg"))
        {


            imageHolder.img.setImageResource(R.drawable.afg_small);

        }

        else if(temp.country.equalsIgnoreCase("Aus")) {
            imageHolder.img.setImageResource(R.drawable.aus_small);

        }

        else if(temp.country.equalsIgnoreCase("NZ"))
        {
            imageHolder.img.setImageResource(R.drawable.nz_small);

        }
        else if(temp.country.equalsIgnoreCase("SA"))
        {
            imageHolder.img.setImageResource(R.drawable.sa_small);

        }

        else if(temp.country.equalsIgnoreCase("Scotland"))
        {
            imageHolder.img.setImageResource(R.drawable.sct_small);

        }


        else if(temp.country.equalsIgnoreCase("Netherlands"))
        {
            imageHolder.img.setImageResource(R.drawable.neth_small);

        }

        return rowView;

    }
}
