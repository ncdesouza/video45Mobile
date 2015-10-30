package com.video45.video45.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.video45.video45.R;
import com.video45.video45.tools.video.VideoAdapter;

public class ProfileActivity extends AppCompatActivity implements ProfileListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getProfile();
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
