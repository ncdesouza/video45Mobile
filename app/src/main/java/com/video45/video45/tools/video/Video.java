package com.video45.video45.tools.video;

/**
 * Created by nicholas on 30/10/15.
 */
public class Video {

    private String author;
    private String profilePictureUrl;
    private String videoUrl;
    private String title;


    public Video(String title, String author, String profilePictureUrl, String videoUrl) {
        this.title = title;
        this.author = author;
        this.profilePictureUrl = profilePictureUrl;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return videoUrl;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
}
