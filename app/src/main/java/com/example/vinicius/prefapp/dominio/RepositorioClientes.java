package com.example.vinicius.prefapp.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.widget.ArrayAdapter;

import com.example.vinicius.prefapp.ClientesArrayAdapter;
import com.example.vinicius.prefapp.R;
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

    public void alterar(Cliente cliente){

        ContentValues values = new ContentValues();
        values.put("NOME", cliente.getNome());
        values.put("CODIGO", cliente.getCodigo());
        values.put("NUMERO", cliente.getNumero());
        values.put("ANO", cliente.getAno());
        values.put("SETOR", cliente.getSetor());


        conn.update("CLIENTES", values, " _id = ? ", new String[]{ String.valueOf(cliente.getId()) });

    }

    public void excluir(long id) {
        conn.delete("CLIENTES", " _id = ? ", new String[]{ String.valueOf( id ) });
    }

    public ClientesArrayAdapter buscaClientes(Context context) {

        ClientesArrayAdapter adpClientes = new ClientesArrayAdapter(context,
                R.layout.linha_clientes);
        Cursor cursor = conn.query("CLIENTES", null, null, null, null, null, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Cliente cliente = new Cliente();
                cliente.setId(cursor.getLong(0));
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
