package com.example.vinicius.prefapp.dominio;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by vinic on 20/06/2017.
 */

public class ScrapSMU {/*

    Scanner scanner = new Scanner(System.in);

    private String cliente = "Jose";
    private String cod = "01";
    private String num = "059169";
    private String ano = "2017";

    try {
        connectSMU(cliente, cod, num, ano);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

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
}
