package com.video45.video45.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.video45.video45.R;
import com.video45.video45.StartActivity;
import com.video45.video45.tools.image.ImageTask;
import com.video45.video45.tools.video.VideoAdapter;

public class ProfileFragment extends Fragment implements ProfileListener {
    View profileView;
    Activity activity;
    private VideoAdapter videoAdapter;
    private ProfileListener profileListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        String token = getArguments().getString(StartActivity.TOKEN);
        getProfile(token);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        profileView = inflater.inflate(R.layout.fragment_profile, container, false);
        return profileView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void getProfile(String token) {
        String host = getString(R.string.dev_url);

        ProfileActivityTask profileActivityTask = new ProfileActivityTask(this);
        profileActivityTask.execute(host, token);
    }

    @Override
    public void showProfile(Profile profile) {
        ImageView profilePic = (ImageView) profileView.findViewById(R.id.profilePic);
        ImageTask imageTask = new ImageTask(profilePic);
        imageTask.execute(profile.getProfilePic());

        TextView username = (TextView) profileView.findViewById(R.id.username);
        username.setText(profile.getUsername());

        TextView followers = (TextView) profileView.findViewById(R.id.followers);
        followers.setText("2 followers");

        TextView following = (TextView) profileView.findViewById(R.id.following);
        following.setText("3 following");

        videoAdapter = new VideoAdapter(activity, profile.getVideos());
        ListView listView = (ListView)  profileView.findViewById(R.id.listView);
        listView.setAdapter(videoAdapter);
    }
}
