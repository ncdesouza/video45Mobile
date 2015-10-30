package com.video45.video45.profile;

import com.video45.video45.tools.video.Video;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nicholas on 30/10/15.
 */
public class Profile {
    private String username;
    private String profilePic;
    private ArrayList<Video> videos;

    public Profile(String username, String profilePic, ArrayList<Video> videos) {
        this.username = username;
        this.profilePic = profilePic;
        this.videos = videos;
    }

    public String getUsername() {
        return username;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }
}
