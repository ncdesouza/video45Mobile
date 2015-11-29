package com.video45.signup;

/**
 * Created by Matthew on 11/29/2015.
 */
public interface SignUpListener {
    public void success(String token);

    public void failure();

    public void cancel();

    public String getUrl();
}
