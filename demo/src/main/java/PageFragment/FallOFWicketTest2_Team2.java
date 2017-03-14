package PageFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.koherent.pdlapps.cricketworldcup2015live.R;

/**
 * Created by TOQEER on 9/16/2015.
 */
public class FallOFWicketTest2_Team2 extends Fragment {
    public static ListView battingTeamforListView;
    public FallOFWicketTest2_Team2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fallwicket4, container,
                false);
        battingTeamforListView=(ListView)view.findViewById(R.id.teamforListView2);
//        battingTeamTwoListView.setAdapter(FullCard_Team1.adapterBattingTeamTwo);
        return view;

    }
}
