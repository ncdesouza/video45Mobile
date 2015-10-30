package com.video45.video45;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.net.Uri;
import android.widget.VideoView;

import com.video45.video45.login.LoginActivity;

/** @author Matthew Rosettis */

public class MainMenuActivity extends Activity {
    VideoView vidView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //backgroundVideo();
    }

    @Override
    protected void onResume(){
        super.onResume();
        backgroundVideo();
    }

    public void loginPress(View view){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    public void signUpPress(View view){
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }
    public void backgroundVideo(){
        String[] vidAddress = new String[2];
        vidView = (VideoView)findViewById(R.id.menuVideo);
        //Video Loop
        vidView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                vidView.start(); //need to make transition seamless.
            }
        });
        //TODO Add more videos and implement looping
        vidAddress[0] =
                "http://video45.cloudapp.net/public/vid/dock.mp4";
        vidAddress[1]=
                "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
        Uri vidUri = Uri.parse(vidAddress[0]);
        vidView.setVideoURI(vidUri);
        vidView.start();
    }
}
