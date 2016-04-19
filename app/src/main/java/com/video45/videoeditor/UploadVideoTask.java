package com.video45.videoeditor;

import android.os.AsyncTask;

/**
 * com.video45.videoeditor
 * Created by nicholas on 10/02/16.
 */
public class UploadVideoTask extends AsyncTask<String, Void, Boolean> {

    UploadVideoListener mlistener;

    public UploadVideoTask(UploadVideoListener listener) {
        mlistener = listener;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        mlistener.onCompleted(success);
    }
}
