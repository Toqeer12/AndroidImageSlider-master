package PageFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.koherent.pdlapps.cricketworldcup2015live.R;

/**
 * Created by TOQEER on 8/18/2015.
 */
public class FragmentPlayerT20 extends Fragment {


   public static ListView playert20list;
    public FragmentPlayerT20() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_player_t20, container,
                false);
        playert20list=(ListView)view.findViewById(R.id.fragmentt20listplayer);
        playert20list.setAdapter(FragmentPlayerTest.adapter);
        return view;
    }
}
