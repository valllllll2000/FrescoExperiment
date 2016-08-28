package com.vaxapp.data.datasource.realm;

import android.util.Log;
import com.vaxapp.data.datasource.PhotoDataSource;
import com.vaxapp.data.datasource.realm.entity.RealmFlickrMapper;
import com.vaxapp.data.datasource.realm.entity.RealmFlickrPhoto;
import com.vaxapp.data.entity.FlickrApiPhoto;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Func1;

@Singleton
public class RealmPhotoDataSource implements PhotoDataSource {

    private static final long INVALID_DATE = -1L;

    private final RealmConfiguration realmConfiguration;
    private final RealmFlickrMapper mapper;

    private long cacheDate = INVALID_DATE;

    @Inject
    public RealmPhotoDataSource(RealmConfiguration realmConfiguration, RealmFlickrMapper mapper) {
        this.realmConfiguration = realmConfiguration;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<FlickrApiPhoto>> getPhotos(int numberOfResults) {
        Log.d("RealmPhotoDataSource", "getting photos from cache");
        final Realm realm = Realm.getInstance(realmConfiguration);
        List<RealmFlickrPhoto> realmFlickrPhotos = realm.where(RealmFlickrPhoto.class).findAll();
        return Observable.from(realmFlickrPhotos).map(new Func1<RealmFlickrPhoto, FlickrApiPhoto>() {
            @Override
            public FlickrApiPhoto call(RealmFlickrPhoto realmFlickrPhoto) {
                return mapper.transform(realmFlickrPhoto);
            }
        }).toList();
    }

    @Override
    public Observable<Boolean> savePhotos(List<FlickrApiPhoto> apiPhotos) {
        return Observable.from(apiPhotos).map(new Func1<FlickrApiPhoto, RealmFlickrPhoto>() {
            @Override
            public RealmFlickrPhoto call(FlickrApiPhoto flickrApiPhoto) {
                return mapper.transform(flickrApiPhoto);
            }
        }).toList().flatMap(new Func1<List<RealmFlickrPhoto>, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call(List<RealmFlickrPhoto> realmFlickrPhotos) {
                final Realm realm = Realm.getInstance(realmConfiguration);
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(realmFlickrPhotos);
                realm.commitTransaction();
                cacheDate = System.currentTimeMillis();
                return Observable.just(true);
            }
        });
    }

    @Override
    public Observable<Boolean> invalidateCache() {
        cacheDate = INVALID_DATE;
        return Observable.just(true);
    }

    @Override
    public Observable<Boolean> isCacheValid() {
        return Observable.just(cacheDate != INVALID_DATE);
    }
}
