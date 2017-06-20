package com.example.vinicius.prefapp.database;

import android.content.Context;
import android.database.sqlite.*;

/**
 * Created by vinicius on 19/06/17.
 */

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "CLIENTES", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptSQL.getCreateClientes());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
