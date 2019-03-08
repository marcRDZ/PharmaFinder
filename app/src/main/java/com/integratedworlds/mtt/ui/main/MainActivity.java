package com.integratedworlds.mtt.ui.main;

import android.os.Bundle;

import com.integratedworlds.mtt.R;
import com.integratedworlds.mtt.ui.main.pharmacies.PharmaciesFragment;
import com.integratedworlds.mtt.ui.main.splash.SplashFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject SplashFragment splashFragment;
    @Inject PharmaciesFragment pharmaciesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_main, splashFragment)
                .commit();
    }

    public void goPharmaciesView(Bundle bundle) {

        pharmaciesFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, pharmaciesFragment)
                .commit();

        getSupportActionBar().show();

    }

}
