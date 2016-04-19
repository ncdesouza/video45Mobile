package com.video45.tools.video;

import android.support.v7.widget.RecyclerView;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.video45.R;

/**
 * Created by nicholas on 05/11/15.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    protected final ImageView profiePicture;
    protected final TextView lblAuthor;
    protected final SurfaceView videoSurface;
    protected final TextView date;
    protected final TextView lblTitle;

    public ViewHolder(View itemView) {
        super(itemView);
        profiePicture = (ImageView) itemView.findViewById(R.id.profilePicture);
        lblAuthor = (TextView) itemView.findViewById(R.id.lblAuthor);

        videoSurface  = (SurfaceView) itemView.findViewById(R.id.videoview);

        date = (TextView) itemView.findViewById(R.id.date);
        lblTitle = (TextView) itemView.findViewById(R.id.lblTitle);
    }
}
