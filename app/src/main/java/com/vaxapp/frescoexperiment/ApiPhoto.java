package com.vaxapp.frescoexperiment;

import java.util.List;

/**
 * Created by valeria on 2/11/15.
 */

public class ApiPhoto {

    private final List<FlickrPhoto> photo;

    public ApiPhoto(List<FlickrPhoto> photo) {
        this.photo = photo;
    }

    public List<FlickrPhoto> getPhoto() {
        return photo;
    }


}
