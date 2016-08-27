package com.vaxapp.frescoexperiment.injector.component;

import com.vaxapp.frescoexperiment.injector.PerActivity;
import com.vaxapp.frescoexperiment.injector.module.ActivityModule;
import com.vaxapp.frescoexperiment.injector.module.PhotoModule;
import com.vaxapp.frescoexperiment.presentation.MainActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PhotoModule.class})
public interface PhotoComponent {

    void inject(MainActivity mainActivity);
}
