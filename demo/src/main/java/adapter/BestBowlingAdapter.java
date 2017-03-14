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

import Model.BestBowlingModel;

/**
 * Created by TOQEER on 9/21/2015.
 */
public class BestBowlingAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    List<BestBowlingModel> historyobject;
    Context mContext;

    public BestBowlingAdapter(Context mContext, List<BestBowlingModel> historyobject) {
        this.mContext = mContext;
        this.historyobject = historyobject;
        inflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return historyobject.size();
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
        TextView name;
        TextView opp;
        TextView o;
        TextView w;
        TextView r;
        TextView m;
        TextView er;
    }
    public class ImageHolder{
        ImageView flag1;
        ImageView flag2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = new Holder();
        ImageHolder hold = new ImageHolder();

        View rowView;
        rowView = inflater.inflate(R.layout.bestbolwingadapter, null);
        holder.name = (TextView) rowView.findViewById(R.id.name);
        holder.opp = (TextView) rowView.findViewById(R.id.opp);
        holder.o = (TextView) rowView.findViewById(R.id.o);
        holder.w = (TextView) rowView.findViewById(R.id.w);
        holder.r = (TextView) rowView.findViewById(R.id.r);
        holder.m = (TextView) rowView.findViewById(R.id.m);
        holder.er = (TextView) rowView.findViewById(R.id.er);

        BestBowlingModel temp = historyobject.get(i);

        holder.name.setText(temp.getName());
        holder.opp.setText(temp.getOpp());
        holder.o.setText(temp.getO());
        holder.w.setText(temp.getW());
        holder.r.setText(temp.getR());
        holder.m.setText(temp.getM());
        holder.er.setText(temp.getEr());
        return rowView;
    }
    }
