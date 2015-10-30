package com.video45.video45.Profile;

import org.json.JSONObject;

/**
 * Created by nicholas on 30/10/15.
 */
public class Profile {
    public String username;
    public String profilePic;
    public JSONObject videos;

    public Profile(String username, String profilePic, JSONObject videos) {
        this.username = username;
        this.profilePic = profilePic;
        this.videos = videos;
    }
}
