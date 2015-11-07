package com.video45.tools.vid;

/**
 * Created by nicholas on 30/10/15.
 */
public class VideoData {

    private String author;
    private String profilePictureUrl;
    private String videoUrl;
    private String title;

    private String date;


    public VideoData(String title, String author, String profilePictureUrl, String videoUrl, String date) {
        this.title = title;
        this.author = author;
        this.profilePictureUrl = profilePictureUrl;
        this.videoUrl = videoUrl;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getDate() {
        return date;
    }
}
