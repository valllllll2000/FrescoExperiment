package com.vaxapp.data.net;

import com.vaxapp.data.entity.ApiPhotos;
import javax.inject.Inject;
import rx.Observable;

public class RestApi {

    @Inject
    public RestApi() {
    }

    public Observable<ApiPhotos> getPhotos() {
        return RestServiceFactory.createRetrofitService(PhotoAPIService.class).loadPhotos();
    }
}
