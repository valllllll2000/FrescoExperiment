package com.vaxapp.frescoexperiment;

import android.support.annotation.Nullable;

public class FlickrPhoto {
    private final int farm;
    private final String server;
    private final String id;
    private final String secret;
    private final String title;

    public FlickrPhoto(int farm, String server, String id, String secret, String title) {
        this.farm = farm;
        this.server = server;
        this.id = id;
        this.secret = secret;
        this.title = title;
    }

    @Nullable
    String getUrl() {
        if(getId() == null || getSecret() == null || getServer() == null){
            return null;
        }
        return "https://farm"+getFarm()+".staticflickr.com/"+getServer()+"/"+getId()+"_"+getSecret()+".jpg";
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
}
