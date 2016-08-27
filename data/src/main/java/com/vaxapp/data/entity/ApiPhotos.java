package com.vaxapp.data.entity;

import com.google.gson.annotations.SerializedName;

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
