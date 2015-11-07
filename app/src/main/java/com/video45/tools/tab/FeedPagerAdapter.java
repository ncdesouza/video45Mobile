package com.video45.tools.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicholas on 07/11/15.
 */
public class FeedPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;
    private List<String> tags;

    public FeedPagerAdapter(FragmentManager fm) {
        super(fm);
        tags = new ArrayList<>();
        mFragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
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
