package PageFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koherent.pdlapps.cricketworldcup2015live.PlayerStatDetail;
import com.koherent.pdlapps.cricketworldcup2015live.R;

/**
 * Created by TOQEER on 8/19/2015.
 */
public class Fragment_Batting extends Fragment {

    TextView
            test_bat_match,test_bat_innings,
            test_bat_no,test_bat_run,test_bat_highest,
            test_bat_avg,test_bat_bf,test_bat_sr,
            test_bat_hun,test_bat_fifty,
            test_bat_4s,test_bat_6s,
    Odi_bat_match,Odi_bat_innings,
    Odi_bat_no,Odi_bat_run,Odi_bat_highest,
    Odi_bat_avg,Odi_bat_bf,Odi_bat_sr,
    Odi_bat_hun,Odi_bat_fifty,
    Odi_bat_4s,Odi_bat_6s,
            t20_bat_match,t20_bat_innings,
            t20_bat_no,t20_bat_run,t20_bat_highest,
            t20_bat_avg,t20_bat_bf,t20_bat_sr,
            t20_bat_hun,t20_bat_fifty,
            t20_bat_4s,t20_bat_6s;

    public Fragment_Batting() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat_batting, container,
                false);

        test_bat_match=(TextView)view.findViewById(R.id.test_bat_match);
        test_bat_innings=(TextView)view.findViewById(R.id.test_bat_innings);
        test_bat_no=(TextView)view.findViewById(R.id.test_bat_no);
        test_bat_run=(TextView)view.findViewById(R.id.test_bat_run);
        test_bat_highest=(TextView)view.findViewById(R.id.test_bat_highest);
        test_bat_avg=(TextView)view.findViewById(R.id.test_bat_avg);
        test_bat_bf=(TextView)view.findViewById(R.id.test_bat_bf);
        test_bat_sr=(TextView)view.findViewById(R.id.test_bat_sr);
        test_bat_hun=(TextView)view.findViewById(R.id.test_bat_hun);
        test_bat_4s=(TextView)view.findViewById(R.id.test_bat_four);
        test_bat_fifty=(TextView)view.findViewById(R.id.test_bat_50);
        test_bat_6s=(TextView)view.findViewById(R.id.test_bat_six);


        Odi_bat_match=(TextView)view.findViewById(R.id.Odi_bat_match);
        Odi_bat_innings=(TextView)view.findViewById(R.id.Odi_bat_innings);
        Odi_bat_no=(TextView)view.findViewById(R.id.Odi_bat_no);
        Odi_bat_run=(TextView)view.findViewById(R.id.Odi_bat_run);
        Odi_bat_highest=(TextView)view.findViewById(R.id.Odi_bat_highest);
        Odi_bat_avg=(TextView)view.findViewById(R.id.Odi_bat_avg);
        Odi_bat_bf=(TextView)view.findViewById(R.id.Odi_bat_bf);
        Odi_bat_sr=(TextView)view.findViewById(R.id.Odi_bat_sr);
        Odi_bat_hun=(TextView)view.findViewById(R.id.Odi_bat_hun);
        Odi_bat_4s=(TextView)view.findViewById(R.id.Odi_bat_four);
        Odi_bat_fifty=(TextView)view.findViewById(R.id.Odi_bat_50);
        Odi_bat_6s=(TextView)view.findViewById(R.id.Odi_bat_six);

        t20_bat_match=(TextView)view.findViewById(R.id.t20_bat_match);
        t20_bat_innings=(TextView)view.findViewById(R.id.t20_bat_innings);
        t20_bat_no=(TextView)view.findViewById(R.id.t20_bat_no);
        t20_bat_run=(TextView)view.findViewById(R.id.t20_bat_runs);
        t20_bat_highest=(TextView)view.findViewById(R.id.t20_bat_highest);
        t20_bat_avg=(TextView)view.findViewById(R.id.t20_bat_avg);
        t20_bat_bf=(TextView)view.findViewById(R.id.t20_bat_bf);
        t20_bat_sr=(TextView)view.findViewById(R.id.t20_bat_sr);
        t20_bat_hun=(TextView)view.findViewById(R.id.t20_bat_100);
        t20_bat_4s=(TextView)view.findViewById(R.id.t20_bat_4s);
        t20_bat_fifty=(TextView)view.findViewById(R.id.t20_bat_50);
        t20_bat_6s=(TextView)view.findViewById(R.id.t20_bat_6s);

        test_bat_match.setText(FragmentInfo.TestBatMatch);
        test_bat_innings.setText(FragmentInfo.TestBatInnings);
        test_bat_no.setText(FragmentInfo.TestBatNotOut);
        test_bat_run.setText(FragmentInfo.TestBatRun);
        test_bat_highest.setText(FragmentInfo.TestBatHighest);
        test_bat_avg.setText(FragmentInfo.TestBatAvg);
        test_bat_bf.setText(FragmentInfo.TestBatFaced);
        test_bat_sr.setText(FragmentInfo.TestBatSr);
        test_bat_hun.setText(FragmentInfo.TestBatundered);
        test_bat_6s.setText(FragmentInfo.TestBatSix);
        test_bat_4s.setText(FragmentInfo.TestBatFour);
        test_bat_fifty.setText(FragmentInfo.TestBatFifity);

        Odi_bat_match.setText(FragmentInfo.OdiBatMatch);
        Odi_bat_innings.setText(FragmentInfo.OdiBatInnings);
        Odi_bat_no.setText(FragmentInfo.OdiBatNotOut);
        Odi_bat_run.setText(FragmentInfo.OdiBatRun);
        Odi_bat_highest.setText(FragmentInfo.OdiBatHighest);
        Odi_bat_avg.setText(FragmentInfo.OdiBatAvg);
        Odi_bat_bf.setText(FragmentInfo.OdiBatBallFaced);
        Odi_bat_sr.setText(FragmentInfo.OdiBatSr);
        Odi_bat_hun.setText(FragmentInfo.OdiBatundered);
        Odi_bat_6s.setText(FragmentInfo.OdiBatSix);
        Odi_bat_4s.setText(FragmentInfo.OdiBatFour);
        Odi_bat_fifty.setText(FragmentInfo.OdiBatFifity);


        t20_bat_match.setText(FragmentInfo.t20BatMatch);
        t20_bat_innings.setText(FragmentInfo.t20BatInnings);
        t20_bat_no.setText(FragmentInfo.t20BatNotOut);
        t20_bat_run.setText(FragmentInfo.t20BatRun);
        t20_bat_highest.setText(FragmentInfo.t20BatHighest);
        t20_bat_avg.setText(FragmentInfo.t20BatAvg);
        t20_bat_bf.setText(FragmentInfo.t20BatBallFaced);
        t20_bat_sr.setText(FragmentInfo.t20BatSr);
        t20_bat_hun.setText(FragmentInfo.t20Batundered);
        t20_bat_6s.setText(FragmentInfo.t20BatSix);
        t20_bat_4s.setText(FragmentInfo.t20BatFour);
        t20_bat_fifty.setText(FragmentInfo.t20BatFifity);
        return view;
    }
}
