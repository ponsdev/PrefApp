package com.example.vinicius.prefapp.dominio.entidades;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by vinicius on 21/06/17.
 */

public class AcessoSQLSMU {

    private SQLiteDatabase conn;

    public AcessoSQLSMU(SQLiteDatabase conn) {

        this.conn = conn;

    }

    public void inserirDBSMU(respSMU objRespSMU) {
        ContentValues values = new ContentValues();
        values.put("NOME", objRespSMU.getNome());
        values.put("PARECER", objRespSMU.getParecer());
        values.put("DATA", objRespSMU.getDate());
        values.put("DAUNID", objRespSMU.getDaUnidade());
        values.put("DAUNIDTEL", objRespSMU.getDaUnidadeTel());
        values.put("PARAUNID", objRespSMU.getParaUnidade());
        values.put("PARAUNIDTEL", objRespSMU.getParaUnidadeTel());

        conn.insertOrThrow("SQLSMU", null, values);
    }

    public void alterarDBSMU(respSMU objRespSMU) {
        ContentValues values = new ContentValues();
        values.put("NOME", objRespSMU.getNome());
        values.put("PARECER", objRespSMU.getParecer());
        values.put("DATA", objRespSMU.getDate());
        values.put("DAUNID", objRespSMU.getDaUnidade());
        values.put("DAUNIDTEL", objRespSMU.getDaUnidadeTel());
        values.put("PARAUNID", objRespSMU.getParaUnidade());
        values.put("PARAUNIDTEL", objRespSMU.getParaUnidadeTel());

        conn.update("SQLSMU", values, " _id = ? ", new String[]{ String.valueOf(objRespSMU.getId()) });
    }

    public void excluirDBSMU( long id ) {
        conn.delete("SQLSMU", " _id = ? ", new String[]{ String.valueOf( id ) });
    }

}
