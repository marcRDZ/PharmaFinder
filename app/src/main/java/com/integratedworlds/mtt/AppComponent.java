package com.integratedworlds.mtt;

import android.app.Application;

import com.integratedworlds.mtt.di.providers.ProvidersModule;
import com.integratedworlds.mtt.jobs.BaseJob;
import com.integratedworlds.mtt.ui.UIBindingsModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AppModule.class, ProvidersModule.class, UIBindingsModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<PharmaFinderApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

    void inject(BaseJob baseJob);

}
