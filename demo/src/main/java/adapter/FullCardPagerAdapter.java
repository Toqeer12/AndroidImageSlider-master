package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import PageFragment.FullCard_Team1;
import PageFragment.FullCard_Team2;

/**
 * Created by TOQEER on 8/28/2015.
 */
public class FullCardPagerAdapter extends FragmentPagerAdapter  {
    public static int PAGE_COUNT = 2;
    public static String tabTitles[] = new String[]{"",""};
    public FullCardPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
            return new FullCard_Team1();
        else
            return new FullCard_Team2();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
//        tabTitles[0]=FullCard_Team1.name;
//        tabTitles[1]=FullCard_Team1.name;
        return tabTitles[position];
    }
}
