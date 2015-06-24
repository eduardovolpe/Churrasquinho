package br.com.e3.churrasquinho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBAdapterCarne {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] colunas = {DBHelper.ID_CARNE,DBHelper.NOME_CARNE,DBHelper.VALOR_CARNE,DBHelper.TIPO_CARNE};

    public DBAdapterCarne(Context context){
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

    public void adicionar (String nomeCarne, double valorCarne, String tipoCarne){
        ContentValues contentValues = new ContentValues();

        // pega e atribui os dados
        contentValues.put(DBHelper.NOME_CARNE, nomeCarne);
        contentValues.put(DBHelper.VALOR_CARNE, valorCarne);
        contentValues.put(DBHelper.TIPO_CARNE, tipoCarne);

        //insere no banco
        database.insert(DBHelper.TABELA_CARNE,null,contentValues);
    }


    public Cursor getCarne(){
        Cursor cursor = database.rawQuery(
                "select idCarne, nomeCarne, valorCarne, tipoCarne from " + DBHelper.TABELA_CARNE, null);

        return cursor;

    }

    private Carne cursorCarne(Cursor cursor){
        Carne carne = new Carne(cursor.getLong(0),
                cursor.getString(1),
                cursor.getDouble(2),
                cursor.getString(3));
        return carne;
    }
    public Carne getCarne(long idCarne){
        Cursor cursor = database.query(DBHelper.TABELA_CARNE,
                colunas, DBHelper.ID_CARNE + " = " + idCarne, null, null, null, null);

        cursor.moveToFirst();
        return cursorCarne(cursor);
    }

    public List<Carne> listar (){
        Cursor cursor = this.getCarne();
        List<Carne> lista = new ArrayList<Carne>();

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Carne carne;

            while (!cursor.isAfterLast()){
                carne = cursorCarne(cursor);
                lista.add(carne);
                cursor.moveToNext();
            }
        }
        return lista;
    }
/*
    public void editarCarne(Carne carne){
        ContentValues valores = new ContentValues();
        valores.put(DBHelper.NOME_CARNE, carne.getNomeCarne());
        valores.put(DBHelper.VALOR_CARNE, carne.getValorCarne());
        valores.put(DBHelper.TIPO_CARNE, carne.getTipoCarne());

        database.update(DBHelper.TABELA_CARNE, valores,
                DBHelper.ID_CARNE + " = ?", new String[] {String.valueOf(carne.getIdCarne())});
    }
*/

}
