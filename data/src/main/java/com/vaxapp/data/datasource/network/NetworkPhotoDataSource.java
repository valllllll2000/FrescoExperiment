package com.vaxapp.data.datasource.network;

import android.util.Log;
import com.vaxapp.data.datasource.PhotoDataSource;
import com.vaxapp.data.entity.ApiPhotos;
import com.vaxapp.data.entity.FlickrApiPhoto;
import com.vaxapp.data.net.RestApi;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func1;

public class NetworkPhotoDataSource implements PhotoDataSource {

    private final RestApi restApi;

    @Inject
    public NetworkPhotoDataSource(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<List<FlickrApiPhoto>> getPhotos(int numberOfResults) {
        Log.d("NetworkPhotoDataSource", "getting photos from network");
        return restApi.getPhotos(numberOfResults).flatMap(new Func1<ApiPhotos, Observable<List<FlickrApiPhoto>>>() {
            @Override
            public Observable<List<FlickrApiPhoto>> call(ApiPhotos apiPhotos) {
                return Observable.just(apiPhotos.getApiPhoto().getPhoto());
            }
        });
    }

    @Override
    public Observable<Boolean> savePhotos(List<FlickrApiPhoto> flickrApiPhotos) {
        return Observable.error(new UnsupportedOperationException("Operation not allowed"));
    }

    @Override
    public Observable<Boolean> invalidateCache() {
        return Observable.error(new UnsupportedOperationException("Operation not allowed"));
    }

    @Override
    public Observable<Boolean> isCacheValid() {
        return Observable.error(new UnsupportedOperationException("Operation not allowed"));
    }
}
