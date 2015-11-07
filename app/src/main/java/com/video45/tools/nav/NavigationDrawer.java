package com.video45.tools.nav;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;

/**
 * Created by nicholas on 07/11/15.
 */
public class NavigationDrawer extends DrawerLayout {

    private NavigationDrawerListener listener;

    public NavigationDrawer(Context context) {
        super(context);
        listener = new NavigationDrawerListener(this);
    }

}
