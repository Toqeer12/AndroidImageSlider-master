package WorldCupFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.koherent.pdlapps.cricketworldcup2015live.R;

import java.util.ArrayList;
import java.util.List;

import adapter.SearchContentAdapter;

/**
 * Created by TOQEER on 9/21/2015.
 */
public class Stats extends Fragment {
    SearchContentAdapter adapter;
    List<String> searchplayerobject;
    private ListView listView;
    public Stats() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statfrag, container,
                false);
        listView = (ListView) view.findViewById(R.id.searchlist);
        searchplayerobject = new ArrayList<String>();
        searchplayerobject.add("Best Bowling");
        searchplayerobject.add("Highest Indiviual Scores");
        searchplayerobject.add("Leading Run Score");
        searchplayerobject.add("Most Wickets");
        searchplayerobject.add("Most Catches");

        adapter=new SearchContentAdapter(getActivity(),searchplayerobject);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "You Clicked at " + i, Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.layoutfragment, new BestBowling());
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                } else if (i == 1) {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.layoutfragment, new HighestIndividual());
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }
                else if (i == 2) {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.layoutfragment, new LeadingRun());
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }
                else if (i == 3) {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.layoutfragment, new MostWickets());
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }
                else if (i == 4) {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.layoutfragment, new MostCatches());
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }


            }
        });
        return view;
    }
}
