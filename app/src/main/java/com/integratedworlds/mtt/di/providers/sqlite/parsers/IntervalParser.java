package com.integratedworlds.mtt.di.providers.sqlite.parsers;

import android.database.Cursor;

import com.integratedworlds.mtt.di.providers.sqlite.PharmaDbContract;
import com.integratedworlds.mtt.model.Interval;

public class IntervalParser extends CursorParser<Interval> {
    
    @Override
    public Interval fromCursorRowToObject(Cursor row) {

        Interval interval = new Interval();

        interval.setStartDate(row.getString(row.getColumnIndex(PharmaDbContract.IntervalsTable.START_DATE)));
        interval.setEndDate(row.getString(row.getColumnIndex(PharmaDbContract.IntervalsTable.END_DATE)));

        return interval;
    }
}
