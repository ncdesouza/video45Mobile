package com.video45.publicfeed;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.video45.tools.db.Video45DbHelper;
import com.video45.tools.db.models.User;
import com.video45.tools.video.Video;
import com.video45.tools.video.VideoRecyclerAdapter;
import com.video45.video45.R;

import java.util.ArrayList;


public class PublicFeedFragment extends Fragment implements PublicFeedListener{

    private View publicFeedView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PublicFeedFragment.
     */
    public static PublicFeedFragment newInstance() {
        return new PublicFeedFragment();
    }

    public PublicFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        Video45DbHelper db = new Video45DbHelper(getContext());
        User user = db.getPrimaryUser();
        db.close();

        getVideos(user.getToken());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        publicFeedView = inflater.inflate(R.layout.fragment_publicfeed, container, false);
        return publicFeedView;
    }

    @Override
    public void onResume(){
        super.onResume();
        Video45DbHelper db = new Video45DbHelper(getContext());
        User user = db.getPrimaryUser();
        db.close();

        getVideos(user.getToken());
    }

    public void getVideos(String token) {
        String host = getString(R.string.dev_url);

        PublicFeedTask publicFeedTask = new PublicFeedTask(this);
        publicFeedTask.execute(host, token);
    }

    @Override
    public void showPublicFeed(ArrayList<Video> videos) {
        RecyclerView recyclerView = (RecyclerView) publicFeedView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new VideoRecyclerAdapter(recyclerView, videos));
    }



}
