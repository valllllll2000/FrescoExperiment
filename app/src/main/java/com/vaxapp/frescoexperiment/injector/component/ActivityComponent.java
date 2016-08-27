package com.vaxapp.frescoexperiment.injector.component;

import android.app.Activity;
import com.vaxapp.frescoexperiment.injector.PerActivity;
import com.vaxapp.frescoexperiment.injector.module.ActivityModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    //Exposed to sub-graphs.
    Activity activity();
}
