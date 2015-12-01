package com.video45.tools.tabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.video45.homefeed.HomeFeedFragment;
import com.video45.profilefeed.ProfileFeedFragment;
import com.video45.publicfeed.PublicFeedFragment;
import com.video45.settings.SettingsFragment;

/**
 * Created by Matthew on 11/29/2015.
 */
public class FragmentPageAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] {"Public Feed","Home Feed","Profile Feed"};

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {
            case 0: return PublicFeedFragment.newInstance();
            case 1: return HomeFeedFragment.newInstance();
            case 2: return ProfileFeedFragment.newInstance();
            case 3: return SettingsFragment.newInstance("a", "b");
            default: return ProfileFeedFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}
