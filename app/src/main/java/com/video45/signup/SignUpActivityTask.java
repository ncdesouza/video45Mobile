package com.video45.signup;

/**
 * Created by Matthew on 29/11/15.
 */

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivityTask extends AsyncTask<Void, Void, JSONObject> {
    private final String mFirstName;
    private final String mLastName;
    private final String mUsername;
    private final String mEmail;
    private final String mPassword;
    private SignUpListener listener;

    SignUpActivityTask(SignUpListener listener,String firstName, String lastName, String username, String email, String password) {
        this.listener = listener;
        mFirstName = firstName;
        mLastName = lastName;
        mUsername = username;
        mEmail = email;
        mPassword = password;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.
        String str =  "{\"firstName\": \""+mFirstName+"\",\"lastName\":\""+mLastName
                +"\",\"username\": \""+mUsername+"\",\"email\": \""+mEmail+"\",\"password\": \""+mPassword+"\"}";
        Log.d("passed String",str);
        String host = listener.getUrl();
        Log.d("URL", host);
        try {

            URL url = new URL(host + "/api/signup");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);


            byte[] outputInBytes = str.getBytes("UTF-8");
            OutputStream os = conn.getOutputStream();
            os.write(outputInBytes);
            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    JSONObject res = new JSONObject(br.readLine());
                    return res;
                } catch (IOException | JSONException e1) {
                    e1.printStackTrace();
                }
            }else {
                return new JSONObject("{success: false}");
            }

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
            try {
                return new JSONObject("{success: false}");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // TODO: register the new account here.
        return null;
    }

    @Override
    protected void onPostExecute(final JSONObject success) {
        System.out.println(success.toString());
        try {
            if (success != null && success.getBoolean("success")) {
                listener.success(success.getString("token"));
            } else {
                listener.failure();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCancelled() {
        listener.cancel();
    }
}