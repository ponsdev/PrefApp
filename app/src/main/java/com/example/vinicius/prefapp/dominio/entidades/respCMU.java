package com.example.vinicius.prefapp.dominio.entidades;

import java.io.Serializable;

/**
 * Created by vinicius on 20/06/17.
 */

public class respCMU implements Serializable {

    private long id;
    private String nome;
    private String codigo;
    private String numero;
    private String ano;
    private String respostaCMU;

    public void respCMU() {
        setId(0);
    }

    @Override
    public String toString() {
        return getNome() + " - " + getCodigo() + "-" + getNumero() + "/" +
                getAno() + " - " + getRespostaCMU();
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getRespostaCMU() {
        return respostaCMU;
    }

    public void setRespostaCMU(String respostaCMU) {
        this.respostaCMU = respostaCMU;
    }
}
