package com.integratedworlds.mtt.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Interval {

    @SerializedName("Inicio")
    private String startDate;
    @SerializedName("Fin")
    private String endDate;

    private SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM HH:mm", Locale.getDefault());


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFormattedStartDate() {
        try {
            return formatter.format(parser.parse(startDate));
        } catch (Exception e) {
            e.printStackTrace();
            return "-/- 00:00";
        }
    }

    public String getFormattedEndDate() {
        try {
            return formatter.format(parser.parse(endDate));
        } catch (Exception e) {
            e.printStackTrace();
            return "-/- 00:00";
        }
    }
}
