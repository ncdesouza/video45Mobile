package com.video45.tools.vid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.video45.video45.R;
import com.video45.tools.img.ImageTask;

import java.util.ArrayList;

/**
 * Created by nicholas on 05/11/15.
 */
public class VideoRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<VideoData> videos;

    public VideoRecyclerAdapter(ArrayList<VideoData> videos) {
        this.videos = videos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VideoData videoData = videos.get(position);

        ImageTask imageTask = new ImageTask(holder.profiePicture);

        imageTask.execute(videoData.getProfilePictureUrl());

        holder.lblAuthor.setText(videoData.getAuthor());

        VideoPlayer videoPlayer = new VideoPlayer(holder.videoSurface, videoData.getVideoUrl());

        holder.date.setText(videoData.getDate());
        holder.lblTitle.setText(videoData.getTitle());

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

}
