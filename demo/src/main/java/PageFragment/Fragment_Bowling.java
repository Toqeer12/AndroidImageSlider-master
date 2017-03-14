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
public class Fragment_Bowling extends Fragment {

    TextView test_bowl_match,test_bowl_innings,
    test_bowl_bbi,test_bowl_run,test_bowl_bbm,
    test_bowl_avg,test_bowl_ball,test_bowl_sr,
    test_bowl_hun,test_bowl_fifty,
    test_bowl_4s,test_bowl_6s,test_bowl_wickts,
    Odi_bowl_match,Odi_bowl_innings,
    Odi_bowl_no,Odi_bowl_run,t20_bowl_ball,
    Odi_bowl_avg,Odi_bowl_bf,Odi_bowl_sr,
    Odi_bowl_hun,Odi_bowl_fifty,
    Odi_bowl_4s,Odi_bowl_6s,
    t20_bowl_match,t20_bowl_innings,
            t20_bowl_bbi,t20_bowl_run,t20_bowl_bbm,
    t20_bowl_avg,t20_bowl_hun,t20_bowl_sr,
            t20_bowl_wickts,t20_bowl_fifty,
    t20_bowl_4s,t20_bowl_6s;
    private TextView Odi_bowl_bbi,Odi_bowl_bbm,Odi_bowl_wickts,Odi_bowl_ball;

