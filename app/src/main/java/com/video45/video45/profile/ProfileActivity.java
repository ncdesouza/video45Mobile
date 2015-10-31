package com.video45.video45.profile;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.video45.video45.R;
import com.video45.video45.tools.nav.NavItemClickListener;
import com.video45.video45.tools.nav.NavToggle;
import com.video45.video45.tools.video.VideoAdapter;

public class ProfileActivity extends AppCompatActivity implements ProfileListener{
    private String[] navOptions;
    private ListView navList;
    private DrawerLayout navDrawer;
    private NavToggle navToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getProfile();

        navOptions = getResources().getStringArray(R.array.nav_options);
        navDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navList = (ListView) findViewById(R.id.left_drawer);

        navList.setAdapter(new ArrayAdapter<String>(this, R.layout.nav_list_item, navOptions));
        navList.setOnItemClickListener(new NavItemClickListener());

        navToggle = new NavToggle(this, navDrawer, R.string.nav_open, R.string.nav_close);
        navDrawer.setDrawerListener(navToggle);
    }

    private void getProfile() {
        Intent profile = getIntent();
        String token = profile.getStringExtra("token");

        String host = getString(R.string.dev_url);

        ProfileActivityTask profileActivityTask = new ProfileActivityTask(this);
        profileActivityTask.execute(host, token);
    }

    @Override
    public void showProfile(Profile profile) {
        VideoAdapter adapter = new VideoAdapter(this, profile.getVideos());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
