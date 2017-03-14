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

import java.util.List;

import Model.HistoryModel;

/**
 * Created by TOQEER on 9/11/2015.
 */
public class HistoryDetailAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    List<HistoryModel> historyobject;
    Context mContext;

    public HistoryDetailAdapter(Context mContext, List<HistoryModel> historyobject) {
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
        TextView year;
        TextView winner;
        TextView Runner;
        TextView wins;
        TextView runs;
        TextView wino;
        TextView runso;
    }
    public class ImageHolder{
        ImageView flag1;
        ImageView flag2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = new Holder();
        ImageHolder hold=new ImageHolder();
    
        View rowView;
        rowView = inflater.inflate(R.layout.historydetailadapter, null);
        holder.year = (TextView) rowView.findViewById(R.id.year);
        holder.winner = (TextView) rowView.findViewById(R.id.Winner);
        holder.Runner = (TextView) rowView.findViewById(R.id.runner);
        holder.runs = (TextView) rowView.findViewById(R.id.runs);
        holder.wino = (TextView) rowView.findViewById(R.id.winso);
        holder.runso=(TextView)rowView.findViewById(R.id.runso);
        holder.wins = (TextView) rowView.findViewById(R.id.wins);
        hold.flag1=(ImageView)rowView.findViewById(R.id.country1);
        hold.flag2=(ImageView)rowView.findViewById(R.id.country2);
        HistoryModel temp=historyobject.get(i);
        holder.year.setText(temp.getYear());
        holder.winner.setText(temp.getWinner());
        holder.Runner.setText(temp.getRunner());
        holder.runs.setText(temp.getRunnerrun());
        holder.wins.setText(temp.getWinnerrun());
        holder.wino.setText(temp.getWino());
        holder.runso.setText(temp.getRuno());

            Log.d("Runner",temp.getRunner()+""+temp.getWinner());

        if(temp.getWinner().equalsIgnoreCase("India"))
        {
            //Log.d("ABCdddd",a);
            hold.flag1.setImageResource(R.drawable.india);
        }

        else if(temp.getWinner().equalsIgnoreCase("Pakistan"))
        {
            hold.flag1.setImageResource(R.drawable.pakistan);

        }
        else if(temp.getWinner().equalsIgnoreCase("England"))
        {
            hold.flag1.setImageResource(R.drawable.england);

        }
        else if(temp.getWinner().equalsIgnoreCase("Sri Lanka"))
        {
            hold.flag1.setImageResource(R.drawable.sri_lanka);

        }
        else if(temp.getWinner().equalsIgnoreCase("West Indies"))
        {
            hold.flag1.setImageResource(R.drawable.west_indies);

        }
        else if(temp.getWinner().equalsIgnoreCase("Zimbabwe"))
        {
            hold.flag1.setImageResource(R.drawable.zimbabwe);

        }
        else if(temp.getWinner().equalsIgnoreCase("Bangladesh"))
        {
            hold.flag1.setImageResource(R.drawable.bangladesh);

        }

        else if(temp.getWinner().equalsIgnoreCase("Afghanistan"))
        {
            hold.flag1.setImageResource(R.drawable.afghanistan);

        }

        else if(temp.getWinner().equalsIgnoreCase("Australia")) {
            hold.flag1.setImageResource(R.drawable.australia);

        }

        else if(temp.getWinner().equalsIgnoreCase("New Zealand"))
        {
            hold.flag1.setImageResource(R.drawable.new_zealand);

        }
        else if(temp.getWinner().equalsIgnoreCase("South Africa"))
        {
            hold.flag1.setImageResource(R.drawable.south_africa);

        }
        else if(temp.getWinner().equalsIgnoreCase("KXIP"))
        {
            hold.flag1.setImageResource(R.drawable.kings_punjab);

        }
        else if(temp.getWinner().equalsIgnoreCase("MI"))
        {
            hold.flag1.setImageResource(R.drawable.mumbai_indians);

        }
        else if(temp.getWinner().equalsIgnoreCase("SRH"))
        {
            hold.flag1.setImageResource(R.drawable.sunrisers_hyderabad);

        }
        else if(temp.getWinner().equalsIgnoreCase("RR"))
        {
            hold.flag1.setImageResource(R.drawable.rajasthan_royals);

        }
        else if(temp.getWinner().equalsIgnoreCase("CSK"))
        {
            hold.flag1.setImageResource(R.drawable.chennai_super_kings);

        }
        else if(temp.getWinner().equalsIgnoreCase("RCB"))
        {
            hold.flag1.setImageResource(R.drawable.royal_challengers_bangalore);

        }
        else if(temp.getWinner().equalsIgnoreCase("Ireland"))
        {
            hold.flag1.setImageResource(R.drawable.ireland);

        }



        else if(temp.getWinner().equalsIgnoreCase("Scotland"))
        {
            hold.flag1.setImageResource(R.drawable.scotland);

        }

        else if(temp.getWinner().equalsIgnoreCase("Kenya"))
        {
            hold.flag1.setImageResource(R.drawable.kenya);

        }

        else if(temp.getWinner().equalsIgnoreCase("Netherlands"))
        {
            hold.flag1.setImageResource(R.drawable.netherlands);

        }


        if(temp.getRunner().equalsIgnoreCase("India"))
        {
            //Log.d("ABCdddd",a);
            hold.flag2.setImageResource(R.drawable.india);
        }

        else if(temp.getRunner().equalsIgnoreCase("Pakistan"))
        {
            hold.flag2.setImageResource(R.drawable.pakistan);

        }
        else if(temp.getRunner().equalsIgnoreCase("England"))
        {
            hold.flag2.setImageResource(R.drawable.england);

        }
        else if(temp.getRunner().equalsIgnoreCase("Sri Lanka"))
        {
            hold.flag2.setImageResource(R.drawable.sri_lanka);

        }
        else if(temp.getRunner().equalsIgnoreCase("West Indies"))
        {
            hold.flag2.setImageResource(R.drawable.west_indies);

        }
        else if(temp.getRunner().equalsIgnoreCase("Zimbabwe"))
        {
            hold.flag2.setImageResource(R.drawable.zimbabwe);

        }
        else if(temp.getRunner().equalsIgnoreCase("Bangladesh"))
        {
            hold.flag2.setImageResource(R.drawable.bangladesh);

        }
        else if(temp.getRunner().equalsIgnoreCase("KXIP"))
        {
            hold.flag2.setImageResource(R.drawable.kings_punjab);

        }
        else if(temp.getRunner().equalsIgnoreCase("MI"))
        {
            hold.flag2.setImageResource(R.drawable.mumbai_indians);

        }
        else if(temp.getRunner().equalsIgnoreCase("SRH"))
        {
            hold.flag2.setImageResource(R.drawable.sunrisers_hyderabad);

        }
        else if(temp.getRunner().equalsIgnoreCase("RR"))
        {
            hold.flag2.setImageResource(R.drawable.rajasthan_royals);

        }
        else if(temp.getRunner().equalsIgnoreCase("CSK"))
        {
            hold.flag2.setImageResource(R.drawable.chennai_super_kings);

        }
        else if(temp.getRunner().equalsIgnoreCase("RCB"))
        {
            hold.flag2.setImageResource(R.drawable.royal_challengers_bangalore);

        }
        else if(temp.getRunner().equalsIgnoreCase("Afghanistan"))
        {
            hold.flag2.setImageResource(R.drawable.afghanistan);

        }

        else if(temp.getRunner().equalsIgnoreCase("Australia")) {
            hold.flag2.setImageResource(R.drawable.australia);

        }

        else if(temp.getRunner().equalsIgnoreCase("New Zealand"))
        {
            hold.flag2.setImageResource(R.drawable.new_zealand);

        }
        else if(temp.getRunner().equalsIgnoreCase("South Africa"))
        {
            hold.flag2.setImageResource(R.drawable.south_africa);

        }

        else if(temp.getRunner().equalsIgnoreCase("Ireland"))
        {
            hold.flag2.setImageResource(R.drawable.ireland);

        }



        else if(temp.getRunner().equalsIgnoreCase("Scotland"))
        {
            hold.flag2.setImageResource(R.drawable.scotland);

        }

        else if(temp.getRunner().equalsIgnoreCase("Kenya"))
        {
            hold.flag2.setImageResource(R.drawable.kenya);

        }

        else if(temp.getRunner().equalsIgnoreCase("Netherlands"))
        {
            hold.flag2.setImageResource(R.drawable.netherlands);

        }
        return rowView;
    }
}
