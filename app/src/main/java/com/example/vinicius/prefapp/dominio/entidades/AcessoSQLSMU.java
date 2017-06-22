package com.example.vinicius.prefapp.dominio.entidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.vinicius.prefapp.ClientesArrayAdapter;
import com.example.vinicius.prefapp.CustomArrayAdapter;
import com.example.vinicius.prefapp.R;
import com.example.vinicius.prefapp.UltimosArrayAdapter;

/**
 * Created by vinicius on 21/06/17.
 */

public class AcessoSQLSMU {

    private SQLiteDatabase conn;
    private Boolean existe = false;

    public AcessoSQLSMU(SQLiteDatabase conn) {

        this.conn = conn;

    }

    public void salvarDBSMU(respSMU objRespSMU) {

        Cursor cursor = conn.query("SQLSMU", null, null, null, null, null, null);

        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                if (cursor.getString(1).equals(objRespSMU.getNome())) {
                    existe = true;
                    break;
                }
            } while (cursor.moveToNext());
        }

        ContentValues values = new ContentValues();
        values.put("NOME", objRespSMU.getNome());
        values.put("PARECER", objRespSMU.getParecer());
        values.put("DATA", objRespSMU.getDate());
        values.put("DAUNID", objRespSMU.getDaUnidade());
        values.put("DAUNIDTEL", objRespSMU.getDaUnidadeTel());
        values.put("PARAUNID", objRespSMU.getParaUnidade());
        values.put("PARAUNIDTEL", objRespSMU.getParaUnidadeTel());

        if (existe == false) {
            conn.insertOrThrow("SQLSMU", null, values);
            Log.i("TESTE", objRespSMU.getNome() + " ADICIONADO DB!!! - " + objRespSMU.getId());
        } else {
            conn.update("SQLSMU", values, " _id = ? ", new String[]{ String.valueOf(objRespSMU.getId()) });
            Log.i("TESTE", objRespSMU.getNome() + " ALTERADO DB!!! - " + objRespSMU.getId());
        }

    }

    public void excluirDBSMU( String nome ) {
        conn.delete("SQLSMU", " NOME = ? ", new String[]{ String.valueOf( nome ) });
        //conn.delete("SQLSMU", " _id = ? ", new String[]{ String.valueOf(0)});
    }

    public UltimosArrayAdapter buscarDBSMU(Context context) {

        UltimosArrayAdapter adpUltimos = new UltimosArrayAdapter(context, R.layout.linha_ultimos);
        Cursor cursor = conn.query("SQLSMU", null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                respSMU objRespSMU = new respSMU();
                objRespSMU.setId(cursor.getLong(0));
                objRespSMU.setNome(cursor.getString(1));
                objRespSMU.setParecer(cursor.getString(2));
                objRespSMU.setDate(cursor.getString(3));
                objRespSMU.setDaUnidade(cursor.getString(4));
                objRespSMU.setDaUnidadeTel(cursor.getString(5));
                objRespSMU.setParaUnidade(cursor.getString(6));
                objRespSMU.setParaUnidadeTel(cursor.getString(7));

                adpUltimos.add(objRespSMU);
            } while (cursor.moveToNext());
        }
        return adpUltimos;

    }

}
