package com.integratedworlds.mtt.di.providers;

import android.app.Application;
import android.util.Log;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.di.DependencyInjector;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.integratedworlds.mtt.PharmaFinderApp;
import com.integratedworlds.mtt.di.Injectable;
import com.integratedworlds.mtt.di.providers.location.LocationProvider;
import com.integratedworlds.mtt.di.providers.rest.IPharmacyServicesApi;
import com.integratedworlds.mtt.di.providers.rest.RestServicesProvider;
import com.integratedworlds.mtt.di.providers.sqlite.PharmaDbProvider;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProvidersModule {

    @Provides
    @Singleton
    PharmaDbProvider providePharmacyDatabase(Application application) {
        return new PharmaDbProvider(application);
    }

    @Provides
    @Singleton
    LocationProvider provideLocationServices(Application application) {
        return new LocationProvider(application);
    }

    @Provides
    @Singleton
    IPharmacyServicesApi providePharmacyServices(){
        return new RestServicesProvider().createService(RestServicesProvider.PHARMACIES_URL, IPharmacyServicesApi.class);
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    JobManager provideJobManager(final Application application) {

        return new JobManager(new Configuration.Builder(application)
                .minConsumerCount(1) // always keep at least one consumer alive
                .maxConsumerCount(3) // up to 3 consumers at a time
                .loadFactor(3) // 3 jobs per consumer
                .consumerKeepAlive(30) // wait 2 minute
                .injector(new DependencyInjector() {
                    @Override
                    public void inject(Job job) {
                        if(job instanceof Injectable) {
                            ((Injectable) job).inject(((PharmaFinderApp)application).getInjector());
                        }
                    }
                })
                .customLogger(new CustomLogger() {
                    private static final String TAG = "JOBS";
                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
                        Log.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }

                    @Override
                    public void v(String text, Object... args) {

                    }
                }).build());
    }
}
