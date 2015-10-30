package com.video45.video45.login;

/**
 * Created by nicholas on 30/10/15.
 */
public interface LoginListener {
    public void success(String token);

    public void failure();

    public void cancel();

    public String getUrl();
}
