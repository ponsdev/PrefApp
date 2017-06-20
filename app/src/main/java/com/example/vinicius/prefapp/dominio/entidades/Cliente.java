package com.example.vinicius.prefapp.dominio.entidades;

/**
 * Created by vinicius on 19/06/17.
 */

public class Cliente {

    private long id;
    private String nome;
    private String codigo;
    private String numero;
    private String ano;
    private String setor;

    public void Cliente() {

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

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return nome + " - " + codigo + "-" + numero + "/" + ano + " - " + setor;
    }
}
