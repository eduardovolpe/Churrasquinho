package br.com.e3.churrasquinho;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 06/04/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static String NOME_BANCO = "churrasquinho.db";
    public static int VERSAO_BANCO  = 1;

    public static String ID_CARNE     = "idCarne";
    public static String NOME_CARNE   = "nomeCarne";
    public static String VALOR_CARNE  = "valorCarne";
    public static String TABELA_CARNE = "carne";

    public static String ID_BEBIDA     = "idBebida";
    public static String NOME_BEBIDA   = "nomeBebida";
    public static String VALOR_BEBIDA  = "valorBebida";
    public static String TABELA_BEBIDA = "bebida";

    public static String ID_ACOMPANHAMENTO     = "idAcompanhamento";
    public static String NOME_ACOMPANHAMENTO   = "nomeAcompanhamento";
    public static String VALOR_ACOMPANHAMENTO  = "valorAcompanhamento";
    public static String TABELA_ACOMPANHAMENTO = "acompanhamento";

    public static String ID_OUTRO      = "idOutro";
    public static String NOME_OUTRO    = "nomeOutro";
    public static String VALOR_OUTRO   = "valorOutro";
    public static String TABELA_OUTRO  = "outro";


    public static String CREATE_DATABASE =
            // Criando a tabela CARNE
            "create table " + TABELA_CARNE + " ( " +
            ID_CARNE + " integer primary key autoincrement, " +
            NOME_CARNE + " text, " +
            VALOR_CARNE + " double );" +

            // Criando a tabela BEBIDA
            " create table " + TABELA_BEBIDA + " ( " +
            ID_BEBIDA + " integer primary key autoincrement, " +
            NOME_BEBIDA + " text, " +
            VALOR_BEBIDA + " double );" +

            // Criando a tabela ACOMPANHAMENTOS
            " create table " + TABELA_ACOMPANHAMENTO + " ( " +
            ID_ACOMPANHAMENTO + " integer primary key autoincrement ," +
            NOME_ACOMPANHAMENTO + " text, " +
            VALOR_ACOMPANHAMENTO + " double );" +

            // Criando a tabela ACOMPANHAMENTOS
            " create table " + TABELA_OUTRO + " ( " +
            ID_OUTRO + " integer primary key autoincrement, " +
            NOME_OUTRO + " text, " +
            VALOR_OUTRO + " double );";

    public static String DROP_DATABASE =
            "drop table if exists " + TABELA_CARNE + " ; " +
            "drop table if exists " + TABELA_BEBIDA + " ; " +
            "drop table if exists " + TABELA_ACOMPANHAMENTO + " ; " +
            "drop table if exists " + TABELA_OUTRO;

    public DBHelper (Context context){
        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }

    @Override //criando.
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    @Override //apagando e recriando.
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL(DROP_DATABASE);
        onCreate(db);

    }
}
