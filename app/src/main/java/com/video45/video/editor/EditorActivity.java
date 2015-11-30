package com.video45.video.editor;

import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.TextureView;
import android.widget.GridLayout;
import android.widget.TextView;

import com.video45.video45.R;

import java.io.IOException;

public class EditorActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {

    private TextureView surface;
    private MediaPlayer player;
    private VideoTextureRenderer renderer;

    private GridLayout videoTrack;
    private GridLayout audioTrack;

    private int surfaceWidth;
    private int surfaceHeight;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        surface = (TextureView) findViewById(R.id.surface);
        surface.setSurfaceTextureListener(this);

        videoTrack = (GridLayout)findViewById(R.id.track_video);
        for (int i = 0; i < videoTrack.getColumnCount(); i++) {
            TextView time = new TextView(this);
            String timeStr;
            if (i%5==0)
                timeStr = String.valueOf((int)(i / 60.0)) + ":" + String.valueOf((int)((i % 60)*100));
            else
                timeStr = " ";

            time.setText(timeStr);
            videoTrack.addView(time, i);
        }

        audioTrack = (GridLayout)findViewById(R.id.track_audio);
        for (int i = 0; i < audioTrack.getColumnCount(); i++) {
            TextView time = new TextView(this);
            String timeStr;
            if (i%5==0)
                timeStr = String.valueOf((int)(i / 60.0)) + ":" + String.valueOf((int)((i % 60)*100));
            else
                timeStr = " ";

            time.setText(timeStr);
            audioTrack.addView(time, i);
        }

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
            player.setLooping(true);
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
