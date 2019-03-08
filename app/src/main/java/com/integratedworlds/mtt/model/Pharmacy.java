package com.integratedworlds.mtt.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pharmacy {

    @SerializedName("Id")
    private Integer id;
    @SerializedName("Nombre")
    private String name;
    @SerializedName("Dir")
    private String address;
    @SerializedName("Tlf")
    private String phone;
    @SerializedName("Lat")
    private Double lat;
    @SerializedName("Long")
    private Double lng;
    @SerializedName("Mts")
    private Integer mts;
    @SerializedName("Horarios")
    private ArrayList<Interval> intervals;
    @SerializedName("Turno")
    private Integer shift;
    @SerializedName("H24")
    private Integer h24;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMts() {
        return mts;
    }

    public void setMts(Integer mts) {
        this.mts = mts;
    }

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public Integer getH24() {
        return h24;
    }

    public void setH24(Integer h24) {
        this.h24 = h24;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public ArrayList<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(ArrayList<Interval> intervals) {
        this.intervals = intervals;
    }
}
