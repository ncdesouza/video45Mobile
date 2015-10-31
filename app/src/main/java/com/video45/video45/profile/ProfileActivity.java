package com.video45.video45.profile;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.video45.video45.R;
import com.video45.video45.tools.image.ImageTask;
import com.video45.video45.tools.nav.NavItemClickListener;
import com.video45.video45.tools.nav.NavToggle;
import com.video45.video45.tools.video.VideoAdapter;

public class ProfileActivity extends AppCompatActivity implements ProfileListener{


    private NavToggle navToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getProfile();

        String[] navOptions = getResources().getStringArray(R.array.nav_options);
        DrawerLayout navDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView navList = (ListView) findViewById(R.id.left_drawer);

        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        navList.setAdapter(new ArrayAdapter<String>(this, R.layout.nav_list_item, navOptions));
        navList.setOnItemClickListener(new NavItemClickListener());

        navToggle = new NavToggle(this, navDrawer, toolbar, R.string.nav_open, R.string.nav_close);
        navDrawer.setDrawerListener(navToggle);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (navToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        ImageView profilePic = (ImageView) findViewById(R.id.profilePic);
        ImageTask imageTask = new ImageTask(profilePic);
        imageTask.execute(profile.getProfilePic());

        TextView username = (TextView)findViewById(R.id.username);
        username.setText(profile.getUsername());

        TextView followers = (TextView)findViewById(R.id.followers);
        followers.setText("2 followers");

        TextView following = (TextView)findViewById(R.id.following);
        following.setText("3 following");

        VideoAdapter adapter = new VideoAdapter(this, profile.getVideos());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
