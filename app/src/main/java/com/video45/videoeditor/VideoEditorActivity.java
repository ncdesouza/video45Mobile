package com.video45.videoeditor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.video45.R;

public class VideoEditorActivity extends Activity {

    private static final int REQUEST_VIDEO_GET = 1;
    private static final int REQUEST_VIDEO_CAPTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_editor);
    }

    public void selectVideo(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_VIDEO_GET);
        }
    }

    public void captureVideo(View view) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    public void editVideo() {

    }

    public void uploadVideo() {


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((resultCode == RESULT_OK)) {
            if ((requestCode == REQUEST_VIDEO_GET) || (requestCode == REQUEST_VIDEO_CAPTURE)) {
                Uri uri = data.getData();
                Log.d("UPLOAD", uri.toString());
                Intent videoEditor = new Intent(Intent.ACTION_EDIT);
                videoEditor.setDataAndType(uri, "video/*");
                startActivity(Intent.createChooser(videoEditor, null));
            }
        }
    }
}
