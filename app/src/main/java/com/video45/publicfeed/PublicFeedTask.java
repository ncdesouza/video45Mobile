package com.video45.publicfeed;

import android.os.AsyncTask;

import com.video45.tools.vid.VideoData;

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
public class PublicFeedTask extends AsyncTask<String, Void, ArrayList<VideoData>> {
    private PublicFeedListener listener;

    public PublicFeedTask(PublicFeedListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<VideoData> doInBackground(String... params) {

        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(params[0] + "/api/public");

            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);

            conn.setRequestProperty("Authorization", params[1]);
            conn.setRequestMethod("GET");


            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                JSONObject response = new JSONObject(br.readLine());

                if ((boolean) response.get("success")) {
                    JSONArray data = response.getJSONArray("data");

                    ArrayList<VideoData> videos = new ArrayList<VideoData>();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject video = (JSONObject) data.get(i);

                        String title = video.getString("title");

                        JSONObject author = video.getJSONObject("author");
                        String username = author.getString("username");
                        String profilePicture = params[0] + author.getString("profilePic");

                        String videoUrl = params[0] + video.getString("videoURL");

                        String videoDate = video.getString("date");

                        videos.add(new VideoData(title, username, profilePicture, videoUrl, videoDate));
                    }

                    return videos;
                }

            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<VideoData> videos) {
        listener.showPublicFeed(videos);
    }
}
