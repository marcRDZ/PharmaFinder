package com.integratedworlds.mtt.di.providers.sqlite.parsers;

import android.database.Cursor;

import com.integratedworlds.mtt.di.providers.sqlite.PharmaDbContract;
import com.integratedworlds.mtt.model.Pharmacy;

public class PharmacyParser extends CursorParser<Pharmacy> {

    @Override
    public Pharmacy fromCursorRowToObject(Cursor row) {

        Pharmacy pharmacy = new Pharmacy();

        pharmacy.setId(row.getInt(row.getColumnIndex(PharmaDbContract.PharmaciesTable.ID_PHARMACY)));
        pharmacy.setName(row.getString(row.getColumnIndex(PharmaDbContract.PharmaciesTable.NAME)));
        pharmacy.setAddress(row.getString(row.getColumnIndex(PharmaDbContract.PharmaciesTable.ADDRESS)));
        pharmacy.setPhone(row.getString(row.getColumnIndex(PharmaDbContract.PharmaciesTable.PHONE)));
        pharmacy.setLat(row.getDouble(row.getColumnIndex(PharmaDbContract.PharmaciesTable.LAT)));
        pharmacy.setLng(row.getDouble(row.getColumnIndex(PharmaDbContract.PharmaciesTable.LONG)));
        pharmacy.setMts(row.getInt(row.getColumnIndex(PharmaDbContract.PharmaciesTable.MTS)));
        pharmacy.setShift(row.getInt(row.getColumnIndex(PharmaDbContract.PharmaciesTable.SHIFT)));
        pharmacy.setH24(row.getInt(row.getColumnIndex(PharmaDbContract.PharmaciesTable.H24)));

        return pharmacy;
    }
}
