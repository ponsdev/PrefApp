package com.example.vinicius.prefapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.database.sqlite.*;
import android.database.*;

import com.example.vinicius.prefapp.database.Database;
import com.example.vinicius.prefapp.dominio.RepositorioClientes;
import com.example.vinicius.prefapp.dominio.entidades.Cliente;

public class MainAct extends AppCompatActivity {

    private ListView lstLista;
    private EditText edtBusca;
    private ArrayAdapter<Cliente> adpClientes;

    private Database dataBase;
    private SQLiteDatabase conn;
    private RepositorioClientes repositorioClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        edtBusca = (EditText) findViewById(R.id.edtBusca);
        lstLista = (ListView) findViewById(R.id.lstLista);


        try {
            dataBase = new Database(this);
            conn = dataBase.getWritableDatabase();

            repositorioClientes = new RepositorioClientes(conn);
            adpClientes = repositorioClientes.buscaClientes(this);
            // repositorioClientes.testeInserirContatos();
            lstLista.setAdapter(adpClientes);

            /*AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Conexão criada com sucesso");
            dlg.setNeutralButton("OK", null);
            dlg.show();*/

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco de dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mni_ultimos) {
            return true;
        }
        switch (item.getItemId()){
            case (R.id.mni_ultimos):
                break;
            case (R.id.mni_criar):
                Intent it = new Intent(this, ActAddCliente.class);
                startActivityForResult(it, 0);
                break;
            case (R.id.mni_alterar):
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }
}
