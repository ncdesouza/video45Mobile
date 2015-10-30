package com.video45.video45.tools.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by nicholas on 30/10/15.
 */
public class ImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView image;
    public ImageTask(ImageView image) {
        this.image = image;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String imageURL = params[0];
        System.out.println(imageURL);
        Bitmap thumbnail = null;
        try {
            URL url = new URL(imageURL);
            thumbnail = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return thumbnail;
    }

    @Override
    protected void onPostExecute(Bitmap thumbnail) {
        image.setImageBitmap(thumbnail);
    }


}
