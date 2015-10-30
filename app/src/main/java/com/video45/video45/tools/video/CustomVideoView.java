package com.video45.video45.tools.video;

import android.content.Context;
import android.widget.VideoView;

/**
 * Created by nicholas on 30/10/15.
 */
public class CustomVideoView extends VideoView {
    private int forcedWidth = 0;
    private int forcedHeight = 0;

    public CustomVideoView(Context context) {
        super(context);
    }

    public void setDimentions(int width, int height) {
        forcedWidth = width;
        forcedHeight = height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(forcedWidth, forcedHeight);
    }
}
