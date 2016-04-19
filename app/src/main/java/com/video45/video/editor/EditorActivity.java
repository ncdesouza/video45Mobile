package com.video45.video.editor;

import android.content.ClipData;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.video45.R;

import java.io.IOException;

public class EditorActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {

    private TextureView surface;
    private MediaPlayer player;
    private VideoTextureRenderer renderer;

    private GridLayout tracks;

    private int surfaceWidth;
    private int surfaceHeight;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        surface = (TextureView) findViewById(R.id.surface);
        surface.setSurfaceTextureListener(this);

        tracks = (GridLayout)findViewById(R.id.tracks);
        int numberOfSeconds = 5*60;
        tracks.setColumnCount(numberOfSeconds);
        tracks.setRowCount(3);
        tracks.setRowOrderPreserved(true);
        
        for (int i = 0; i < tracks.getColumnCount(); i++) {
            TextView time = new TextView(this);
            String timeStr;
            timeStr = String.format("%02d:%02d", (int)(i / 60.0), (i%60));
            time.setText(timeStr);
            time.setBackgroundColor(Color.DKGRAY);
            time.setTextColor(Color.WHITE);
            if (i%5==0)
                time.setVisibility(View.VISIBLE);
            else
                time.setVisibility(View.INVISIBLE);

//            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
//            params.rowSpec = GridLayout.spec(1);
//            params.columnSpec = GridLayout.spec(i, 1, 1/(float)numberOfSeconds);
//            time.setLayoutParams(params);
            tracks.addView(time, new GridLayout.LayoutParams(
                    GridLayout.spec(1),
                    GridLayout.spec(i, 1, 1/(float)numberOfSeconds))
            );
        }
        tracks.addView(new Space(this), new GridLayout.LayoutParams(
                GridLayout.spec(2),
                GridLayout.spec(0, 1, 1/(float)numberOfSeconds)));

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (surface.isAvailable())
            startPlaying();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if (player != null)
            player.release();
        if (renderer != null)
            renderer.onPause();
    }

    private void startPlaying()
    {
        renderer = new VideoTextureRenderer(this, surface.getSurfaceTexture(), surfaceWidth, surfaceHeight);
        player = new MediaPlayer();

        try
        {
            AssetFileDescriptor afd = getAssets().openFd("Drake - Hotline Bling.mp4");
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            while(renderer.getVideoTexture() == null) {

            }

            player.setSurface(new Surface(renderer.getVideoTexture()));
//            player.setLooping(true);
            player.prepare();
            player.setVolume(85, 85);
            renderer.setVideoSize(player.getVideoWidth(), player.getVideoHeight());
            player.start();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Could not open input video!");
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height)
    {
        surfaceWidth = width;
        surfaceHeight = height;
        startPlaying();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface)
    {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
