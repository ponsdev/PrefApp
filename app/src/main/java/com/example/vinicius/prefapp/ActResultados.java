package com.example.vinicius.prefapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vinicius.prefapp.dominio.ScrapCMU;
import com.example.vinicius.prefapp.dominio.entidades.Cliente;
import com.example.vinicius.prefapp.dominio.entidades.respCMU;

/**
 * Created by vinic on 20/06/2017.
 */

public class ActResultados extends AppCompatActivity {

    private Cliente cliente;
    private ListView lstResultados;
    private ArrayAdapter<respCMU> adpResultados;
    private ScrapCMU scrapCMU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_resultados);

        lstResultados = (ListView) findViewById(R.id.lstResultados);

        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && (bundle.containsKey("CLIENTES"))){
            cliente = (Cliente) bundle.getSerializable("CLIENTES");
            /*Toast.makeText(this, cliente.getNome().toString() + " carregado...",
                    Toast.LENGTH_SHORT).show();*/
        }

        scrapCMU = new ScrapCMU();
        adpResultados = scrapCMU.buscaCMU(this, cliente);
        lstResultados.setAdapter(adpResultados);

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
