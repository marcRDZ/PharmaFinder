package com.integratedworlds.mtt.di.providers.sqlite;

import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;
import com.integratedworlds.mtt.model.Interval;

import java.util.ArrayList;

public class PharmaDbContract {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pharma_database.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INT";
    private static final String REAL_TYPE = " REAL";
    private static final String INT_PK_TYPE = "  INTEGER PRIMARY KEY ";
    private static final String FOREIGN_KEY_TYPE = " FOREIGN KEY ";
    private static final String REF = " REFERENCES ";
    private static final String COMMA_SEP = ", ";

    private PharmaDbContract() {}

    public abstract class PharmaciesTable implements BaseColumns {

        public static final String TABLE_NAME = "PharmaciesTable";
        public static final String ID_PHARMACY = "ID";
        public static final String NAME = "Name";
        public static final String ADDRESS = "Address";
        public static final String PHONE = "Phone";
        public static final String LAT = "Latitude";
        public static final String LONG = "Longitude";
        public static final String MTS = "Mts";
        public static final String SHIFT = "Shift";
        public static final String H24 = "H24";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                ID_PHARMACY + INT_TYPE + COMMA_SEP +
                NAME + TEXT_TYPE + COMMA_SEP +
                ADDRESS + TEXT_TYPE + COMMA_SEP +
                PHONE + TEXT_TYPE + COMMA_SEP +
                LAT + REAL_TYPE + COMMA_SEP +
                LONG + REAL_TYPE + COMMA_SEP +
                MTS + INT_TYPE + COMMA_SEP +
                SHIFT + INT_TYPE + COMMA_SEP +
                H24 + INT_TYPE +" )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public abstract class IntervalsTable implements BaseColumns {

        public static final String TABLE_NAME = "IntervalsTable";
        public static final String START_DATE = "StartDate";
        public static final String END_DATE = "EndDate";
        public static final String ID_PHARMACY_REF = "PharmacyId";


        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + INT_PK_TYPE + COMMA_SEP +
                START_DATE + TEXT_TYPE + COMMA_SEP +
                END_DATE + TEXT_TYPE + COMMA_SEP +
                ID_PHARMACY_REF + INT_TYPE+ COMMA_SEP
                + FOREIGN_KEY_TYPE +"(" + ID_PHARMACY_REF + ")" + REF
                + PharmaciesTable.TABLE_NAME + "(_id)" +" )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
