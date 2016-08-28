package com.vaxapp.data.repository;

import android.support.annotation.NonNull;
import android.util.Log;
import com.vaxapp.data.datasource.DataStoreFactory;
import com.vaxapp.data.datasource.PhotoDataSource;
import com.vaxapp.data.entity.FlickrApiPhoto;
import com.vaxapp.data.entity.FlickrPhotoMapper;
import com.vaxapp.domain.entity.FlickrPhoto;
import com.vaxapp.domain.repository.PhotoRepository;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func1;

public class PhotoDataRepository implements PhotoRepository {

    private final DataStoreFactory dataStoreFactory;
    private final FlickrPhotoMapper mapper;

    @Inject
    public PhotoDataRepository(DataStoreFactory factory, FlickrPhotoMapper mapper) {
        this.dataStoreFactory = factory;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<FlickrPhoto>> getPhotoList(final int numberOfResults) {
        final PhotoDataSource realmPhotoDataSource = dataStoreFactory.createRealmPhotoDataSource();
        return realmPhotoDataSource.isCacheValid().flatMap(new Func1<Boolean, Observable<List<FlickrPhoto>>>() {
            @Override
            public Observable<List<FlickrPhoto>> call(Boolean isCacheValid) {
                if (isCacheValid) {
                    return realmPhotoDataSource.getPhotos(numberOfResults)
                                               .flatMap(
                                                   new Func1<List<FlickrApiPhoto>, Observable<List<FlickrPhoto>>>() {
                                                       @Override
                                                       public Observable<List<FlickrPhoto>> call(
                                                           List<FlickrApiPhoto> apiPhotos) {
                                                           return mapPhotos(apiPhotos);
                                                       }
                                                   });
                } else {
                    return getNetWorkPhotos(realmPhotoDataSource, numberOfResults);
                }
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<List<FlickrPhoto>>>() {
            @Override
            public Observable<List<FlickrPhoto>> call(Throwable throwable) {
                Log.e("PhotoDataRepository", "error getting cached photos", throwable);
                return getNetWorkPhotos(realmPhotoDataSource, numberOfResults);
            }
        });
    }

    private Observable<List<FlickrPhoto>> getNetWorkPhotos(final PhotoDataSource realmPhotoDataSource,
                                                           int numberOfResults) {
        return dataStoreFactory.createNetworkPhotoDataSource()
                               .getPhotos(numberOfResults)
                               .flatMap(new Func1<List<FlickrApiPhoto>, Observable<List<FlickrPhoto>>>() {
                                   @Override
                                   public Observable<List<FlickrPhoto>> call(
                                       final List<FlickrApiPhoto> flickrApiPhotos) {
                                       return realmPhotoDataSource.savePhotos(flickrApiPhotos)
                                                                  .flatMap(
                                                                      new Func1<Boolean, Observable<List<FlickrPhoto>>>() {
                                                                          @Override
                                                                          public Observable<List<FlickrPhoto>> call(
                                                                              Boolean aBoolean) {
                                                                              return mapPhotos(flickrApiPhotos);
                                                                          }
                                                                      });
                                   }
                               });
    }

    @NonNull
    private Observable<List<FlickrPhoto>> mapPhotos(List<FlickrApiPhoto> flickrApiPhotos) {
        return Observable.from(flickrApiPhotos).map(new Func1<FlickrApiPhoto, FlickrPhoto>() {
            @Override
            public FlickrPhoto call(FlickrApiPhoto flickrApiPhoto) {
                return mapper.transform(flickrApiPhoto);
            }
        }).toList();
    }
}
