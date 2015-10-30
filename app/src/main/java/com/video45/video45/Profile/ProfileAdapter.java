package com.video45.video45.Profile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.json.JSONObject;

/**
 * Created by nicholas on 30/10/15.
 */
public class ProfileAdapter extends BaseAdapter {
    private Context context;
    private JSONObject profile;

    public ProfileAdapter(Context context, JSONObject profile) {
        this.context = context;
        this.profile = profile;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
