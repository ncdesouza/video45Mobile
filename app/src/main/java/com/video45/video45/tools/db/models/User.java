package com.video45.video45.tools.db.models;

/**
 * Created by nicholas on 05/11/15.
 */
public class User {

    private long id;
    private String name;
    private String password;
    private String token;
    private boolean primary;

    public User(String name, String password, String token) {
        this.name = name;
        this.password = password;
        this.token = token;
        this.primary = false;
    }

    public User(long id, String name, String password, String token, boolean primary) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.token = token;
        this.primary = primary;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setAsPrimary() {
        this.primary = true;
    }

    public void removePrimary() {
        this.primary = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
