package com.vaxapp.domain.repository;

import com.vaxapp.domain.entity.FlickrPhoto;
import java.util.List;
import rx.Observable;

public interface PhotoRepository {

    Observable<List<FlickrPhoto>> getPhotoList(int numberOfResults);
}
