package com.example.vinicius.prefapp.dominio;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import com.example.vinicius.prefapp.dominio.entidades.Cliente;
import com.example.vinicius.prefapp.dominio.entidades.respCMU;

/**
 * Created by vinic on 20/06/2017.
 */

public class ScrapCMU {

    private Cliente cliente;
    private Document docs;
    private String numProcesso;
    private String zoneamento;
    private String decisao;
    private String date;


    public ArrayAdapter<respCMU> buscaCMU(Context context, Cliente cliente) {

        ArrayAdapter<respCMU> adpResultadosCMU = new ArrayAdapter<respCMU>(context,
                android.R.layout.simple_list_item_1);
        this.cliente = cliente;

        connectCMU(context, cliente.getNome().toString(), cliente.getCodigo().toString(),
                cliente.getNumero().toString(), cliente.getAno().toString());

        return adpResultadosCMU;
    }

    public void connectCMU(Context context, String cliente, String cod,
                              String num, String ano) {

        String url = "http://www2.curitiba.pr.gov.br/GTM/PMAT_RecursoSMU/Index/Resultado/frmImprime.aspx?CodRegional=10&NumProtocolo=";
        url += num;
        url += "&AnoProtocolo=";
        url += ano;
        url += "&Regional=CMU";

        Log.i("EU", "ENTRANDO");

        //new cntCMU().execute(url);

        final String finalUrl = url;
        Thread downloadThread = new Thread() {
            public void run() {
                Document doc;
                try {
                    doc = Jsoup.connect(finalUrl).get();
                    docs = doc;
                    setNumProcesso(doc.select("span#lblProcesso").text());
                    setZoneamento(doc.select("table#dgZoneamento").text());
                    setDecisao(doc.select("span#lblDecisao").text());
                    setDate(doc.select("span#lblData").text());
                    Log.i("EU", doc.select("span#lblData").text());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        downloadThread.start();
        Toast.makeText(context, numProcesso, Toast.LENGTH_LONG).show();


        /*String numProcesso = doc.select("span#lblProcesso").text();
        Toast.makeText(context, numProcesso, Toast.LENGTH_SHORT).show();

        String zoneamento = doc.select("table#dgZoneamento").text();

        String decisao = doc.select("span#lblDecisao").text();

        String date = doc.select("span#lblData").text();*/

    }

    public Document getDocs() {
        return docs;
    }
    public void setDocs(Document docs) {
        this.docs = docs;
    }

    public String getNumProcesso() {
        return numProcesso;
    }

    public void setNumProcesso(String numProcesso) {
        this.numProcesso = numProcesso;
    }

    public String getZoneamento() {
        return zoneamento;
    }

    public void setZoneamento(String zoneamento) {
        this.zoneamento = zoneamento;
    }

    public String getDecisao() {
        return decisao;
    }

    public void setDecisao(String decisao) {
        this.decisao = decisao;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
