package com.example.vinicius.prefapp.dominio.entidades;

import java.io.Serializable;

/**
 * Created by vinic on 21/06/2017.
 */

public class respSMU implements Serializable {

    private long id;
    private String nome;
    private String date;
    private String parecer;
    private String daUnidade;
    private String daUnidadeTel;
    private String paraUnidade;
    private String paraUnidadeTel;

    public void respSMU() { setId(0); }

    @Override
    public String toString() {
        return getDate() + "\n" + getParecer();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public String getDaUnidade() {
        return daUnidade;
    }

    public void setDaUnidade(String daUnidade) {
        this.daUnidade = daUnidade;
    }

    public String getDaUnidadeTel() {
        return daUnidadeTel;
    }

    public void setDaUnidadeTel(String daUnidadeTel) {
        this.daUnidadeTel = daUnidadeTel;
    }

    public String getParaUnidade() {
        return paraUnidade;
    }

    public void setParaUnidade(String paraUnidade) {
        this.paraUnidade = paraUnidade;
    }

    public String getParaUnidadeTel() {
        return paraUnidadeTel;
    }

    public void setParaUnidadeTel(String paraUnidadeTel) {
        this.paraUnidadeTel = paraUnidadeTel;
    }
}
