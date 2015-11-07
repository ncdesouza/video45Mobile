package com.video45.profilefeed;

import com.video45.tools.vid.VideoData;

import java.util.ArrayList;

/**
 * Created by nicholas on 30/10/15.
 */
public class Profile {
    private String username;
    private String profilePic;
    private ArrayList<VideoData> videos;

    public Profile(String username, String profilePic, ArrayList<VideoData> videos) {
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

    public ArrayList<VideoData> getVideos() {
        return videos;
    }
}
