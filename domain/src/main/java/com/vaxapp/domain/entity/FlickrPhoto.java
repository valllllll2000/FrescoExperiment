package com.vaxapp.domain.entity;

import android.support.annotation.Nullable;

public class FlickrPhoto {

    private int farm;
    private String server;
    private String id;
    private String secret;
    private String title;

    public FlickrPhoto() {
    }

    public FlickrPhoto(int farm, String server, String id, String secret, String title) {
        this.farm = farm;
        this.server = server;
        this.id = id;
        this.secret = secret;
        this.title = title;
    }

    public int getFarm() {
        return farm;
    }

    public String getServer() {
        return server;
    }

    public String getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public String getTitle() {
        return title;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    public String getUrl() {
        if (getId() == null || getSecret() == null || getServer() == null) {
            return null;
        }
        return "https://farm"
            + getFarm()
            + ".staticflickr.com/"
            + getServer()
            + "/"
            + getId()
            + "_"
            + getSecret()
            + ".jpg";
    }
}
