package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import PageFragment.FallOFWickets_Team1;
import PageFragment.FallOFWickets_Team2;

/**
 * Created by TOQEER on 9/9/2015.
 */
public class PagerAdapterFallofWickets extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    public static String tabTitles[] = new String[]{"", ""};

    public PagerAdapterFallofWickets(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new FallOFWickets_Team1();
        else
            return new FallOFWickets_Team2();


        //  return (position == 0)? new RankingFragment() : new SearchPlayerStat() ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

        return tabTitles[position];
        //return (position == 0)? "Info" : "Batting" :"Bowling" ;
    }
}
