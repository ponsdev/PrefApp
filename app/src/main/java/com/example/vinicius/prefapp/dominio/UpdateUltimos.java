package com.example.vinicius.prefapp.dominio;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.vinicius.prefapp.CustomArrayAdapter;
import com.example.vinicius.prefapp.MainAct;
import com.example.vinicius.prefapp.R;
import com.example.vinicius.prefapp.UltimosArrayAdapter;
import com.example.vinicius.prefapp.app.MsgBox;
import com.example.vinicius.prefapp.database.Database;
import com.example.vinicius.prefapp.database.DatabaseSMU;
import com.example.vinicius.prefapp.dominio.entidades.AcessoSQLSMU;
import com.example.vinicius.prefapp.dominio.entidades.Cliente;
import com.example.vinicius.prefapp.dominio.entidades.respCMU;
import com.example.vinicius.prefapp.dominio.entidades.respSMU;

import static com.example.vinicius.prefapp.R.id.lstUltimos;

/**
 * Created by vinic on 22/06/2017.
 */

public class UpdateUltimos {

    private Cliente cliente;

    public static UltimosArrayAdapter atualizar(Context context) {

        DatabaseSMU databaseSMU;
        SQLiteDatabase connSMU = null;
        AcessoSQLSMU acessoSQLSMU;

        Database dataBase;
        SQLiteDatabase conn = null;

        respSMU objRespSMU = new respSMU();

        String s = "";
        String msg = "";

        try {
            databaseSMU = new DatabaseSMU(context);
            connSMU = databaseSMU.getWritableDatabase();
            acessoSQLSMU = new AcessoSQLSMU(connSMU);

        } catch (SQLException ex) {
            MsgBox.show(context, "Erro", "Erro ao criar o banco de dados resultados: " + ex.getMessage());
        }

        try {
            dataBase = new Database(context);
            conn = dataBase.getWritableDatabase();

        } catch (SQLException ex) {
            MsgBox.show(context, "Erro", "Erro ao criar o banco de dados clientes: " + ex.getMessage());
        }

        UltimosArrayAdapter adpUltimos = new UltimosArrayAdapter(context, R.layout.linha_ultimos);
        Cursor cursor = connSMU.query("SQLSMU", null, null, null, null, null, null);
        Cursor cursorCliente = conn.query("CLIENTES", null, null, null, null, null, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                cursorCliente.moveToFirst();
                do {
                    if (cursorCliente.getString(1).equals(cursor.getString(1))){
                        acessoSQLSMU = new AcessoSQLSMU(connSMU);
                        Cliente cliente = new Cliente();
                        cliente.setId(cursorCliente.getLong(0));
                        cliente.setNome(cursorCliente.getString(1));
                        cliente.setCodigo(cursorCliente.getString(2));
                        cliente.setNumero(cursorCliente.getString(3));
                        cliente.setAno(cursorCliente.getString(4));
                        cliente.setSetor(cursorCliente.getString(5));

                        if (cliente.getSetor().equals("CMU")){
                            ScrapCMU scrapCMU = new ScrapCMU();
                            Cliente cliente2 = new Cliente();
                            cliente2.setNome(cliente.getNome());
                            cliente2.setCodigo(cliente.getCodigo());
                            cliente2.setNumero(scrapCMU.cmuNumProcesso(context, cliente));
                            cliente2.setAno(cliente.getAno());
                            cliente2.setSetor(cliente.getSetor());
                            ScrapSMU scrapSMU = new ScrapSMU();
                            objRespSMU = scrapSMU.smuUpdate(context, cliente2);
                            adpUltimos.add(objRespSMU);
                            s = acessoSQLSMU.salvarDBSMU(objRespSMU);
                        }
                        else {
                            ScrapSMU scrapSMU = new ScrapSMU();
                            objRespSMU = scrapSMU.smuUpdate(context, cliente);
                            adpUltimos.add(objRespSMU);
                            s = acessoSQLSMU.salvarDBSMU(objRespSMU);
                        }

                        msg += s + "\n";

                    }

                }while (cursorCliente.moveToNext());

            }while (cursor.moveToNext());
        }

        Toast.makeText(context, msg, Toast.LENGTH_LONG);
        return adpUltimos;

    }

}
