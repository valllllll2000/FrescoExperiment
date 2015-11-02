package com.vaxapp.frescoexperiment;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by valeria on 2/11/15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(getApplicationContext());
    }
}