    public Fragment_Bowling() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat_bowling, container,
                false);
        test_bowl_match=(TextView)view.findViewById(R.id.test_bowl_match);
        test_bowl_innings=(TextView)view.findViewById(R.id.test_bowl_innings);
        test_bowl_bbi=(TextView)view.findViewById(R.id.test_bowl_bbi);
        test_bowl_run=(TextView)view.findViewById(R.id.test_bowl_runs);
        test_bowl_bbm=(TextView)view.findViewById(R.id.test_bowl_bbm);
        test_bowl_avg=(TextView)view.findViewById(R.id.test_bowl_avg);
        test_bowl_sr=(TextView)view.findViewById(R.id.test_bowl_sr);
        test_bowl_hun=(TextView)view.findViewById(R.id.test_bowl_100);
        test_bowl_4s=(TextView)view.findViewById(R.id.test_bowl_4);
        test_bowl_fifty=(TextView)view.findViewById(R.id.test_bowl_50);
        test_bowl_6s=(TextView)view.findViewById(R.id.test_bowl_ecn);
        test_bowl_wickts=(TextView)view.findViewById(R.id.test_bowl_wickets);
        test_bowl_ball=(TextView)view.findViewById(R.id.test_bowl_balls);



            test_bowl_match.setText(FragmentInfo.TestBowlmatch);
            test_bowl_innings.setText(FragmentInfo.TestBowlInnings);
            test_bowl_run.setText(FragmentInfo.TestBowlRuns);
            test_bowl_avg.setText(FragmentInfo.TestBowlAvg);
            test_bowl_sr.setText(FragmentInfo.TestBowlSr);
            test_bowl_hun.setText(FragmentInfo.TestBowl10w);
            test_bowl_fifty.setText(FragmentInfo.TestBowl5w);
            test_bowl_4s.setText(FragmentInfo.TestBowl4w);
            test_bowl_ball.setText(FragmentInfo.TestBowls);
            test_bowl_wickts.setText(FragmentInfo.TestBowlWickts);
            test_bowl_bbi.setText(FragmentInfo.TestBBI);
            test_bowl_bbm.setText(FragmentInfo.TestBBM);
            test_bowl_6s.setText(FragmentInfo.TestBowlEcn);

        Odi_bowl_bbi=(TextView)view.findViewById(R.id.odi_bowl_bbi);

        Odi_bowl_bbm=(TextView)view.findViewById(R.id.odi_bowl_bbm);
        Odi_bowl_6s=(TextView)view.findViewById(R.id.odi_bowl_ecn);
        Odi_bowl_wickts=(TextView)view.findViewById(R.id.odi_bowl_wickets);
        Odi_bowl_ball=(TextView)view.findViewById(R.id.odi_bowl_balls);
        Odi_bowl_match=(TextView)view.findViewById(R.id.Odi_bowl_match);
        Odi_bowl_innings=(TextView)view.findViewById(R.id.Odi_bowl_innings);
        Odi_bowl_run=(TextView)view.findViewById(R.id.odi_bowl_runs);
        Odi_bowl_avg=(TextView)view.findViewById(R.id.odi_bowl_avg);
        Odi_bowl_sr=(TextView)view.findViewById(R.id.odi_bowl_sr);
        Odi_bowl_4s=(TextView)view.findViewById(R.id.odi_bowl_4);
        Odi_bowl_fifty=(TextView)view.findViewById(R.id.odi_bowl_50);
        Odi_bowl_hun=(TextView)view.findViewById(R.id.odi_bowl_100);
        Odi_bowl_match.setText(FragmentInfo.OdiBowlmatch);
        Odi_bowl_innings.setText(FragmentInfo.OdiBowlInnings);
        Odi_bowl_ball.setText(FragmentInfo.OdiBowls);
        Odi_bowl_wickts.setText(FragmentInfo.OdiBowlWickts);
        Odi_bowl_bbi.setText(FragmentInfo.OdiBBI);
        Odi_bowl_bbm.setText(FragmentInfo.OdiBBM);
        Odi_bowl_hun.setText(FragmentInfo.OdiBowl10w);
        Odi_bowl_fifty.setText(FragmentInfo.OdiBowl5w);
        Odi_bowl_4s.setText(FragmentInfo.OdiBowl4w);
        Odi_bowl_avg.setText(FragmentInfo.OdiBowlAvg);
        Odi_bowl_6s.setText(FragmentInfo.OdiBowlEcn);
        Odi_bowl_sr.setText(FragmentInfo.OdiBowlSr);
        Odi_bowl_run.setText(FragmentInfo.OdiBowlRuns);


        t20_bowl_bbi=(TextView)view.findViewById(R.id.t20_bowl_bbi);
        t20_bowl_bbm=(TextView)view.findViewById(R.id.t20_bowl_bbm);
        t20_bowl_6s=(TextView)view.findViewById(R.id.t20_bowl_ecn);
        t20_bowl_wickts=(TextView)view.findViewById(R.id.t20_bowl_wickets);
        t20_bowl_ball=(TextView)view.findViewById(R.id.t20_bowl_balls);
        t20_bowl_match=(TextView)view.findViewById(R.id.t20_bowl_match);
        t20_bowl_innings=(TextView)view.findViewById(R.id.t20_bowl_innings);
        t20_bowl_run=(TextView)view.findViewById(R.id.t20_bowl_runs);
        t20_bowl_avg=(TextView)view.findViewById(R.id.t20_bowl_avg);
        t20_bowl_sr=(TextView)view.findViewById(R.id.t20_bowl_sr);
        t20_bowl_4s=(TextView)view.findViewById(R.id.t20_bowl_4);
        t20_bowl_fifty=(TextView)view.findViewById(R.id.t20_bowl_50);
        t20_bowl_hun=(TextView)view.findViewById(R.id.t20_bowl_100);

        t20_bowl_run.setText(FragmentInfo.T20BowlRuns);
        t20_bowl_match.setText(FragmentInfo.T20Bowlmatch);
        t20_bowl_innings.setText(FragmentInfo.T20BowlInnings);
        t20_bowl_ball.setText(FragmentInfo.T20Bowls);
        t20_bowl_wickts.setText(FragmentInfo.T20BowlWickts);
        t20_bowl_bbi.setText(FragmentInfo.T20BBI);
        t20_bowl_bbm.setText(FragmentInfo.T20BBM);
        t20_bowl_hun.setText(FragmentInfo.T20Bowl10w);
        t20_bowl_fifty.setText(FragmentInfo.T20Bowl5w);
        t20_bowl_4s.setText(FragmentInfo.T20Bowl4w);
        t20_bowl_avg.setText(FragmentInfo.T20BowlAvg);
        t20_bowl_6s.setText(FragmentInfo.T20BowlEcn);
        t20_bowl_sr.setText(FragmentInfo.T20BowlSr);
        return view;
    }
}
