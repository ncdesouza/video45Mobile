package com.video45.video45.tools.video;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.video45.video45.R;
import com.video45.video45.tools.image.ImageTask;

import java.util.ArrayList;

/**
 * Created by nicholas on 30/10/15.
 */
public class VideoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Video> videos;

    public VideoAdapter(Context context, ArrayList<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Video video = videos.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_video_item, parent, false);
        }

        ImageView profiePicture = (ImageView) convertView.findViewById(R.id.profilePicture);
        ImageTask imageTask = new ImageTask(profiePicture);
        imageTask.execute(video.getProfilePictureUrl());

        TextView lblAuthor = (TextView) convertView.findViewById(R.id.lblAuthor);
        lblAuthor.setText(video.getAuthor());

        VideoView videoView  = (VideoView) convertView.findViewById(R.id.videoPlayer);
        videoView.setVideoURI(Uri.parse(video.getUrl()));
        videoView.setFocusableInTouchMode(true);
        videoView.start();

        TextView lblTitle = (TextView) convertView.findViewById(R.id.lblTitle);
        lblTitle.setText(video.getTitle());

        return convertView;
    }
}
