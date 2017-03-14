package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import PageFragment.FallOFWicketTest1_Team1;
import PageFragment.FallOFWicketTest1_Team2;
import PageFragment.FallOFWicketTest2_Team1;
import PageFragment.FallOFWicketTest2_Team2;

/**
 * Created by TOQEER on 9/17/2015.
 */
public class PagerAdapterFallofWicketsTest extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    public static String tabTitles[] = new String[]{"","","",""};

    public PagerAdapterFallofWicketsTest(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new FallOFWicketTest1_Team1();
        else if(position==1)
            return new FallOFWicketTest1_Team2();
        else if(position==2)
            return new FallOFWicketTest2_Team1();
        else
            return new FallOFWicketTest2_Team2();


        //  return (position == 0)? new RankingFragment() : new SearchPlayerStat() ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

        return tabTitles[position];
        //return (position == 0)? "Info" : "Batting" :"Bowling" ;
    }
}
