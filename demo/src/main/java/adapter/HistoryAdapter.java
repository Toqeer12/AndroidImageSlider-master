package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.koherent.pdlapps.cricketworldcup2015live.R;

import java.util.List;

import Model.HistoryModel;

/**
 * Created by TOQEER on 9/11/2015.
 */
public class HistoryAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    List<HistoryModel> historyobject;
    Context mContext;

    public HistoryAdapter(Context mContext, List<HistoryModel> historyobject) {
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
        TextView title;


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = new Holder();


        View rowView;
        rowView = inflater.inflate(R.layout.historyadapter, null);
        holder.title = (TextView) rowView.findViewById(R.id.historytitle);
        HistoryModel temp=historyobject.get(i);
        holder.title.setText(temp.getTitle());

        return rowView;
    }
}