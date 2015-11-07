package com.video45.tools.nav;

import android.content.Context;
import android.support.design.widget.NavigationView;

/**
 * Created by nicholas on 07/11/15.
 */
public class NavView extends NavigationView {

    public NavView(Context context, NavigationDrawerListener listener) {
        super(context);
        setNavigationItemSelectedListener(listener);
    }

}
