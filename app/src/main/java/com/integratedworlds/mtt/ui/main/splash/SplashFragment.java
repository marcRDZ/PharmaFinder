package com.integratedworlds.mtt.ui.main.splash;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.integratedworlds.mtt.R;
import com.integratedworlds.mtt.di.ActivityScoped;
import com.integratedworlds.mtt.ui.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

@ActivityScoped
public class SplashFragment extends DaggerFragment implements ISplash.View, EasyPermissions.PermissionCallbacks {


    private static final String[] LOCATION_PERMISSIONS = { Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION };
    private static final int LOCATION_REQUEST_CODE = 1003;
    public static final String MY_LAT = "latitude";
    public static final String MY_LNG = "longitude";

    @Inject ISplash.Presenter splashPresenter;

    @Inject
    public SplashFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        splashPresenter.takeView(this);
        if (hasLocationPermissions()) {
            splashPresenter.requestLastKnownLocation();
        } else {
            requestLocationPermissions();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        splashPresenter.dropView();
    }

    @Override
    public Context getViewContext() {
        return this.getActivity();
    }

    @Override
    public boolean hasLocationPermissions() {
        return EasyPermissions.hasPermissions(getContext(), LOCATION_PERMISSIONS);
    }

    @Override
    public void requestLocationPermissions() {
        EasyPermissions.requestPermissions(this, getString(R.string.rationale_ask), LOCATION_REQUEST_CODE, LOCATION_PERMISSIONS);
    }

    @Override
    public void deliverLocation(Location location) {

        Bundle b = new Bundle();
        b.putDouble(MY_LAT, location.getLatitude());
        b.putDouble(MY_LNG, location.getLongitude());

        ((MainActivity) getActivity()).goPharmaciesView(b);

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

        if (requestCode == LOCATION_REQUEST_CODE && perms.size() > 0 ) {

            splashPresenter.requestLastKnownLocation();

        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        } else {
            requestLocationPermissions();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
