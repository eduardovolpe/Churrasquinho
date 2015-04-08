package br.com.e3.churrasquinho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 06/04/2015.
 */
public class DBAdapterBebida {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] colunas = {DBHelper.ID_BEBIDA,DBHelper.NOME_BEBIDA,DBHelper.VALOR_BEBIDA};

    public DBAdapterBebida(Context context){
        dbHelper = new DBHelper(context);
    }
    //abre a conexão
    public void open(){
        database = dbHelper.getWritableDatabase();
    }
    //encerra a conexão
    public void close(){
        database.close();
    }

    public void adicionar (String nomeBebida, double valorBebida){
        ContentValues contentValues = new ContentValues();

        // pega e atribui os dados
        contentValues.put(DBHelper.NOME_BEBIDA, nomeBebida);
        contentValues.put(DBHelper.VALOR_BEBIDA, valorBebida);

        //insere no banco
        database.insert(DBHelper.TABELA_BEBIDA,null,contentValues);
    }

    public Cursor getBebida(){
        Cursor cursor = database.rawQuery(
                "select idBebida,nomeBebida,valorBebida from " + DBHelper.TABELA_BEBIDA, null);

        return cursor;

    }

    private Bebida cursorBebida(Cursor cursor){
        Bebida bebida = new Bebida(cursor.getLong(0),
                cursor.getString(1),
                cursor.getDouble(2));
        return bebida;
    }
    public Bebida getBebida(long idBebida){
        Cursor cursor = database.query(DBHelper.TABELA_BEBIDA,
                colunas, DBHelper.ID_BEBIDA + " = " + idBebida, null, null, null, null);

        cursor.moveToFirst();
        return cursorBebida(cursor);
    }

    public List<Bebida> listar (){
        Cursor cursor = this.getBebida();
        List<Bebida> lista = new ArrayList<Bebida>();

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Bebida bebida;

            while (!cursor.isAfterLast()){
                bebida = cursorBebida(cursor);
                lista.add(bebida);
                cursor.moveToNext();
            }
        }
        return lista;
    }

}
