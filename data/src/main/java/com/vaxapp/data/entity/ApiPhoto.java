package com.vaxapp.data.entity;

import java.util.List;

public class ApiPhoto {

    private final List<FlickrApiPhoto> photo;

    public ApiPhoto(List<FlickrApiPhoto> photo) {
        this.photo = photo;
    }

    public List<FlickrApiPhoto> getPhoto() {
        return photo;
    }
}
