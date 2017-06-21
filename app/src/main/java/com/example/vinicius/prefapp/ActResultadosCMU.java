package com.example.vinicius.prefapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinicius.prefapp.dominio.ScrapCMU;
import com.example.vinicius.prefapp.dominio.ScrapSMU;
import com.example.vinicius.prefapp.dominio.entidades.Cliente;
import com.example.vinicius.prefapp.dominio.entidades.respCMU;
import com.example.vinicius.prefapp.dominio.entidades.respSMU;

import static com.example.vinicius.prefapp.R.id.lstResultados;

/**
 * Created by vinic on 21/06/2017.
 */

public class ActResultadosCMU extends AppCompatActivity {

    private Cliente cliente;
    private TextView txtNumProt;
    private TextView txtNumProc;
    private TextView txtZoneamento;
    private TextView txtDecisao;
    private ListView lstResultadosSMU;
    private ArrayAdapter<respCMU> adpResultados;
    private ArrayAdapter<respSMU> adpResultadosSMU;
    private ScrapCMU scrapCMU;
    private ScrapSMU scrapSMU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_resultados_cmu);

        txtNumProt = (TextView) findViewById(R.id.txtNumProt);
        txtNumProc = (TextView) findViewById(R.id.txtNumProc);
        txtZoneamento = (TextView) findViewById(R.id.txtZoneamento);
        txtDecisao = (TextView) findViewById(R.id.txtDecisao);
        lstResultadosSMU = (ListView) findViewById(R.id.lstResultadosSMU);

        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && (bundle.containsKey("CLIENTES"))){
            cliente = (Cliente) bundle.getSerializable("CLIENTES");
        }

        scrapCMU = new ScrapCMU();
        adpResultados = scrapCMU.buscaCMU(this, cliente);

        txtNumProt.setText(cliente.getCodigo() + "-" + cliente.getNumero() + "/" + cliente.getAno());
        txtNumProc.setText(scrapCMU.getNumProcesso());
        txtZoneamento.setText(scrapCMU.getZoneamento());
        txtDecisao.setText(scrapCMU.getDecisao());

        /*-----------------------------*/

        Log.i("SMU", scrapCMU.getNumProcesso().replace("01-", "").replace("/" + cliente.getAno(), ""));
        Cliente cliente2 = cliente;
        cliente2.setNumero(scrapCMU.getNumProcesso().replace("01-", "").replace("/" + cliente.getAno(), ""));
        scrapSMU = new ScrapSMU();
        adpResultadosSMU = scrapSMU.buscaSMU(this, cliente);
        lstResultadosSMU.setAdapter(adpResultadosSMU);

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