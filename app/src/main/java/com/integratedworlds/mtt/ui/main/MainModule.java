package com.integratedworlds.mtt.ui.main;

import com.integratedworlds.mtt.di.ActivityScoped;
import com.integratedworlds.mtt.di.FragmentScoped;
import com.integratedworlds.mtt.ui.main.pharmacies.IPharmacies;
import com.integratedworlds.mtt.ui.main.pharmacies.PharmaciesFragment;
import com.integratedworlds.mtt.ui.main.pharmacies.PharmaciesPresenter;
import com.integratedworlds.mtt.ui.main.splash.ISplash;
import com.integratedworlds.mtt.ui.main.splash.SplashFragment;
import com.integratedworlds.mtt.ui.main.splash.SplashPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SplashFragment splashFragment();

    @ActivityScoped
    @Binds
    abstract ISplash.Presenter provideSplashPresenter(SplashPresenter splashPresenter);


    @FragmentScoped
    @ContributesAndroidInjector
    abstract PharmaciesFragment pharmaciesFragment();

    @ActivityScoped
    @Binds
    abstract IPharmacies.Presenter providePharmaciesPresenter(PharmaciesPresenter pharmaciesPresenter);
}
