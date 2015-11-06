package com.video45.tools.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.video45.tools.db.Video45Contract.*;
import com.video45.tools.db.models.User;

/**
 * Video45DbHelper
 *      This class is responsible for creating and upgrading the local database.
 * @author Nicholas De Souza
 */
public class Video45DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Video45.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String BOOL_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_USER =
            "CREATE TABLE " + UserTable.TABLE_NAME + " (" +
                    UserTable._ID + " INTEGER PRIMARY KEY," +
                    UserTable.COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                    UserTable.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    UserTable.COLUMN_NAME_TOKEN + TEXT_TYPE + COMMA_SEP +
                    UserTable.COLUMN_NAME_PRIMARY + BOOL_TYPE  +
            " )";

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + UserTable.TABLE_NAME;


    public Video45DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USER);
        onCreate(db);
    }

    public User updateOrCreateUser(User user) {
        User existingUser = getUser(user.getName());
        if (existingUser != null) {
            if (!existingUser.isPrimary()) {

                User primary = getPrimaryUser();
                if (primary != null) {
                    primary.removePrimary();
                    updatePrimaryUser(primary);
                }

                existingUser.setAsPrimary();
                updatePrimaryUser(existingUser);
            }
            return existingUser;
        }

        long user_id = createUser(user);
        user.setId(user_id);

        User primary = getPrimaryUser();
        if (primary != null) {
            primary.removePrimary();
            updatePrimaryUser(primary);
        }

        user.setAsPrimary();
        updatePrimaryUser(user);

        return user;
    }

    public long createUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserTable.COLUMN_NAME_USERNAME, user.getName());
        values.put(UserTable.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(UserTable.COLUMN_NAME_TOKEN, user.getToken());
        values.put(UserTable.COLUMN_NAME_PRIMARY, user.isPrimary() ? 1 : 0);

        return db.insert(UserTable.TABLE_NAME, null, values);
    }

    public User getUser(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery =
                "SELECT * FROM " + UserTable.TABLE_NAME +
                        " WHERE " + UserTable.COLUMN_NAME_USERNAME + " = \"" + name + "\"";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            return extractUser(c);
        }

        return null;
    }

    public int updatePrimaryUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserTable.COLUMN_NAME_PRIMARY, user.isPrimary());

        String selection = UserTable._ID + " = ?";
        String[] selectionArgs = { String.valueOf(user.getId())};

        return db.update(UserTable.TABLE_NAME, values, selection, selectionArgs);
    }

    public User getPrimaryUser() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery =
                "SELECT * FROM " + UserTable.TABLE_NAME +
                " WHERE " + UserTable.COLUMN_NAME_PRIMARY + " = " + "1";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            return extractUser(c);
        }
        return null;
    }

    public User extractUser(Cursor c) {
        return new User(
                c.getInt(c.getColumnIndex(UserTable._ID)),
                c.getString(c.getColumnIndex(UserTable.COLUMN_NAME_USERNAME)),
                c.getString(c.getColumnIndex(UserTable.COLUMN_NAME_PASSWORD)),
                c.getString(c.getColumnIndex(UserTable.COLUMN_NAME_TOKEN)),
                c.getInt(c.getColumnIndex(UserTable.COLUMN_NAME_PRIMARY)) != 0
        );
    }


}
