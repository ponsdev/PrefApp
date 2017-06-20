package com.example.vinicius.prefapp;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case (R.id.mni_acao1):

                break;
            case (R.id.mni_acao2):

                break;
            case (R.id.mni_acao3):
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
