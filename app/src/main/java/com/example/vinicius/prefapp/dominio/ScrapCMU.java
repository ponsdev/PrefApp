package com.example.vinicius.prefapp.dominio;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by vinic on 20/06/2017.
 */

public class ScrapCMU {/*

    String cliente = "Jose";
    String cod = "10";
    String num = "1874";
    String ano = "2017";

    connectCMU(cliente, cod, num, ano);

    public connectCMU(String cliente, String cod, String num, String ano) {
        String url = "http://www2.curitiba.pr.gov.br/GTM/PMAT_RecursoSMU/Index/Resultado/frmImprime.aspx?CodRegional=10&NumProtocolo=";
        url += num;
        url += "&AnoProtocolo=";
        url += ano;
        url += "&Regional=CMU";
        Document doc = Jsoup.connect(url).get();

        String numProcesso = doc.select("span#lblProcesso").text();
        System.out.println(numProcesso);

        String zoneamento = doc.select("table#dgZoneamento").text();
        System.out.println(zoneamento);

        String decisao = doc.select("span#lblDecisao").text();
        System.out.println(decisao);

        String date = doc.select("span#lblData").text();
        System.out.println(date);

    }
*/
}
