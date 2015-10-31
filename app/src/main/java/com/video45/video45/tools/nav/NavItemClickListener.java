package com.video45.video45.tools.nav;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by nicholas on 30/10/15.
 */
public class NavItemClickListener implements ListView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        selectItem(position);
    }

    private void selectItem(int position) {

    }
}
