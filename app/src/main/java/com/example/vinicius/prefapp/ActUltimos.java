package com.example.vinicius.prefapp;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vinicius.prefapp.app.MsgBox;
import com.example.vinicius.prefapp.database.DatabaseSMU;
import com.example.vinicius.prefapp.dominio.entidades.AcessoSQLSMU;
import com.example.vinicius.prefapp.dominio.entidades.respSMU;

/**
 * Created by vinicius on 22/06/17.
 */

public class ActUltimos extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private respSMU respSMU;
    private ListView lstUltimos;

    private DatabaseSMU databaseSMU;
    private SQLiteDatabase connSMU;
    private AcessoSQLSMU acessoSQLSMU;

    private CustomArrayAdapter adpUltimos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ultimos);

        lstUltimos = (ListView) findViewById(R.id.lstUltimos);

        try {
            databaseSMU = new DatabaseSMU(this);
            connSMU = databaseSMU.getWritableDatabase();
            acessoSQLSMU = new AcessoSQLSMU(connSMU);
            adpUltimos = acessoSQLSMU.buscarDBSMU(this);
            lstUltimos.setAdapter(adpUltimos);
        } catch (SQLException ex) {
            MsgBox.show(this, "Erro", "Erro ao criar o banco de dados resultados: " + ex.getMessage());
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i("EU", "AQUI");
    }
}
