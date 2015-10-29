package com.video45.video45;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

/**
 * Created by Matthew on 10/28/2015.
 */
public class GifMovieView extends View{
    private Movie mMovie;
    private long mMoviestart;

    public GifMovieView(Context context) {
        super(context);
    }

    public GifMovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GifMovieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GifMovieView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public GifMovieView(Context context, InputStream stream){
        super(context);
        mMovie = Movie.decodeStream(stream);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);
        final long now = SystemClock.uptimeMillis();

        if (mMoviestart == 0){
            mMoviestart = now;
        }

        final int relTime = (int)((now-mMoviestart)%mMovie.duration());
        mMovie.setTime(relTime);
        mMovie.draw(canvas, 10, 10);
        this.invalidate();
    }


}
