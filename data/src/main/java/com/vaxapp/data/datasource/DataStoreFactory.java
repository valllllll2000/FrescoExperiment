package com.vaxapp.data.datasource;

import com.vaxapp.data.datasource.network.NetworkPhotoDataSource;
import com.vaxapp.data.datasource.realm.RealmPhotoDataSource;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataStoreFactory {

    @Inject
    Lazy<NetworkPhotoDataSource> networkPhotoDataSource;

    @Inject
    Lazy<RealmPhotoDataSource> realmPhotoDataSource;

    @Inject
    public DataStoreFactory() {
    }

    public PhotoDataSource createNetworkPhotoDataSource() {
        return networkPhotoDataSource.get();
    }

    public PhotoDataSource createRealmPhotoDataSource() {
        return realmPhotoDataSource.get();
    }

}
