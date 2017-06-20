package com.example.vinicius.prefapp.database;

/**
 * Created by vinicius on 19/06/17.
 */

public class ScriptSQL {

    public static String getCreateClientes(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS CLIENTES ( ");
        sqlBuilder.append("_id           INTEGER      NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NOME          VARCHAR (200), ");
        sqlBuilder.append("CODIGO        VARCHAR (2), ");
        sqlBuilder.append("NUMERO        VARCHAR (6), ");
        sqlBuilder.append("ANO           VARCHAR (4), ");
        sqlBuilder.append("SETOR         VARCHAR (10) ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

}
