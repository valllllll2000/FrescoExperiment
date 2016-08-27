package com.vaxapp.data.net;

import com.vaxapp.data.entity.ApiPhotos;
import retrofit.http.GET;
import rx.Observable;

public interface PhotoAPIService {

    @GET("/services/rest/?&method=flickr.people.getPublicPhotos&api_key=4ef2fe2affcdd6e13218f5ddd0e2500d&user_id=29096781@N02&format=json&nojsoncallback=1&per_page=500")
    Observable<ApiPhotos> loadPhotos();

}
