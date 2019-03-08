package com.integratedworlds.mtt.ui.main.pharmacies;

import com.integratedworlds.mtt.model.Pharmacy;
import com.integratedworlds.mtt.ui.IPresenter;
import com.integratedworlds.mtt.ui.IView;

import java.util.ArrayList;

public interface IPharmacies {


    interface View extends IView<Presenter> {

        void setPharmaciesList(ArrayList<Pharmacy> pharmacies);
    }

    interface Presenter extends IPresenter<View> {

        void fetchPharmacies(Double lat, Double lng);
    }
}
