package com.vaxapp.frescoexperiment;

import com.vaxapp.frescoexperiment.presentation.FlickrPhoto;
import java.util.List;

public class ApiPhoto {

    private final List<FlickrPhoto> photo;

    public ApiPhoto(List<FlickrPhoto> photo) {
        this.photo = photo;
    }

    public List<FlickrPhoto> getPhoto() {
        return photo;
    }


}
