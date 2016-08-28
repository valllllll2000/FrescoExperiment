package com.vaxapp.data.datasource.realm.entity;

import android.support.annotation.NonNull;
import com.vaxapp.data.entity.FlickrApiPhoto;
import javax.inject.Inject;

public class RealmFlickrMapper {

    @Inject
    public RealmFlickrMapper() {
    }

    public RealmFlickrPhoto transform(@NonNull FlickrApiPhoto flickrApiPhoto) {
        RealmFlickrPhoto realmFlickrPhoto = new RealmFlickrPhoto();
        realmFlickrPhoto.setFarm(flickrApiPhoto.getFarm());
        realmFlickrPhoto.setId(flickrApiPhoto.getId());
        realmFlickrPhoto.setSecret(flickrApiPhoto.getSecret());
        realmFlickrPhoto.setServer(flickrApiPhoto.getServer());
        realmFlickrPhoto.setTitle(flickrApiPhoto.getTitle());
        return realmFlickrPhoto;
    }

    public FlickrApiPhoto transform(@NonNull RealmFlickrPhoto realmFlickrPhoto) {
        FlickrApiPhoto flickrApiPhoto = new FlickrApiPhoto();
        flickrApiPhoto.setFarm(realmFlickrPhoto.getFarm());
        flickrApiPhoto.setId(realmFlickrPhoto.getId());
        flickrApiPhoto.setSecret(realmFlickrPhoto.getSecret());
        flickrApiPhoto.setServer(realmFlickrPhoto.getServer());
        flickrApiPhoto.setTitle(realmFlickrPhoto.getTitle());
        return flickrApiPhoto;
    }
}
