package com.video45.profilefeed;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.video45.tools.db.Video45DbHelper;
import com.video45.tools.db.models.User;
import com.video45.tools.image.ImageTask;
import com.video45.tools.video.VideoRecyclerAdapter;
import com.video45.video45.R;


public class ProfileFeedFragment extends Fragment implements ProfileListener {
    View profileView;
    Activity activity;
    RecyclerView recyclerView;

    public ProfileFeedFragment() {

    }

    public static ProfileFeedFragment newInstance() {
        return new ProfileFeedFragment();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        Video45DbHelper db = new Video45DbHelper(getContext());
        User user = db.getPrimaryUser();
        db.close();

        getProfile(user.getToken());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

        profileView = inflater.inflate(R.layout.fragment_profilefeed, container, false);
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

        recyclerView = (RecyclerView) profileView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));
        recyclerView.setAdapter(new VideoRecyclerAdapter(profile.getVideos()));

    }
}
