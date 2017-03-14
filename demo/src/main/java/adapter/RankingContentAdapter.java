package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.koherent.pdlapps.cricketworldcup2015live.R;
import java.util.List;

/**
 * Created by TOQEER on 8/17/2015.
 */
public class RankingContentAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    List<String> searchplayerlist;
    Context mContext;
    public RankingContentAdapter(Context mContext, List<String> searchplayerlist) {
        this.mContext=mContext;
        this.searchplayerlist=searchplayerlist;
        inflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return searchplayerlist.size();
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
        rowView = inflater.inflate(R.layout.content_adapter, null);
        holder.title = (TextView) rowView.findViewById(R.id.contenttitle);
        holder.title.setText(searchplayerlist.get(i));

        return rowView;
    }
}
