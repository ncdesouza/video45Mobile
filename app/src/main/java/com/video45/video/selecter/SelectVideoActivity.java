package com.video45.video.selecter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.video45.R;

public class SelectVideoActivity extends AppCompatActivity {

    private static final int REQUEST_VIDEO_GET = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video);
        selectVideo();
    }

    public void selectVideo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_VIDEO_GET);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQUEST_VIDEO_GET) && (resultCode == RESULT_OK)) {
            Uri uri = data.getData();
            Log.d("UPLOAD", uri.toString());
        }
    }
}
