package com.vaxapp.data.datasource.realm.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmFlickrPhoto extends RealmObject {

    @PrimaryKey
    private String id;

    private int farm;
    private String server;
    private String secret;
    private String title;

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
}
