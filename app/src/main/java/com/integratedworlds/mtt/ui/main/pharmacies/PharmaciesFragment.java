package com.integratedworlds.mtt.ui.main.pharmacies;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.integratedworlds.mtt.R;
import com.integratedworlds.mtt.di.ActivityScoped;
import com.integratedworlds.mtt.model.Pharmacy;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

import static com.integratedworlds.mtt.ui.main.splash.SplashFragment.MY_LAT;
import static com.integratedworlds.mtt.ui.main.splash.SplashFragment.MY_LNG;

@ActivityScoped
public class PharmaciesFragment extends DaggerFragment implements IPharmacies.View {

    private PharmaciesAdapter pharmaciesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Double lat, lng;

    @Inject IPharmacies.Presenter pharmaciesPresenter;

    @BindView(R.id.pharmacies_recycler_view)
    RecyclerView pharmaciesRecyclerView;
    @BindView(R.id.pb_pharmacies)
    ProgressBar progressBar;

    @Inject
    public PharmaciesFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.lat = getArguments().getDouble(MY_LAT);
        this.lng = getArguments().getDouble(MY_LNG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pharmacies, container, false);
        ButterKnife.bind(this, view);

        layoutManager = new LinearLayoutManager(getContext());
        pharmaciesRecyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        pharmaciesPresenter.takeView(this);
        pharmaciesPresenter.fetchPharmacies(lat, lng);
    }

    @Override
    public void onPause() {
        super.onPause();
        pharmaciesPresenter.dropView();
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    @Override
    public void setPharmaciesList(ArrayList<Pharmacy> pharmacies) {

        progressBar.setVisibility(View.INVISIBLE);
        pharmaciesAdapter = new PharmaciesAdapter(pharmacies);
        pharmaciesRecyclerView.setAdapter(pharmaciesAdapter);
    }
}
