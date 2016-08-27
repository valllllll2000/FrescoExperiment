package com.vaxapp.data.datasource;

import com.vaxapp.data.entity.FlickrApiPhoto;
import java.util.List;
import rx.Observable;

public interface PhotoDataStore {

    Observable<List<FlickrApiPhoto>> getPhotos();
}
