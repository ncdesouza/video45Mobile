package com.video45.video.editor;

import android.content.Context;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

/**
 * Created by nicholas on 30/11/15.
 */
public class TrackView extends TextView {

    private int second;

    public TrackView(Context context, int second) {
        super(context);
        this.second = second;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }


}
