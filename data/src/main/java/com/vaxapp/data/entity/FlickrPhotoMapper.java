package com.vaxapp.data.entity;

import com.mobandme.android.transformer.Transformer;
import com.vaxapp.domain.entity.FlickrPhoto;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FlickrPhotoMapper {

    @Inject
    public FlickrPhotoMapper() {
    }

    public FlickrPhoto transform(FlickrApiPhoto flickrApiPhoto) {
        Transformer apiFlickrPhotoTransformer = new Transformer.Builder().build(FlickrApiPhoto.class);
        return apiFlickrPhotoTransformer.transform(flickrApiPhoto, FlickrPhoto.class);
    }
}
