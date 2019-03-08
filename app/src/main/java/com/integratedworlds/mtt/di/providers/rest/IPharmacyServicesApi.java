package com.integratedworlds.mtt.di.providers.rest;

import com.integratedworlds.mtt.model.Pharmacy;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPharmacyServicesApi {


    @GET("/api/FarmaciasAbiertas/FindFarmacias")
    Call<ArrayList<Pharmacy>> getByLocation(@Query("fecha") String date, @Query("latitud") Double lat, @Query("longitud") Double lng,
                                            @Query("mostrarCerradas") Boolean showClosed, @Query("rows") Integer rows, @Query("radio") Integer radio);
}
