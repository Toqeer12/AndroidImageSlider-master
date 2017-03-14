package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import PageFragment.FullCardTest_Team1;
import PageFragment.FullCardTest_Team2;
import PageFragment.FullCardTest_Team3;
import PageFragment.FullCardTest_Team4;

/**
 * Created by TOQEER on 9/7/2015.
 */
public class FullCardPagerTestAdapter extends FragmentPagerAdapter {
    public static int PAGE_COUNT ;

    public static String tabTitles[] = new String[]{"", "", "",""};


    public FullCardPagerTestAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new FullCardTest_Team1();
        else if(position==1)
            return new FullCardTest_Team2();
        else if(position==2)
            return  new FullCardTest_Team3();
        else
            return new FullCardTest_Team4();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
//        tabTitles[0]=FullCard_Team1.name;
//        tabTitles[1]=FullCard_Team1.name;
        return tabTitles[position];
    }

}
