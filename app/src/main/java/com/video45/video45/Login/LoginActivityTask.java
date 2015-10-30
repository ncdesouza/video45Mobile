package com.video45.video45.Login;

/**
 * Created by nicholas on 30/10/15.
 */

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 */
public class LoginActivityTask extends AsyncTask<Void, Void, JSONObject> {

    private final String mEmail;
    private final String mPassword;
    private LoginListener listener;

    LoginActivityTask(LoginListener listener, String email, String password) {
        this.listener = listener;
        mEmail = email;
        mPassword = password;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.

        HashMap<String, String> postData = new HashMap<>(2);
        postData.put("email", mEmail);
        postData.put("password", mPassword);

        String host = listener.getUrl();
        Log.d("URL", host);
        try {

            URL url = new URL(host + "/api/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            bw.write(getPostDataString(postData));
            bw.flush();
            bw.close();
            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    JSONObject res = new JSONObject(br.readLine());
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

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}