package com.integratedworlds.mtt.events;

import com.integratedworlds.mtt.model.Pharmacy;

import java.util.ArrayList;

public class PharmaciesEvent extends BaseEvent<ArrayList<Pharmacy>> {
    public PharmaciesEvent(String message) {
        super(message);
    }

    public PharmaciesEvent(String message, ArrayList<Pharmacy> resources) {
        super(message, resources);
    }
}
