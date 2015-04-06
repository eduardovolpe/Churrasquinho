package br.com.e3.churrasquinho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 06/04/2015.
 */
public class DBHelper extends SQLiteOpenHelper {


    public static String NOME_BANCO = "carne.db";
    public static int VERSAO_BANCO  = 1;

    public static String ID      = "id";
    public static String NOME   = "nome";
    public static String VALOR  = "valor";

    public static String TABELA = "carne";

    public static String CREATE_DATABASE = "create table " + TABELA + " ( " +
            ID + "integer primary key autoincrement" +
            NOME + "text" +
            VALOR + "double );";

    public static String DROP_DATABASE = "drop table if exists" + TABELA;

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
