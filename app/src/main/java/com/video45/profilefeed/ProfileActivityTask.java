package com.video45.profilefeed;

import android.os.AsyncTask;

import com.video45.tools.video.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by nicholas on 30/10/15.
 */
public class ProfileActivityTask extends AsyncTask<String, Void, Profile> {
    private ProfileListener listener;

    public ProfileActivityTask(ProfileListener listener) {
        this.listener = listener;
    }

    @Override
    protected Profile doInBackground(String... params) {

        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(params[0] + "/api/profile");

            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);

            conn.setRequestProperty("Authorization", params[1]);
            conn.setRequestMethod("GET");


            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                JSONObject response = new JSONObject(br.readLine());

                if ((boolean) response.get("success")) {
                    JSONObject data = response.getJSONObject("data");

                    String profileUsername = data.getString("username");
                    String profileProfilePicture = params[0] + data.getString("profilePic");

                    ArrayList<Video> videos = new ArrayList<Video>();
                    JSONArray jsonVideos = data.getJSONArray("videos");
                    for (int i = 0; i < jsonVideos.length(); i++) {
                        JSONObject video = (JSONObject) jsonVideos.get(i);

                        String title = video.getString("title");

                        JSONObject author = video.getJSONObject("author");
                        String username = author.getString("username");
                        String profilePicture = params[0] + author.getString("profilePic");

                        String videoUrl = params[0] + video.getString("videoURL");

                        String videoDate = video.getString("date");

                        videos.add(new Video(title, username, profilePicture, videoUrl, videoDate));
                    }

                    return new Profile(profileUsername, profileProfilePicture, videos);
                }

            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Profile profile) {
        listener.showProfile(profile);
    }
}
