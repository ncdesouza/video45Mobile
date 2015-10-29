package com.video45.video45;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
//        InputStream stream = null;
//        try{
//            stream.getAssets().open("main_menu.gif");
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        GifMovieView view = new GifMovieView(this, stream);
//        setContentView(view);
    }

    public void loginPress(View view){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    public void signUpPress(View view){
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }
}
