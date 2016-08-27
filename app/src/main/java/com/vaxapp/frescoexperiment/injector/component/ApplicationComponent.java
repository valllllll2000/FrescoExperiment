package com.vaxapp.frescoexperiment.injector.component;

import android.content.Context;
import com.vaxapp.domain.executor.PostExecutionThread;
import com.vaxapp.domain.executor.ThreadExecutor;
import com.vaxapp.domain.repository.PhotoRepository;
import com.vaxapp.frescoexperiment.App;
import com.vaxapp.frescoexperiment.injector.module.ApplicationModule;
import com.vaxapp.frescoexperiment.presentation.BaseActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
    ApplicationModule.class
})
public interface ApplicationComponent {

    void inject(App app);

    void inject(BaseActivity baseActivity);

    Context context();

    ThreadExecutor getThreadExecutor();

    PostExecutionThread getPostExecutionThread();

    PhotoRepository photoRepository();

}
