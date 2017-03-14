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
public class MostCatchesAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    List<BestBowlingModel> historyobject;
    Context mContext;

    public MostCatchesAdapter(Context mContext, List<BestBowlingModel> historyobject) {
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
        TextView match;
        TextView catches;

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
        rowView = inflater.inflate(R.layout.mostcatchesadapter, null);
        holder.name = (TextView) rowView.findViewById(R.id.name);
        holder.match = (TextView) rowView.findViewById(R.id.matchwc);
        holder.catches = (TextView) rowView.findViewById(R.id.catcheswc);


        BestBowlingModel temp = historyobject.get(i);

        holder.name.setText(temp.getName());
        holder.match.setText(temp.getMatch());
        holder.catches.setText(temp.getCathces());


        return rowView;
    }
}
