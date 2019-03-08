package com.integratedworlds.mtt.jobs;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.integratedworlds.mtt.AppComponent;
import com.integratedworlds.mtt.di.Injectable;
import com.integratedworlds.mtt.di.providers.rest.IPharmacyServicesApi;
import com.integratedworlds.mtt.di.providers.sqlite.PharmaDbProvider;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public abstract class BaseJob extends Job implements Injectable {

    @Inject transient IPharmacyServicesApi pharmacyServicesApi;
    @Inject transient PharmaDbProvider pharmaDbProvider;
    @Inject transient EventBus bus;

    protected BaseJob(Params params) {
        super(params);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
