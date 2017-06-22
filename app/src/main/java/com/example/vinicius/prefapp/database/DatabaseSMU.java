package com.example.vinicius.prefapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vinicius on 21/06/17.
 */

public class DatabaseSMU extends SQLiteOpenHelper {

    public DatabaseSMU(Context context) {
        super(context, "SQLSMU", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptSQLsmu.getCreateDB());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}