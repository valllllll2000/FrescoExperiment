package com.vaxapp.frescoexperiment.injector.module;

import com.vaxapp.domain.interactor.GetPhotos;
import com.vaxapp.domain.interactor.UseCase;
import com.vaxapp.frescoexperiment.injector.PerActivity;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

@Module
public class PhotoModule {

    @Provides
    @PerActivity
    @Named("getPhotos")
    UseCase provideGetPhotosUseCase(GetPhotos getPhotos) {
        return getPhotos;
    }
}
