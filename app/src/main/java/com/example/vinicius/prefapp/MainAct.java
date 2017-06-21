package com.example.vinicius.prefapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.database.sqlite.*;
import android.database.*;

import com.example.vinicius.prefapp.app.MsgBox;
import com.example.vinicius.prefapp.database.Database;
import com.example.vinicius.prefapp.dominio.RepositorioClientes;
import com.example.vinicius.prefapp.dominio.entidades.Cliente;

public class MainAct extends AppCompatActivity implements AdapterView.OnItemClickListener{

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
        lstLista.setOnItemClickListener(this);


        try {
            dataBase = new Database(this);
            conn = dataBase.getWritableDatabase();

            repositorioClientes = new RepositorioClientes(conn);
            adpClientes = repositorioClientes.buscaClientes(this);
            lstLista.setAdapter(adpClientes);

            FiltraDados filtraDados = new FiltraDados(adpClientes);
            edtBusca.addTextChangedListener(filtraDados);

        } catch (SQLException ex) {
            MsgBox.show(this, "Erro", "Erro ao criar o banco de dados: " + ex.getMessage());
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (conn != null){
            conn.close();
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

        adpClientes = repositorioClientes.buscaClientes(this);
        lstLista.setAdapter(adpClientes);
        FiltraDados filtraDados = new FiltraDados(adpClientes);
        edtBusca.addTextChangedListener(filtraDados);

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

        /*
        Cliente cliente = adpClientes.getItem(i);
        Intent it = new Intent(this, ActAddCliente.class);
        it.putExtra("CLIENTES", cliente);
        startActivityForResult(it, 0);
        */


        Cliente cliente = adpClientes.getItem(i);
        if (cliente.getSetor().equals("CMU")) {
            Intent it = new Intent(this, ActResultadosCMU.class);
            it.putExtra("CLIENTES", cliente);
            startActivityForResult(it, 0);
        } else {
            Intent it = new Intent(this, ActResultados.class);
            it.putExtra("CLIENTES", cliente);
            startActivityForResult(it, 0);
        }

    }

    private class FiltraDados implements TextWatcher {

        ArrayAdapter<Cliente> arrayAdapter;

        private FiltraDados(ArrayAdapter<Cliente> arrayAdapter) {
            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            arrayAdapter .getFilter().filter(charSequence);
        }

        @Override
        public void afterTextChanged(Editable editable) { }
    }
}
