package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import PageFragment.FragmentPlayerODI;
import PageFragment.FragmentPlayerT20;
import PageFragment.FragmentPlayerTest;

/**
 * Created by TOQEER on 8/18/2015.
 */
public class PlayerFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "TEST", "ODI", "T20" };

    public PlayerFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
            return new FragmentPlayerTest();
        else if(position==1)
            return new FragmentPlayerODI();
        else
            return new FragmentPlayerT20();

        //  return (position == 0)? new RankingFragment() : new SearchPlayerStat() ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

        return tabTitles[position];
        //return (position == 0)? "Info" : "Batting" :"Bowling" ;
    }
}
