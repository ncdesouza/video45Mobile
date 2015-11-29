package com.video45.video.editor;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class EditorActivity extends AppCompatActivity {

    private GLSurfaceView videoEditorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoEditorView = new VideoEditorView(this);
        setContentView(videoEditorView);
    }
}
