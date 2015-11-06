package com.video45.tools.videos;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.video45.video45.R;
import com.video45.tools.image.ImageTask;
import com.video45.tools.video.Video;

import java.util.ArrayList;

/**
 * Created by nicholas on 05/11/15.
 */
public class VideoRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Video> videos;

    public VideoRecyclerAdapter(ArrayList<Video> videos) {
        this.videos = videos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Video video = videos.get(position);

        ImageTask imageTask = new ImageTask(holder.profiePicture);

        imageTask.execute(video.getProfilePictureUrl());

        holder.lblAuthor.setText(video.getAuthor());

        holder.videoView.setVideoURI(Uri.parse(video.getVideoUrl()));
        holder.videoView.setFocusableInTouchMode(true);
        holder.videoView.start();

        holder.date.setText(video.getDate());
        holder.lblTitle.setText(video.getTitle());

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}
