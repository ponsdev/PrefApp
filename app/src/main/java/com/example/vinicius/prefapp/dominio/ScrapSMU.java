package com.example.vinicius.prefapp.dominio;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

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

    public ArrayAdapter<respSMU> buscaSMU(Context context, Cliente cliente) {

        ArrayAdapter<respSMU> adpResultadosSMU = new ArrayAdapter<respSMU>(context,
                android.R.layout.simple_list_item_1);
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

        for (int y = 0; y < parecer.length; y++) {
            objRespSMU = new respSMU();
            objRespSMU.setNome(cliente.getNome());
            objRespSMU.setDate(date[y]);
            objRespSMU.setParecer(parecer[y]);
            objRespSMU.setDaUnidade(daUnidade[y]);
            objRespSMU.setDaUnidadeTel(daUnidadeTel[y]);
            objRespSMU.setParaUnidade(paraUnidade[y]);
            objRespSMU.setParaUnidadeTel(paraUnidadeTel[y]);

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


}



    /*

    private static void connectSMU(String cliente, String cod, String num, String ano) throws IOException {

        String url = "http://consultaprotocolo.curitiba.pr.gov.br/frmConsProtocolo.aspx?txtNumProtocolo=";
        url += num;
        url += "&txtAnoProtocolo=";
        url += ano;
        url += "&txtTipoProtocolo=";
        url += cod;


        Document doc = Jsoup.connect(url).get();
        Elements tv = doc.select("tr.TramiteValor").select("span.valor");
        Elements tp = doc.select("tr.TramiteParecer");

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

        for (int y = 0; y < parecer.length; y++) {
            System.out.println("-------------------------------------" + parecer.length);
            System.out.println(date[y]);
            System.out.println(parecer[y]);
            //System.out.println(daUnidade[y]);
            //System.out.println(daUnidadeTel[y]);
            //System.out.println(paraUnidade[y]);
            //System.out.println(paraUnidadeTel[y]);
        }

    }
*/
