package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.koherent.pdlapps.cricketworldcup2015live.R;

import java.util.ArrayList;

import ImageDownloader.ImageLoader;
import Model.fallwicketsitem;

/**
 * Created by TOQEER on 9/15/2015.
 */
public class OverAdapter extends BaseAdapter {

    ArrayList<fallwicketsitem> object;
    Context context;
    private static LayoutInflater inflater = null;
    private ImageLoader imageLoader;
    public OverAdapter(Context context, ArrayList<fallwicketsitem> object) {
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
        TextView ball1;
        TextView ball2;
        TextView ball3;
        TextView ball4;
        TextView ball5;
        TextView ball6;
        TextView ball7;
        TextView overno;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();


        View rowView;
        String slp;
        rowView = inflater.inflate(R.layout.overadapter, null);

        holder.ball1 = (TextView) rowView.findViewById(R.id.name);
        holder.ball2 = (TextView) rowView.findViewById(R.id.run);
        holder.ball3 = (TextView) rowView.findViewById(R.id.overstre);
        holder.ball4 = (TextView) rowView.findViewById(R.id.a);
        holder.ball5 = (TextView) rowView.findViewById(R.id.b);
        holder.ball6 = (TextView) rowView.findViewById(R.id.c);
        holder.ball7 = (TextView) rowView.findViewById(R.id.d);
        holder.overno = (TextView) rowView.findViewById(R.id.overno);


        holder.ball1.setVisibility(View.GONE);
        holder.ball2.setVisibility(View.GONE);
        holder.ball3.setVisibility(View.GONE);
        holder.ball4.setVisibility(View.GONE);
        holder.ball5.setVisibility(View.GONE);
        holder.ball6.setVisibility(View.GONE);
        holder.ball7.setVisibility(View.GONE);
        //holder.overno.setVisibility(View.GONE);

        if (object.size() == 0) {
          //  Toast.makeText(context,"Loading Data",Toast.LENGTH_LONG).show();
            //object.clear();
        } else {


            fallwicketsitem temp = object.get(position);

            holder.overno.setText("Over No: "+temp.overno);

            if (temp.ball1.equalsIgnoreCase("0")) {
                holder.ball1.setVisibility(View.GONE);
            } else if (temp.ball2.equalsIgnoreCase("0")) {
                holder.ball2.setVisibility(View.GONE);
            } else if (temp.ball3.equalsIgnoreCase("0")) {
                holder.ball2.setVisibility(View.GONE);
            } else if (temp.ball3.equalsIgnoreCase("0")) {
                holder.ball3.setVisibility(View.GONE);
            } else if (temp.ball4.equalsIgnoreCase("0")) {
                holder.ball4.setVisibility(View.GONE);
            } else if (temp.ball5.equalsIgnoreCase("0")) {
                holder.ball5.setVisibility(View.GONE);
            } else if (temp.ball6.equalsIgnoreCase("0")) {
                holder.ball6.setVisibility(View.GONE);
            }

            if (temp.ball1.startsWith("e")) {
                String[] splitted = temp.ball1.split(",");
                //      slp= temp.ball1.substring(temp.ball1.length() - 1);
                holder.ball1.setVisibility(View.VISIBLE);
                Log.d("Response Happ", splitted[0]);
                holder.ball1.setText(splitted[0]);
            }

            if (temp.ball1.startsWith("r")) {
                slp = temp.ball1.substring(temp.ball1.length() - 1);
                holder.ball1.setVisibility(View.VISIBLE);
                Log.d("Response Happ", slp);
                holder.ball1.setText(slp);
            } else if (temp.ball1.startsWith("b")) {
                slp = temp.ball1.substring(temp.ball1.length() - 1);
                Log.d("Response Happ", slp);
                if (slp.equalsIgnoreCase("4")) {
                    holder.ball1.setVisibility(View.VISIBLE);
                    holder.ball1.setBackgroundResource(R.drawable.four);
                    //   holder.ball1.setText(slp);
                } else {
                    holder.ball1.setVisibility(View.VISIBLE);
                    holder.ball1.setBackgroundResource(R.drawable.six);
                    //holder.ball1.setText(slp);
                }
            } else if (temp.ball1.startsWith("w")) {
                holder.ball1.setVisibility(View.VISIBLE);
                holder.ball1.setBackgroundResource(R.drawable.out);
                //  holder.ball1.setText(temp.ball1);
            } else if (temp.ball1.startsWith("e")) {
                String[] splitted = temp.ball1.split(",");
                //      slp= temp.ball1.substring(temp.ball1.length() - 1);
                holder.ball1.setVisibility(View.VISIBLE);
                Log.d("Response Happ", splitted[0]);
                holder.ball1.setText(splitted[0]);
            }


            if (temp.ball2.startsWith("r")) {
                holder.ball2.setVisibility(View.VISIBLE);
                slp = temp.ball2.substring(temp.ball2.length() - 1);
                Log.d("Response Happ", slp);
                holder.ball2.setText(slp);
            } else if (temp.ball2.startsWith("b")) {
                slp = temp.ball2.substring(temp.ball2.length() - 1);
                Log.d("Response Happ", slp);
                if (slp.equalsIgnoreCase("4")) {
                    holder.ball2.setVisibility(View.VISIBLE);
                    holder.ball2.setBackgroundResource(R.drawable.four);
                    //     holder.ball2.setText(slp);
                } else {
                    holder.ball2.setVisibility(View.VISIBLE);
                    holder.ball2.setBackgroundResource(R.drawable.six);
                    //     holder.ball2.setText(slp);
                }
            } else if (temp.ball2.startsWith("w")) {
                holder.ball2.setVisibility(View.VISIBLE);
                holder.ball2.setBackgroundResource(R.drawable.out);
                // holder.ball2.setText(temp.ball1);
            } else if (temp.ball2.startsWith("e")) {
                String[] splitted = temp.ball2.split(",");
                //      slp= temp.ball1.substring(temp.ball1.length() - 1);
                holder.ball2.setVisibility(View.VISIBLE);
                Log.d("Response Happ", splitted[0]);
                holder.ball2.setText(splitted[0]);
            }


            if (temp.ball3.startsWith("r")) {
                holder.ball3.setVisibility(View.VISIBLE);
                slp = temp.ball3.substring(temp.ball3.length() - 1);
                Log.d("Response Happ", slp);
                holder.ball3.setText(slp);
            } else if (temp.ball3.startsWith("b")) {
                slp = temp.ball3.substring(temp.ball3.length() - 1);
                Log.d("Response Happ", slp);
                if (slp.equalsIgnoreCase("4")) {
                    holder.ball3.setVisibility(View.VISIBLE);
                    holder.ball3.setBackgroundResource(R.drawable.four);
                    // holder.ball3.setText(slp);
                } else {
                    holder.ball3.setVisibility(View.VISIBLE);
                    holder.ball3.setBackgroundResource(R.drawable.six);
                    // holder.ball3.setText(slp);
                }
            } else if (temp.ball3.startsWith("w")) {
                holder.ball3.setVisibility(View.VISIBLE);
                holder.ball3.setBackgroundResource(R.drawable.out);
                // holder.ball3.setText(temp.ball1);
            } else if (temp.ball3.startsWith("e")) {
                String[] splitted = temp.ball3.split(",");
                //      slp= temp.ball1.substring(temp.ball1.length() - 1);
                holder.ball3.setVisibility(View.VISIBLE);
                Log.d("Response Happ", splitted[0]);
                holder.ball3.setText(splitted[0]);
            }


            if (temp.ball4.startsWith("r")) {
                holder.ball4.setVisibility(View.VISIBLE);
                slp = temp.ball4.substring(temp.ball4.length() - 1);
                Log.d("Response Happ", slp);
                holder.ball4.setText(slp);
            } else if (temp.ball4.startsWith("b")) {
                slp = temp.ball4.substring(temp.ball4.length() - 1);
                Log.d("Response Happ", slp);
                if (slp.equalsIgnoreCase("4")) {
                    holder.ball4.setVisibility(View.VISIBLE);
                    holder.ball4.setBackgroundResource(R.drawable.four);
                    //     holder.ball4.setText(slp);
                } else {
                    holder.ball4.setVisibility(View.VISIBLE);
                    holder.ball4.setBackgroundResource(R.drawable.six);
                    //    holder.ball4.setText(slp);
                }
            } else if (temp.ball4.startsWith("w")) {
                holder.ball4.setVisibility(View.VISIBLE);
                holder.ball4.setBackgroundResource(R.drawable.out);
                // holder.ball4.setText(temp.ball1);
            } else if (temp.ball4.startsWith("e")) {
                String[] splitted = temp.ball4.split(",");
                //      slp= temp.ball1.substring(temp.ball1.length() - 1);
                holder.ball4.setVisibility(View.VISIBLE);
                Log.d("Response Happ", splitted[0]);
                holder.ball4.setText(splitted[0]);
            }

            if (temp.ball5.startsWith("r")) {
                holder.ball5.setVisibility(View.VISIBLE);
                slp = temp.ball5.substring(temp.ball5.length() - 1);
                Log.d("Response Happ", slp);
                holder.ball5.setText(slp);
            } else if (temp.ball5.startsWith("b")) {
                slp = temp.ball5.substring(temp.ball5.length() - 1);
                Log.d("Response Happ", slp);
                if (slp.equalsIgnoreCase("4")) {
                    holder.ball5.setVisibility(View.VISIBLE);
                    holder.ball5.setBackgroundResource(R.drawable.four);
                    //     holder.ball5.setText(slp);
                } else {
                    holder.ball5.setVisibility(View.VISIBLE);
                    holder.ball5.setBackgroundResource(R.drawable.six);
                    //    holder.ball5.setText(slp);
                }
            } else if (temp.ball5.startsWith("w")) {
                holder.ball5.setVisibility(View.VISIBLE);
                holder.ball5.setBackgroundResource(R.drawable.out);
                // holder.ball5.setText(temp.ball1);
            } else if (temp.ball5.startsWith("e")) {
                String[] splitted = temp.ball5.split(",");
                //      slp= temp.ball1.substring(temp.ball1.length() - 1);
                holder.ball5.setVisibility(View.VISIBLE);
                Log.d("Response Happ", splitted[0]);
                holder.ball5.setText(splitted[0]);
            }

            if (temp.ball6.startsWith("r")) {
                holder.ball6.setVisibility(View.VISIBLE);
                slp = temp.ball6.substring(temp.ball6.length() - 1);
                Log.d("Response Happ", slp);
                holder.ball6.setText(slp);
            } else if (temp.ball6.startsWith("b")) {
                slp = temp.ball6.substring(temp.ball6.length() - 1);
                Log.d("Response Happ", slp);
                if (slp.equalsIgnoreCase("4")) {
                    holder.ball6.setVisibility(View.VISIBLE);
                    holder.ball6.setBackgroundResource(R.drawable.four);
                    //    holder.ball6.setText(slp);
                } else {
                    holder.ball6.setVisibility(View.VISIBLE);
                    holder.ball6.setBackgroundResource(R.drawable.six);
                   // holder.ball6.setText(slp);
                }
            } else if (temp.ball6.startsWith("w")) {
                holder.ball6.setVisibility(View.VISIBLE);
                holder.ball6.setBackgroundResource(R.drawable.out);
                //   holder.ball6.setText(temp.ball1);
            } else if (temp.ball6.startsWith("e")) {
                String[] splitted = temp.ball6.split(",");
                //      slp= temp.ball1.substring(temp.ball1.length() - 1);
                holder.ball6.setVisibility(View.VISIBLE);
                Log.d("Response Happ", splitted[0]);
                holder.ball6.setText(splitted[0]);
            }
        }
//        holder.over.setText(temp.over);
            return rowView;

        }

}
