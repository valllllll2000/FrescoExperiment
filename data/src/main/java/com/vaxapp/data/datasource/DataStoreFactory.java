package com.vaxapp.data.datasource;

import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataStoreFactory {

    @Inject
    Lazy<NetworkPhotoDataStore> networkPhotoDataStore;

    @Inject
    public DataStoreFactory() {
    }

    public PhotoDataStore createPhotoDataStore() {
        return networkPhotoDataStore.get();
    }
}
