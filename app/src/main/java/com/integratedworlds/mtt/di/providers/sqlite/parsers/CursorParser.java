package com.integratedworlds.mtt.di.providers.sqlite.parsers;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos.rodriguezdiaz on 18/12/2016.
 */

public abstract class CursorParser<T> {

    public List<T> fromCursorToObjectList (Cursor cursor) {

        if (cursor == null) {
            return null;
        }

        List<T> models = new ArrayList<>();
        while(cursor.moveToNext()) {
            T model = fromCursorRowToObject(cursor);
            if (model != null) {
                models.add(model);
            }
        }
        cursor.close();

        return models;
    }

    public abstract T fromCursorRowToObject (Cursor row);

}
