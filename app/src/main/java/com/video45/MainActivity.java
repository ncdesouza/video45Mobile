package com.video45;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.video45.video.editor.EditorActivity;
import com.video45.homefeed.HomeFeedFragment;
import com.video45.profilefeed.ProfileFeedFragment;
import com.video45.publicfeed.PublicFeedFragment;
import com.video45.settings.SettingsFragment;
import com.video45.tools.tabs.FeedPagerAdapter;
import com.video45.video.selecter.SelectVideoActivity;
import com.video45.video45.R;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private FragmentManager fragmentManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar Setup
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        toolbar.setLogo(R.drawable.ic_logo);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        // Initial Fragment Setup
        if (findViewById(R.id.content) != null) {

            if (savedInstanceState != null) {
                return;
            }
            ProfileFeedFragment profileFeedFragment = new ProfileFeedFragment();

            profileFeedFragment.setArguments(getIntent().getExtras());


            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.content, profileFeedFragment, getResources().getString(R.string.nav_item_profile))
                    .commit();
        }

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_action_action_language);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_action_favorite_outline);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_action_social_person_outline);
    }

    private void setupViewPager(ViewPager viewPager) {
        FeedPagerAdapter adapter = new FeedPagerAdapter(fragmentManager);
        adapter.addFrag(PublicFeedFragment.newInstance(), "Public");
        adapter.addFrag(HomeFeedFragment.newInstance(), "Home");
        adapter.addFrag(ProfileFeedFragment.newInstance(), "Profile");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_new_video:
                Intent newVideo = new Intent(this, EditorActivity.class);
                startActivity(newVideo);

            case R.id.action_select_video:
                Intent getVideo = new Intent(this, SelectVideoActivity.class);
                startActivity(getVideo);


            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        mDrawerLayout.closeDrawers();

        int id = item.getItemId();
        System.out.println(id);
        System.out.println(R.id.nav_item_profile);
        switch (id) {
            case R.id.nav_item_public:
                switchView(PublicFeedFragment.newInstance(), getString(R.string.nav_item_public));
                return true;

            case R.id.nav_item_profile:
                switchView(new ProfileFeedFragment(), getResources().getString(R.string.nav_item_profile));
                return true;

            case R.id.nav_item_settings:
                switchView(new SettingsFragment(), getResources().getString(R.string.nav_item_settings));
                return true;
        }
        return false;
    }

    private void switchView(Fragment fragment, String fragTag) {
        System.out.println(fragTag);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction
                .replace(R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }


}
