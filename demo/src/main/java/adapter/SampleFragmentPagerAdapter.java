package adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import PageFragment.FragmentODI;
import PageFragment.FragmentT20;
import PageFragment.FragmentTest;


/**
 * Created by TOQEER on 8/10/2015.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "TEST", "ODI", "T20" };

    public SampleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
            return new FragmentTest();
        else if(position==1)
        return new FragmentODI();
        else
            return new FragmentT20();

      //  return (position == 0)? new RankingFragment() : new SearchPlayerStat() ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

       return tabTitles[position];
        //return (position == 0)? "Info" : "Batting" :"Bowling" ;
    }
}
