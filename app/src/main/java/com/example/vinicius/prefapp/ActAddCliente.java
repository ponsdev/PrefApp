package com.example.vinicius.prefapp;

import android.app.ActionBar;
import android.app.Activity;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vinicius.prefapp.app.MsgBox;
import com.example.vinicius.prefapp.database.Database;
import com.example.vinicius.prefapp.dominio.RepositorioClientes;
import com.example.vinicius.prefapp.dominio.entidades.Cliente;

/**
 * Created by vinic on 19/06/2017.
 */

public class ActAddCliente extends AppCompatActivity {

    private EditText edtNome;
    private Spinner spnCodigo;
    private EditText edtNumero;
    private Spinner spnAno;
    private Spinner spnSetor;

    private ArrayAdapter<String> adpCodigo;
    private ArrayAdapter<String> adpAno;
    private ArrayAdapter<String> adpSetor;

    private Database dataBase;
    private SQLiteDatabase conn;
    private RepositorioClientes repositorioClientes;
    private Cliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actaddcliente);

        edtNome = (EditText) findViewById(R.id.edtNome);
        spnCodigo = (Spinner) findViewById(R.id.spnCodigo);
        edtNumero = (EditText) findViewById(R.id.edtNumero);
        spnAno = (Spinner) findViewById(R.id.spnAno);
        spnSetor = (Spinner) findViewById(R.id.spnSetor);

        adpCodigo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpCodigo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCodigo.setAdapter(adpCodigo);
        adpCodigo.add("90");
        adpCodigo.add("01");
        adpCodigo.add("91");

        adpAno = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpAno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAno.setAdapter(adpAno);

        for (int x=2017; x>2009; x--){
            adpAno.add(Integer.toString(x));
        }

        adpSetor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpSetor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSetor.setAdapter(adpSetor);
        adpSetor.add("SMU");
        adpSetor.add("CMU");
        adpSetor.add("UNIFICAÇÃO");


        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && (bundle.containsKey("CLIENTES"))){
            cliente = (Cliente) bundle.getSerializable("CLIENTES");
            preencheDados();
        }
        else { cliente = new Cliente(); }


        try {
            dataBase = new Database(this);
            conn = dataBase.getWritableDatabase();
            repositorioClientes = new RepositorioClientes(conn);

        } catch (SQLException ex) {
            MsgBox.show(this, "Erro!!!", "Erro ao criar o banco de dados: " + ex.getMessage());
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
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case (R.id.mni_salvar):
                salvar();
                finish();
                break;
            case (R.id.mni_excluir):
                excluir();
                finish();
                break;
            case (R.id.mni_fechar):
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void preencheDados() {
        edtNome.setText(cliente.getNome());
        spnCodigo.setSelection(adpCodigo.getPosition(cliente.getCodigo()));
        edtNumero.setText(cliente.getNumero());
        spnAno.setSelection(adpAno.getPosition(cliente.getAno()));
        spnSetor.setSelection(adpSetor.getPosition(cliente.getSetor()));
        Toast.makeText(this, String.valueOf(cliente.getId()), Toast.LENGTH_LONG).show();
    }

    private void salvar() {
        try {
            //cliente = new Cliente();
            cliente.setNome(edtNome.getText().toString());
            cliente.setCodigo(spnCodigo.getSelectedItem().toString());
            cliente.setNumero(edtNumero.getText().toString());
            cliente.setAno(spnAno.getSelectedItem().toString());
            cliente.setSetor(spnSetor.getSelectedItem().toString());

            if (cliente.getId() == 0) {
                repositorioClientes.inserir(cliente);
                Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_LONG).show();
            } else {
                repositorioClientes.alterar(cliente);
                Toast.makeText(this, "Cadastro alterado", Toast.LENGTH_LONG).show();
            }

        } catch (Exception ex) {
            MsgBox.show(this, "OPS....", "Erro ao inserir os dados: " + ex.getMessage());
        }

    }

    private void excluir() {
        try {
            repositorioClientes.excluir( cliente.getId() );

        } catch (Exception ex) {
            MsgBox.show(this, "Erro", "Erro ao excluir os dados: " + ex.getMessage());
        }
    }
}
