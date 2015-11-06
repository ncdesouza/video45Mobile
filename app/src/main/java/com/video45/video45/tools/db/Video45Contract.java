package com.video45.video45.tools.db;

import android.provider.BaseColumns;

/**
 * Created by nicholas on 05/11/15.
 */
public final class Video45Contract {

    public Video45Contract() {
    }

    public static abstract class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_TOKEN = "token";
        public static final String COLUMN_NAME_PRIMARY = "prime";
    }
}
