package com.vaxapp.data.repository;

import com.vaxapp.data.datasource.DataStoreFactory;
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
    public Observable<List<FlickrPhoto>> getPhotoList() {
        return dataStoreFactory.createPhotoDataStore()
                               .getPhotos()
                               .flatMap(new Func1<List<FlickrApiPhoto>, Observable<List<FlickrPhoto>>>() {
                                   @Override
                                   public Observable<List<FlickrPhoto>> call(List<FlickrApiPhoto> flickrApiPhotos) {
                                       return Observable.from(flickrApiPhotos)
                                                        .map(new Func1<FlickrApiPhoto, FlickrPhoto>() {
                                                            @Override
                                                            public FlickrPhoto call(FlickrApiPhoto flickrApiPhoto) {
                                                                return mapper.transform(flickrApiPhoto);
                                                            }
                                                        })
                                                        .toList();
                                   }
                               });
    }
}
