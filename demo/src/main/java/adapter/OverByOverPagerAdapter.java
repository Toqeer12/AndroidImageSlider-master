package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import PageFragment.OverA1;
import PageFragment.OverA2;
import PageFragment.OverB1;
import PageFragment.OverB2;

/**
 * Created by TOQEER on 9/29/2015.
 */
public class OverByOverPagerAdapter extends FragmentPagerAdapter {
    public static int PAGE_COUNT = 4;
    public static String tabTitles[] = new String[]{"","","",""};

    public OverByOverPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new OverA1();
        else if(position==1)
            return new OverB1();
        else if(position==2)
            return new OverA2();
        else
            return new OverB2();


        //  return (position == 0)? new RankingFragment() : new SearchPlayerStat() ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

        return tabTitles[position];
        //return (position == 0)? "Info" : "Batting" :"Bowling" ;
    }
}
