package com.vaxapp.data.datasource;

import com.vaxapp.data.entity.ApiPhotos;
import com.vaxapp.data.entity.FlickrApiPhoto;
import com.vaxapp.data.net.RestApi;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func1;

public class NetworkPhotoDataStore implements PhotoDataStore {

    private final RestApi restApi;

    @Inject
    public NetworkPhotoDataStore(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<List<FlickrApiPhoto>> getPhotos() {
        return restApi.getPhotos().flatMap(new Func1<ApiPhotos, Observable<List<FlickrApiPhoto>>>() {
            @Override
            public Observable<List<FlickrApiPhoto>> call(ApiPhotos apiPhotos) {
                return Observable.just(apiPhotos.getApiPhoto().getPhoto());
            }
        });
    }
}
