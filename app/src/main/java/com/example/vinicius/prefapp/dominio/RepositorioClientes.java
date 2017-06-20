package com.example.vinicius.prefapp.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.widget.ArrayAdapter;

import com.example.vinicius.prefapp.dominio.entidades.Cliente;

/**
 * Created by vinicius on 19/06/17.
 */

public class RepositorioClientes {

    private SQLiteDatabase conn;

    public RepositorioClientes(SQLiteDatabase conn){

        this.conn = conn;

    }

    public void inserir(Cliente cliente){

        ContentValues values = new ContentValues();
        values.put("NOME", cliente.getNome());
        values.put("CODIGO", cliente.getCodigo());
        values.put("NUMERO", cliente.getNumero());
        values.put("ANO", cliente.getAno());
        values.put("SETOR", cliente.getSetor());

        conn.insertOrThrow("CLIENTES", null, values);

    }
/*
    public void testeInserirContatos() {
        for (int i=0; i<10; i++){
            ContentValues values = new ContentValues();
            values.put("NOME", "VINICIUS");
            conn.insertOrThrow("CLIENTES", null, values);
        }

    }
*/
    public ArrayAdapter<Cliente> buscaClientes(Context context) {

        ArrayAdapter<Cliente> adpClientes = new ArrayAdapter<Cliente>(context,
                android.R.layout.simple_list_item_1);
        Cursor cursor = conn.query("CLIENTES", null, null, null, null, null, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Cliente cliente = new Cliente();
                cliente.setNome(cursor.getString(1));
                cliente.setCodigo(cursor.getString(2));
                cliente.setNumero(cursor.getString(3));
                cliente.setAno(cursor.getString(4));
                cliente.setSetor(cursor.getString(5));

                adpClientes.add(cliente);
            } while (cursor.moveToNext());
        }

        return adpClientes;

    }

}
