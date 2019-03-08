package com.integratedworlds.mtt.ui.main.splash;

import android.location.Location;
import android.support.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.integratedworlds.mtt.di.ActivityScoped;
import com.integratedworlds.mtt.di.providers.location.LocationProvider;

import javax.inject.Inject;

@ActivityScoped
public class SplashPresenter implements ISplash.Presenter {

    @Nullable ISplash.View splashView;

    @Inject LocationProvider locationProvider;

    @Inject
    public SplashPresenter() { }

    @Override
    public void takeView(ISplash.View view) {
        this.splashView = view;
    }

    @Override
    public void dropView() {
        this.splashView = null;
    }

    @Override
    public void requestLastKnownLocation() {

        if (splashView != null) {

            locationProvider.getLastKnownLocation(splashView.getViewContext(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    splashView.deliverLocation(location);
                }
            });
        }
    }
}
