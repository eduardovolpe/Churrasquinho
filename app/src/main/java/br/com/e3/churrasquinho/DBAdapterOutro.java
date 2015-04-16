package br.com.e3.churrasquinho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 08/04/2015.
 */
public class DBAdapterOutro {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] colunas = {DBHelper.ID_OUTRO,DBHelper.NOME_OUTRO,DBHelper.VALOR_OUTRO};

    public DBAdapterOutro(Context context){
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

    public void adicionar (String nomeOutro, double valorOutro){
        ContentValues contentValues = new ContentValues();

        // pega e atribui os dados
        contentValues.put(DBHelper.NOME_OUTRO, nomeOutro);
        contentValues.put(DBHelper.VALOR_OUTRO, valorOutro);

        //insere no banco
        database.insert(DBHelper.TABELA_OUTRO,null,contentValues);
    }

    public Cursor getOutro(){
        Cursor cursor = database.rawQuery(
                "select idOutro, nomeOutro, valorOutro from " + DBHelper.TABELA_OUTRO, null);

        return cursor;

    }

    private Outro cursorOutro(Cursor cursor){
        Outro outro = new Outro(cursor.getLong(0),
                cursor.getString(1),
                cursor.getDouble(2));
        return outro;
    }
    public Outro getOutro(long idOutro){
        Cursor cursor = database.query(DBHelper.TABELA_OUTRO,
                colunas, DBHelper.ID_OUTRO + " = " + idOutro, null, null, null, null);

        cursor.moveToFirst();
        return cursorOutro(cursor);
    }

    public List<Outro> listar (){
        Cursor cursor = this.getOutro();
        List<Outro> lista = new ArrayList<Outro>();

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Outro outro;

            while (!cursor.isAfterLast()){
                outro = cursorOutro(cursor);
                lista.add(outro);
                cursor.moveToNext();
            }
        }
        return lista;
    }


}
