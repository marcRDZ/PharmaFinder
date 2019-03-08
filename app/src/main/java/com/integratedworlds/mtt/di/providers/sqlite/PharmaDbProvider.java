package com.integratedworlds.mtt.di.providers.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.integratedworlds.mtt.di.providers.sqlite.parsers.IntervalParser;
import com.integratedworlds.mtt.di.providers.sqlite.parsers.PharmacyParser;
import com.integratedworlds.mtt.model.Interval;
import com.integratedworlds.mtt.model.Pharmacy;

import java.util.ArrayList;
import java.util.List;

public class PharmaDbProvider extends SQLiteOpenHelper {

    public PharmaDbProvider(@Nullable Context context) {
        super(context, PharmaDbContract.DATABASE_NAME, null, PharmaDbContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PharmaDbContract.PharmaciesTable.CREATE_TABLE);
        sqLiteDatabase.execSQL(PharmaDbContract.IntervalsTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(PharmaDbContract.PharmaciesTable.DELETE_TABLE);
        sqLiteDatabase.execSQL(PharmaDbContract.IntervalsTable.DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public List<Pharmacy> getPharmaciesWithIntervals() {

        List<Pharmacy> pharmacies = getPharmacies();

        if (pharmacies != null) {
            for (Pharmacy p : pharmacies) {
                ArrayList<Interval> intervals = (ArrayList<Interval>) getIntervalsByPharmacyId(p.getId());
                p.setIntervals(intervals);
            }

            return pharmacies;
        }
        else {

            return new ArrayList<>();
        }

    }

    private List<Pharmacy> getPharmacies() {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM PharmaciesTable";
        Cursor c = db.rawQuery(selectQuery, null);
        PharmacyParser pharmacyParser = new PharmacyParser();

        if(c.getCount() > 0) {
            return pharmacyParser.fromCursorToObjectList(c);
        }else {
            return null;
        }
    }

    private List<Interval> getIntervalsByPharmacyId(Integer id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM IntervalsTable WHERE PharmacyId=?";
        Cursor c = db.rawQuery(selectQuery, new String[] { String.valueOf(id) });
        IntervalParser intervalParser = new IntervalParser();

        if(c.getCount() > 0) {
            return intervalParser.fromCursorToObjectList(c);
        }else {
            return null;
        }
    }

    public void refreshPharmaciesDatabase(List<Pharmacy> pharmacies) {

        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(PharmaDbContract.PharmaciesTable.TABLE_NAME, null, null);
        database.delete(PharmaDbContract.IntervalsTable.TABLE_NAME, null, null);

        insertPharmacies(pharmacies, database);

        database.close();
    }

    private void insertPharmacies(List<Pharmacy> pharmacies, SQLiteDatabase database) {

        for (Pharmacy p : pharmacies) {
            insertPharmacy(p, database);
            insertIntervals(p.getIntervals(), p.getId(), database);
        }
    }

    private void insertPharmacy(Pharmacy pharmacy, SQLiteDatabase database) {


        ContentValues values = new ContentValues();

        values.put(PharmaDbContract.PharmaciesTable.ID_PHARMACY, pharmacy.getId());
        values.put(PharmaDbContract.PharmaciesTable.NAME, pharmacy.getName());
        values.put(PharmaDbContract.PharmaciesTable.ADDRESS, pharmacy.getAddress());
        values.put(PharmaDbContract.PharmaciesTable.PHONE, pharmacy.getPhone());
        values.put(PharmaDbContract.PharmaciesTable.LAT, pharmacy.getLat());
        values.put(PharmaDbContract.PharmaciesTable.LONG, pharmacy.getLng());
        values.put(PharmaDbContract.PharmaciesTable.MTS, pharmacy.getMts());
        values.put(PharmaDbContract.PharmaciesTable.SHIFT, pharmacy.getShift());
        values.put(PharmaDbContract.PharmaciesTable.H24, pharmacy.getH24());

        database.insert(PharmaDbContract.PharmaciesTable.TABLE_NAME, null, values);
    }

    private void insertIntervals(List<Interval> intervals, Integer pharmacyId, SQLiteDatabase database) {

        for (Interval i : intervals) {
            insertInterval(i, pharmacyId, database);
        }
    }

    private void insertInterval(Interval interval, Integer pharmacyId, SQLiteDatabase database) {

        ContentValues values = new ContentValues();

        values.put(PharmaDbContract.IntervalsTable.START_DATE, interval.getStartDate());
        values.put(PharmaDbContract.IntervalsTable.END_DATE, interval.getEndDate());
        values.put(PharmaDbContract.IntervalsTable.ID_PHARMACY_REF, pharmacyId);

        database.insert(PharmaDbContract.IntervalsTable.TABLE_NAME, null, values);
    }
}
