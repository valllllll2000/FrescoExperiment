package com.vaxapp.data.datasource;

import com.vaxapp.data.entity.FlickrApiPhoto;
import java.util.List;
import rx.Observable;

public interface PhotoDataSource {

    Observable<List<FlickrApiPhoto>> getPhotos(int numberOfResults);

    Observable<Boolean> savePhotos(List<FlickrApiPhoto> flickrApiPhotos);

    Observable<Boolean> invalidateCache();

    Observable<Boolean> isCacheValid();
}
