package com.integratedworlds.mtt.ui.main.splash;

import android.location.Location;

import com.integratedworlds.mtt.ui.IPresenter;
import com.integratedworlds.mtt.ui.IView;

public interface ISplash {

    interface View extends IView<Presenter> {

        boolean hasLocationPermissions();

        void requestLocationPermissions();

        void deliverLocation(Location location);
    }

    interface Presenter extends IPresenter<View> {

        void requestLastKnownLocation();
    }
}
