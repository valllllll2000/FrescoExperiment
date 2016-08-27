package com.vaxapp.frescoexperiment;

public interface MainActivityView {

    void hideRefreshing();

    void showRefreshing();

    void showImages(ApiPhotos response);
}
