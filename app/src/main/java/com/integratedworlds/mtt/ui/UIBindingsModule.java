package com.integratedworlds.mtt.ui;

import com.integratedworlds.mtt.di.ActivityScoped;
import com.integratedworlds.mtt.ui.main.MainActivity;
import com.integratedworlds.mtt.ui.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mrodriguezd on 20/12/2017.
 */
@Module
public abstract class UIBindingsModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

}
