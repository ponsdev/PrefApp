package com.example.vinicius.prefapp.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vinicius.prefapp.CustomArrayAdapter;
import com.example.vinicius.prefapp.R;
import com.example.vinicius.prefapp.app.MsgBox;
import com.example.vinicius.prefapp.database.DatabaseSMU;
import com.example.vinicius.prefapp.dominio.entidades.AcessoSQLSMU;
import com.example.vinicius.prefapp.dominio.entidades.Cliente;
import com.example.vinicius.prefapp.dominio.entidades.respSMU;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by vinic on 20/06/2017.
 */

public class ScrapSMU {

    private Cliente cliente;
    private Document docs;
    private respSMU objRespSMU;

    private DatabaseSMU databaseSMU;
    private SQLiteDatabase connSMU;
    private AcessoSQLSMU acessoSQLSMU;

    public CustomArrayAdapter buscaSMU(Context context, Cliente cliente) {

        CustomArrayAdapter adpResultadosSMU = new CustomArrayAdapter(context,
                R.layout.linha_resultados);
        this.cliente = cliente;

        connectSMU(context, cliente.getNome(), cliente.getCodigo(), cliente.getNumero(),
                cliente.getAno());

/*--------------------------------------------------------*/

        Elements tv = docs.select("tr.TramiteValor").select("span.valor");
        Elements tp = docs.select("tr.TramiteParecer");

        String parecer[] = new String[tp.size()];
        String date[] = new String[100];
        String daUnidade[] = new String[100];
        String daUnidadeTel[] = new String[100];
        String paraUnidade[] = new String[100];
        String paraUnidadeTel[] = new String[100];

        // PARECER
        int id = 0;
        for (Element i : tp) {
            parecer[id]	= i.text().replaceAll("Parecer do Protocolo: ", "");
            parecer[id] = parecer[id].substring(0, 1).toUpperCase() + parecer[id].substring(1).toLowerCase();
            //parecer[id] = parecer[id].substring(1);
            //System.out.println(parecer[id]);
            id++;
        }

        // DATA
        id = 0;
        int contador = 0;
        for (Element r : tv) {
            switch (contador) {
                case 0:
                    date[id] = r.text();
                    contador++;
                    break;
                case 1:
                    daUnidade[id] = r.text();
                    contador++;
                    break;
                case 2:
                    daUnidadeTel[id] = r.text();
                    contador++;
                    break;
                case 3:
                    paraUnidade[id] = r.text();
                    contador++;
                    break;
                case 4:
                    paraUnidadeTel[id] = r.text();
                    contador = 0;
                    id++;
                    break;
            }
        }
        objRespSMU = new respSMU();

        try {
            databaseSMU = new DatabaseSMU(context);
            connSMU = databaseSMU.getWritableDatabase();
            acessoSQLSMU = new AcessoSQLSMU(connSMU);
        } catch (SQLException ex) {
            MsgBox.show(context, "Erro", "Erro ao criar o banco de dados resultados: " + ex.getMessage());
        }
        /*
        objRespSMU = new respSMU();
        objRespSMU.setNome(cliente.getNome());
        objRespSMU.setDate(date[0]);
        objRespSMU.setParecer(parecer[0]);
        objRespSMU.setDaUnidade(daUnidade[0]);
        objRespSMU.setDaUnidadeTel(daUnidadeTel[0]);
        objRespSMU.setParaUnidade(paraUnidade[0]);
        objRespSMU.setParaUnidadeTel(paraUnidadeTel[0]);
        acessoSQLSMU.salvarDBSMU(objRespSMU);
        */
        for (int y = 0; y < parecer.length; y++) {
            objRespSMU = new respSMU();
            objRespSMU.setNome(cliente.getNome());
            objRespSMU.setDate(date[y]);
            objRespSMU.setParecer(parecer[y]);
            objRespSMU.setDaUnidade(daUnidade[y]);
            objRespSMU.setDaUnidadeTel(daUnidadeTel[y]);
            objRespSMU.setParaUnidade(paraUnidade[y]);
            objRespSMU.setParaUnidadeTel(paraUnidadeTel[y]);
            if (y == 0) {
                acessoSQLSMU.salvarDBSMU(objRespSMU);
            }
            adpResultadosSMU.add(objRespSMU);
        }

        return adpResultadosSMU;

    }

    public void connectSMU(Context context, String cliente, String cod,
                           String num, String ano) {

        String url = "http://consultaprotocolo.curitiba.pr.gov.br/frmConsProtocolo.aspx?txtNumProtocolo=";
        url += num;
        url += "&txtAnoProtocolo=";
        url += ano;
        url += "&txtTipoProtocolo=";
        url += cod;

        final String finalUrl = url;
        Thread downloadThread = new Thread() {
            public void run() {
                Document doc;
                try {
                    doc = Jsoup.connect(finalUrl).get();
                    setDocs(doc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        downloadThread.start();
        try {
            downloadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        downloadThread.interrupt();

    }

    public Document getDocs() {
        return docs;
    }

    public void setDocs(Document docs) {
        this.docs = docs;
    }

    /* ------------------------------------------------ */

    public respSMU smuUpdate(Context context, Cliente cliente){

        this.cliente = cliente;
        connectSMU(context, cliente.getNome(), cliente.getCodigo(), cliente.getNumero(),
                cliente.getAno());
        Elements tv = docs.select("tr.TramiteValor").select("span.valor");
        Elements tp = docs.select("tr.TramiteParecer");

        String parecer[] = new String[tp.size()];
        String date[] = new String[100];
        String daUnidade[] = new String[100];
        String daUnidadeTel[] = new String[100];
        String paraUnidade[] = new String[100];
        String paraUnidadeTel[] = new String[100];

        // PARECER
        int id = 0;
        for (Element i : tp) {
            parecer[id]	= i.text().replaceAll("Parecer do Protocolo: ", "");
            parecer[id] = parecer[id].substring(0, 1).toUpperCase() + parecer[id].substring(1).toLowerCase();
            //parecer[id] = parecer[id].substring(1);
            //System.out.println(parecer[id]);
            id++;
        }

        // DATA
        id = 0;
        int contador = 0;
        for (Element r : tv) {
            switch (contador) {
                case 0:
                    date[id] = r.text();
                    contador++;
                    break;
                case 1:
                    daUnidade[id] = r.text();
                    contador++;
                    break;
                case 2:
                    daUnidadeTel[id] = r.text();
                    contador++;
                    break;
                case 3:
                    paraUnidade[id] = r.text();
                    contador++;
                    break;
                case 4:
                    paraUnidadeTel[id] = r.text();
                    contador = 0;
                    id++;
                    break;
            }
        }
        objRespSMU = new respSMU();

        try {
            databaseSMU = new DatabaseSMU(context);
            connSMU = databaseSMU.getWritableDatabase();
            acessoSQLSMU = new AcessoSQLSMU(connSMU);
        } catch (SQLException ex) {
            MsgBox.show(context, "Erro", "Erro ao criar o banco de dados resultados: " + ex.getMessage());
        }
        objRespSMU = new respSMU();
        objRespSMU.setNome(cliente.getNome());
        objRespSMU.setDate(date[0]);
        objRespSMU.setParecer(parecer[0]);
        objRespSMU.setDaUnidade(daUnidade[0]);
        objRespSMU.setDaUnidadeTel(daUnidadeTel[0]);
        objRespSMU.setParaUnidade(paraUnidade[0]);
        objRespSMU.setParaUnidadeTel(paraUnidadeTel[0]);

        return objRespSMU;
    }


}
