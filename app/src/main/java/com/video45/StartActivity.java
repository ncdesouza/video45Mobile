package com.video45;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.net.Uri;
import android.widget.VideoView;

import com.video45.video45.R;
import com.video45.login.LoginActivity;
import com.video45.signup.SignUpActivity;

/**
 * StartActivity:
 *      This class is responsible handling the initial launch
 *      of the application. It options a user to either login
 *      or signup. It then starts that activity awaiting a
 *      response to see if the user is authorized. On a
 *      successful login or signup the activity starts the
 *      main activity, sending the users token with it, before
 *      finishing.
 * @author Nicholas De Souza
 * @author Matthew Rosettis
 */
public class StartActivity extends Activity {
    public final static int LOGIN_REQUEST = 1;
    public final static int SIGNUP_REQUEST = 2;
    public final static String TOKEN = "com.video45.TOKEN";

    VideoView vidView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //backgroundVideo();
    }

    @Override
    protected void onResume(){
        super.onResume();
        backgroundVideo();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == LOGIN_REQUEST || requestCode == SIGNUP_REQUEST) {
                Intent mainActivity = new Intent(this, MainActivity.class);
                mainActivity.putExtra(TOKEN, data.getStringExtra(TOKEN));
                startActivity(mainActivity);
                finish();
            }
        }
    }

    public void loginPress(View view){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivityForResult(loginIntent, LOGIN_REQUEST);
    }

    public void signUpPress(View view){
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivityForResult(signUpIntent, SIGNUP_REQUEST);
    }

    public void backgroundVideo(){
        String[] vidAddress = new String[2];
        vidView = (VideoView)findViewById(R.id.menuVideo);
        //TODO Add more videos and implement looping
        vidAddress[0] =
                "http://video45.cloudapp.net/public/vid/dock.mp4";
        vidAddress[1]=
                "http://video45.cloudapp.net/public/vid/river.mp4";
        Uri vidUri = Uri.parse(vidAddress[0]);
        vidView.setVideoURI(vidUri);
        vidView.start();

        if(vidView.getCurrentPosition()- vidView.getDuration() == 100){
            vidView.seekTo(0);
        }
        //Video Loop
        vidView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                //vidView.seekTo(0);
                vidView.start(); //need to make transition seamless.
            }
        });
    }
}
