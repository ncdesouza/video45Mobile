package com.video45.tools.tabs;

import android.app.ActionBar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.video45.homefeed.HomeFeedFragment;
import com.video45.profilefeed.Profile;
import com.video45.profilefeed.ProfileFeedFragment;
import com.video45.publicfeed.PublicFeedFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicholas on 07/11/15.
 */
public class FeedPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;
    private List<String> tags;
    private TabsInterface tabs;

    public FeedPagerAdapter(FragmentManager fm, TabsInterface tabs) {
        super(fm);
        tags = new ArrayList<>();
        mFragments = new ArrayList<>();
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tabs.switchView(PublicFeedFragment.newInstance(), "PUBLIC");
            case 1:
                tabs.switchView(HomeFeedFragment.newInstance(), "HOME");
            case 2:
                tabs.switchView(ProfileFeedFragment.newInstance(), "PROFILE");
        }

        return null;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    public void addFrag(Fragment fragment, String title) {
        mFragments.add(fragment);
        tags.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
