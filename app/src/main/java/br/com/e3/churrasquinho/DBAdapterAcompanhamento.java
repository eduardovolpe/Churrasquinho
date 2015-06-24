package br.com.e3.churrasquinho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class DBAdapterAcompanhamento {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] colunas = {DBHelper.ID_ACOMPANHAMENTO,DBHelper.NOME_ACOMPANHAMENTO,DBHelper.VALOR_ACOMPANHAMENTO,DBHelper.TIPO_ACOMPANHAMENTO};

    public DBAdapterAcompanhamento(Context context){
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

    public void adicionar (String nomeAcompanhamento, double valorAcompanhamento, String tipoAcompanhamento){
        ContentValues contentValues = new ContentValues();

        // pega e atribui os dados
        contentValues.put(DBHelper.NOME_ACOMPANHAMENTO, nomeAcompanhamento);
        contentValues.put(DBHelper.VALOR_ACOMPANHAMENTO, valorAcompanhamento);
        contentValues.put(DBHelper.TIPO_ACOMPANHAMENTO, tipoAcompanhamento);

        //insere no banco
        database.insert(DBHelper.TABELA_ACOMPANHAMENTO,null,contentValues);
    }

    public Cursor getAcompanhamentos(){
        Cursor cursor = database.rawQuery(
                "select idAcompanhamento, nomeAcompanhamento, valorAcompanhamento, tipoAcompanhamento from " + DBHelper.TABELA_ACOMPANHAMENTO, null);

        return cursor;

    }

    private Acompanhamento cursorAcompanhamento(Cursor cursor){
        Acompanhamento acompanhamento = new Acompanhamento(cursor.getLong(0),
                cursor.getString(1),
                cursor.getDouble(2),
                cursor.getString(3));
        return acompanhamento;
    }

    public Acompanhamento getAcompanhamento(long idAcompanhamento){
        Cursor cursor = database.query(DBHelper.TABELA_ACOMPANHAMENTO,
                colunas, DBHelper.ID_ACOMPANHAMENTO + " = " + idAcompanhamento, null, null, null, null);

        cursor.moveToFirst();
        return cursorAcompanhamento(cursor);
    }

    public List<Acompanhamento> listar (){
        Cursor cursor = this.getAcompanhamentos();
        List<Acompanhamento> lista = new ArrayList<Acompanhamento>();

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Acompanhamento acompanhamento;

            while (!cursor.isAfterLast()){
                acompanhamento = cursorAcompanhamento(cursor);
                lista.add(acompanhamento);
                cursor.moveToNext();
            }
        }
        return lista;
    }

}
