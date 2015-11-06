package com.video45;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;

import com.video45.video45.R;
import com.video45.profile.ProfileFragment;
import com.video45.tools.nav.NavItemClickListener;
import com.video45.tools.nav.NavMenuAdapter;
import com.video45.tools.nav.NavMenuItem;
import com.video45.tools.nav.NavToggle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NavToggle navToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar Setup
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        toolbar.setLogo(R.drawable.ic_logo);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayShowTitleEnabled(false);
        }

        // Navigation Drawer Setup
        DrawerLayout navDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView navList = (ListView) findViewById(R.id.left_drawer);

        Resources res = getResources();
        String[] navItems = res.getStringArray(R.array.nav_options);
        int[] navIcons = new int[] {
                R.drawable.ic_action_action_language,
                R.drawable.ic_action_toggle_star,
                R.drawable.ic_action_social_person_outline,
                R.drawable.ic_action_action_settings,
                R.drawable.ic_action_av_fast_rewind
        };

        ArrayList<NavMenuItem> navOptions = new ArrayList<NavMenuItem>();
        for(int i = 0; i < navItems.length && i < navIcons.length; i++) {
            navOptions.add(new NavMenuItem(navIcons[i], navItems[i]));
        }

        navList.setAdapter(new NavMenuAdapter(this, R.layout.nav_list_item, navOptions));
        navList.setOnItemClickListener(new NavItemClickListener(this, navDrawer, navList));

        navToggle = new NavToggle(this, navDrawer, toolbar, R.string.nav_open, R.string.nav_close);
        navDrawer.setDrawerListener(navToggle);


        // Initial Fragment Setup
        if (findViewById(R.id.content) != null) {

            if (savedInstanceState != null) {
                return;
            }

            ProfileFragment profileFragment = new ProfileFragment();

            profileFragment.setArguments(getIntent().getExtras());

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.content, profileFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        navToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        navToggle.onConfigurationChanged(newConfig);
    }

}
