package com.vaxapp.frescoexperiment;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.vaxapp.frescoexperiment.injector.component.ApplicationComponent;
import com.vaxapp.frescoexperiment.injector.component.DaggerApplicationComponent;
import com.vaxapp.frescoexperiment.injector.module.ApplicationModule;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        Fresco.initialize(getApplicationContext());
    }

    private void initializeInjector() {
        this.applicationComponent =
            DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        this.applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
