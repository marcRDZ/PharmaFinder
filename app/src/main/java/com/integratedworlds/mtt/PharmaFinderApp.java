package com.integratedworlds.mtt;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class PharmaFinderApp extends DaggerApplication {

    public static final String APP_NAME = "PharmaFinderApp";
    private AppComponent appInjector;

    @Override
    public AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appInjector = DaggerAppComponent.builder().application(this).build();
        return appInjector;
    }

    public AppComponent getInjector() {
        return appInjector;
    }
}
