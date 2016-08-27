package com.vaxapp.frescoexperiment.presentation;

import com.vaxapp.domain.entity.FlickrPhoto;
import java.util.List;

public interface MainActivityView {

    void hideRefreshing();

    void showRefreshing();

    void showImages(List<FlickrPhoto> response);
}
