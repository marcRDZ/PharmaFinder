package com.integratedworlds.mtt.jobs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.integratedworlds.mtt.R;
import com.integratedworlds.mtt.events.PharmaciesEvent;
import com.integratedworlds.mtt.model.Pharmacy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Response;

import static com.integratedworlds.mtt.PharmaFinderApp.APP_NAME;

public class PharmaciesJob extends BaseJob {

    private final String TAG = APP_NAME + " --> " + this.getClass().getSimpleName();
    private Double lat, lng;
    private String now;

    public PharmaciesJob(Double lat, Double lng) {
        super(new Params(1).requireNetwork());
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public void onAdded() {

        now = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.getDefault()).format(Calendar.getInstance().getTime());

        Context context = getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm.getActiveNetworkInfo() == null || !cm.getActiveNetworkInfo().isConnectedOrConnecting()) {

            ArrayList<Pharmacy> pharmacies = (ArrayList<Pharmacy>) pharmaDbProvider.getPharmaciesWithIntervals();
            bus.post(new PharmaciesEvent(
                    getApplicationContext().getString(R.string.pharmacies_db_result, pharmacies.size()),
                    pharmacies)
            );
        }
    }

    @Override
    public void onRun() throws Throwable {
        Log.d(TAG, " running");

        Response<ArrayList<Pharmacy>> response =
                this.pharmacyServicesApi.getByLocation(now, lat, lng, false, 25, 0).execute();

        if(response.isSuccessful()) {

            if (response.body() != null) {

                bus.post(new PharmaciesEvent(
                        getApplicationContext().getString(R.string.pharmacies_result_successful, response.body().size()),
                        response.body())
                );

                pharmaDbProvider.refreshPharmaciesDatabase(response.body());
            } else {

                bus.post(new PharmaciesEvent(getApplicationContext().getString(R.string.pharmacies_result_unsuccessful)));
            }
        } else {

            Log.d(TAG, response.errorBody().string());
            bus.post(new PharmaciesEvent(response.errorBody().string()));
        }
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }
}
