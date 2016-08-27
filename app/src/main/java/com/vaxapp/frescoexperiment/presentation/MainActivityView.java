package com.vaxapp.frescoexperiment.presentation;

import com.vaxapp.frescoexperiment.ApiPhotos;

public interface MainActivityView {

    void hideRefreshing();

    void showRefreshing();

    void showImages(ApiPhotos response);
}
