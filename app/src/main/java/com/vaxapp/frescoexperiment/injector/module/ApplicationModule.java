package com.vaxapp.frescoexperiment.injector.module;

import android.content.Context;
import com.vaxapp.data.executor.JobExecutor;
import com.vaxapp.data.repository.PhotoDataRepository;
import com.vaxapp.domain.executor.PostExecutionThread;
import com.vaxapp.domain.executor.ThreadExecutor;
import com.vaxapp.domain.repository.PhotoRepository;
import com.vaxapp.frescoexperiment.App;
import com.vaxapp.frescoexperiment.thread.UIThread;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class ApplicationModule {

    private final App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    // provide dependencies
    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    PhotoRepository providePhotoRepository(PhotoDataRepository photoDataRepository) {
        return photoDataRepository;
    }
}
