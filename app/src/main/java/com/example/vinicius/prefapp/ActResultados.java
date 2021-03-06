package com.example.vinicius.prefapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinicius.prefapp.dominio.ScrapSMU;
import com.example.vinicius.prefapp.dominio.entidades.Cliente;
import com.example.vinicius.prefapp.dominio.entidades.respSMU;

/**
 * Created by vinic on 20/06/2017.
 */

public class ActResultados extends AppCompatActivity {

    private Cliente cliente;
    private TextView txtNumProt;
    private TextView txtNomeClienteSMU;
    private ListView lstResultados;
    private ArrayAdapter<respSMU> adpResultados;
    private ScrapSMU scrapSMU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_resultados);

        txtNumProt = (TextView) findViewById(R.id.txtNumProtRes);
        txtNomeClienteSMU = (TextView) findViewById(R.id.txtNomeClienteSMU);
        lstResultados = (ListView) findViewById(R.id.lstResultados);

        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && (bundle.containsKey("CLIENTES"))){
            cliente = (Cliente) bundle.getSerializable("CLIENTES");
            /*Toast.makeText(this, cliente.getNome().toString() + " carregado...",
                    Toast.LENGTH_SHORT).show();*/
        }

        scrapSMU = new ScrapSMU();
        adpResultados = scrapSMU.buscaSMU(this, cliente);
        lstResultados.setAdapter(adpResultados);

        txtNumProt.setText(cliente.getCodigo() + "-" + cliente.getNumero() + "/" + cliente.getAno());
        txtNomeClienteSMU.setText(cliente.getNome());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_respostas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case (R.id.mni_alterar):
                Intent it = new Intent(this, ActAddCliente.class);
                it.putExtra("CLIENTES", cliente);
                startActivityForResult(it, 0);
                break;
            case (R.id.mni_fechar):
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
