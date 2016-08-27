package com.vaxapp.data.entity;

import com.mobandme.android.transformer.compiler.Mappable;
import com.mobandme.android.transformer.compiler.Mapped;
import com.vaxapp.domain.entity.FlickrPhoto;

@Mappable(with = FlickrPhoto.class)
public class FlickrApiPhoto {

    @Mapped
    private int farm;

    @Mapped
    private String server;

    @Mapped
    private String id;

    @Mapped
    private String secret;

    @Mapped
    private String title;

    public FlickrApiPhoto() {
    }

    public FlickrApiPhoto(int farm, String server, String id, String secret, String title) {
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
}
