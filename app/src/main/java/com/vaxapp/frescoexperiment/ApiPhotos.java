package com.vaxapp.frescoexperiment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by valeria on 2/11/15.
 */
public class ApiPhotos {

    @SerializedName("photos")
    private final ApiPhoto apiPhoto;

    public ApiPhotos(ApiPhoto apiPhoto) {
        this.apiPhoto = apiPhoto;
    }

    public ApiPhoto getApiPhoto() {
        return apiPhoto;
    }
}
