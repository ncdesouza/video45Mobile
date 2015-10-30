package com.video45.video45.Profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.video45.video45.R;

import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity implements ProfileListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    private void getProfile(String token) {

    }


    @Override
    public void showProfile(JSONObject profile) {

    }
}
