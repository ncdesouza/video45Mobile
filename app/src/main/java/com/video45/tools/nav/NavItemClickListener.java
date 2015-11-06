package com.video45.tools.nav;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.video45.video45.R;
import com.video45.settings.SettingsFragment;

/**
 * Created by nicholas on 30/10/15.
 */
public class NavItemClickListener implements ListView.OnItemClickListener {
    private AppCompatActivity activity;
    private DrawerLayout navDrawer;
    private ListView navList;

    public NavItemClickListener(AppCompatActivity activity, DrawerLayout navDrawer, ListView navList) {
        this.activity = activity;
        this.navDrawer = navDrawer;
        this.navList = navList;
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        selectItem(position);
        navDrawer.closeDrawer(navList);
    }

    private void selectItem(int position) {

        int id = position;
        System.out.println(id);
        switch (id) {
            /*case 2: {
                switchView(new ProfileFragment());
            }*/
            case 3: {
                switchView(new SettingsFragment());
            }
        }

    }

    private void switchView(Fragment fragment) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
