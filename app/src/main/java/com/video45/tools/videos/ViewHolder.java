package com.video45.tools.videos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.video45.video45.R;

/**
 * Created by nicholas on 05/11/15.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    protected final ImageView profiePicture;
    protected final TextView lblAuthor;
    protected final VideoView videoView;
    protected final TextView date;
    protected final TextView lblTitle;

    public ViewHolder(View itemView) {
        super(itemView);
        profiePicture = (ImageView) itemView.findViewById(R.id.profilePicture);
        lblAuthor = (TextView) itemView.findViewById(R.id.lblAuthor);
        videoView  = (VideoView) itemView.findViewById(R.id.videoPlayer);
        date = (TextView) itemView.findViewById(R.id.date);
        lblTitle = (TextView) itemView.findViewById(R.id.lblTitle);
    }
}
