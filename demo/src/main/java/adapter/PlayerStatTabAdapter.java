package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import PageFragment.FragmentInfo;
import PageFragment.Fragment_Batting;
import PageFragment.Fragment_Bowling;
import PageFragment.Fragment_Career;

/**
 * Created by TOQEER on 8/17/2015.
 */
public class PlayerStatTabAdapter  extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Info", "Batting", "Bowling","Careers" };

    public PlayerStatTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
            return new FragmentInfo();
        else if(position==1)
            return new Fragment_Batting();
        else if(position==2)
            return new Fragment_Bowling();
        else
            return new Fragment_Career();
        //  return (position == 0)? new RankingFragment() : new SearchPlayerStat() ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

        return tabTitles[position];
        //return (position == 0)? "Info" : "Batting" :"Bowling" ;
    }
}
