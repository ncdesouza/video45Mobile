package com.video45.tools.nav;

import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.video45.MainActivity;

/**
 * Created by nicholas on 07/11/15.
 */
public class NavigationDrawerListener implements OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;

    public NavigationDrawerListener(DrawerLayout mDrawerLayout) {
        this.mDrawerLayout = mDrawerLayout;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
//        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
        return true;
    }
}
