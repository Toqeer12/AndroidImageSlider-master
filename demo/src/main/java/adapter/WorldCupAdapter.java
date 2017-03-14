package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import WorldCupFragment.FixtureFragment;
import WorldCupFragment.HistoryFragment;
import WorldCupFragment.LiveFragment;
import WorldCupFragment.PointTableFragment;
import WorldCupFragment.Result;
import WorldCupFragment.Stats;
import WorldCupFragment.VenueFragment;

/**
 * Created by TOQEER on 9/15/2015.
 */
public class WorldCupAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 7;
    private String tabTitles[] = new String[] { "Live","Result", "Fixture", "Point Table","History","Venue","STATS" };

    public WorldCupAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
            return new LiveFragment();
        else if(position==1)
            return new Result();
        else if(position==2)
            return new FixtureFragment();
        else if(position==3)
            return new PointTableFragment();
        else if (position==4)
            return new HistoryFragment();
        else if(position==5)
            return new VenueFragment();
        else
            return new Stats();
        //  return (position == 0)? new RankingFragment() : new SearchPlayerStat() ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

        return tabTitles[position];
        //return (position == 0)? "Info" : "Batting" :"Bowling" ;
    }
}
