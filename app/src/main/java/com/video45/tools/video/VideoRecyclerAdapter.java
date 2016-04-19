package com.video45.tools.video;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.video45.R;
import com.video45.tools.image.ImageTask;

import java.util.ArrayList;

/**
 * Created by nicholas on 05/11/15.
 */
public class VideoRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private RecyclerView recyclerView;
    private ArrayList<Video> videos;

    public VideoRecyclerAdapter(RecyclerView  recyclerView, ArrayList<Video> videos) {
        this.videos = videos;
        this.recyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Video video = videos.get(position);

        ImageTask imageTask = new ImageTask(holder.profiePicture);

        imageTask.execute(video.getProfilePictureUrl());

        holder.lblAuthor.setText(video.getAuthor());

        VideoPlayer videoPlayer = new VideoPlayer(holder.videoSurface, video.getVideoUrl());
//        recyclerView.addOnChildAttachStateChangeListener(videoPlayer);

        holder.date.setText(video.getDate());
        holder.lblTitle.setText(video.getTitle());

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

}
