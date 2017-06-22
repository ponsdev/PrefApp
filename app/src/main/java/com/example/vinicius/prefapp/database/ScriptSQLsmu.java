package com.example.vinicius.prefapp.database;

/**
 * Created by vinicius on 21/06/17.
 */

public class ScriptSQLsmu {

    public static String getCreateDB(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS SQLSMU ( ");
        sqlBuilder.append("_id           INTEGER      NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NOME          VARCHAR (200), ");
        sqlBuilder.append("PARECER       VARCHAR (1000), ");
        sqlBuilder.append("DATA          VARCHAR (6), ");
        sqlBuilder.append("DAUNID        VARCHAR (200), ");
        sqlBuilder.append("DAUNIDTEL     VARCHAR (15), ");
        sqlBuilder.append("PARAUNID      VARCHAR (200), ");
        sqlBuilder.append("PARAUNIDTEL   VARCHAR (15) ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

}
