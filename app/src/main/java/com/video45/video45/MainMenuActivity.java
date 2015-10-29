package com.video45.video45;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainMenuActivity extends Activity {

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
    VideoView vidView;
    public void backgroundVideo(){
        String vidAddress;
        vidView = (VideoView)findViewById(R.id.menuVideo);
        //Video Loop
        vidView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                vidView.start(); //need to make transition seamless.
            }
        });
        //TODO Make this into an array and add more videos to be looped
        vidAddress =
//            "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
            "http://video45.cloudapp.net/public/vid/dock.mp4";

        Uri vidUri = Uri.parse(vidAddress);
        vidView.setVideoURI(vidUri);
        vidView.start();
    }
}
