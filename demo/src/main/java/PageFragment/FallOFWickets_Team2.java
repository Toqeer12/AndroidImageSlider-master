package PageFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.koherent.pdlapps.cricketworldcup2015live.R;

/**
 * Created by TOQEER on 9/9/2015.
 */
public class FallOFWickets_Team2 extends Fragment {
    public static ListView battingTeamTwoListView;
    public FallOFWickets_Team2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.team2_list, container,
                false);
        battingTeamTwoListView=(ListView)view.findViewById(R.id.teamOneListView3);
//        battingTeamTwoListView.setAdapter(FullCard_Team1.adapterBattingTeamTwo);
        return view;

    }
}
