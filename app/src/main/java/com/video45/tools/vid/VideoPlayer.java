package com.video45.tools.vid;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * Created by nicholas on 06/11/15.
 */
public class VideoPlayer implements Callback, OnPreparedListener{
    private MediaPlayer mediaPlayer;
    private SurfaceHolder vidHolder;
    String vidAddress;

    public VideoPlayer(SurfaceView vidSurface, String vidAddress) {
        this.vidHolder = vidSurface.getHolder();
        vidHolder.addCallback(this);
        this.vidAddress = vidAddress;
        try {
            this.mediaPlayer = new MediaPlayer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mediaPlayer.setDisplay(vidHolder);
            mediaPlayer.setDataSource(vidAddress);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }

    public void play() {
        mediaPlayer.start();
    }
}
