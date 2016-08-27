package com.vaxapp.frescoexperiment.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Scope for objects whose lifetime conforms to Activity life
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
