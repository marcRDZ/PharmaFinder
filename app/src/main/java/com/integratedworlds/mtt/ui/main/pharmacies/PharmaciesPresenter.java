package com.integratedworlds.mtt.ui.main.pharmacies;


import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.birbit.android.jobqueue.JobManager;
import com.integratedworlds.mtt.di.ActivityScoped;
import com.integratedworlds.mtt.events.PharmaciesEvent;
import com.integratedworlds.mtt.jobs.PharmaciesJob;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import static com.integratedworlds.mtt.PharmaFinderApp.APP_NAME;

@ActivityScoped
public class PharmaciesPresenter implements IPharmacies.Presenter {

    private final String TAG = APP_NAME + " --> " + this.getClass().getSimpleName();

    @Nullable IPharmacies.View pharmaciesView;

    @Inject JobManager jobManager;
    @Inject EventBus bus;

    @Inject
    public PharmaciesPresenter() {
    }

    @Override
    public void takeView(IPharmacies.View view) {
        this.pharmaciesView = view;
        if(!bus.isRegistered(this)) {
            bus.register(this);
        }
    }

    @Override
    public void dropView() {
        this.pharmaciesView = null;
        bus.unregister(this);
    }

    @Override
    public void fetchPharmacies(Double lat, Double lng) {
        jobManager.addJobInBackground(new PharmaciesJob(lat, lng));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PharmaciesEvent pharmaciesEvent) {
        Log.d(TAG, pharmaciesEvent.getMessage());

        if(pharmaciesView != null) {
            if (pharmaciesEvent.getResources() != null) {

                Toast.makeText(pharmaciesView.getViewContext(), pharmaciesEvent.getMessage(), Toast.LENGTH_SHORT).show();
                pharmaciesView.setPharmaciesList(pharmaciesEvent.getResources());
            } else {
                Toast.makeText(pharmaciesView.getViewContext(), pharmaciesEvent.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
