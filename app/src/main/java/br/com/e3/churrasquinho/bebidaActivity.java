package br.com.e3.churrasquinho;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;


public class bebidaActivity extends ActionBarActivity {

    ListView lstBebida;
    DBAdapterBebida dbAdapterBebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebida);

        lstBebida = (ListView) findViewById(R.id.lstBebidas);

        dbAdapterBebida = new DBAdapterBebida(this);
        dbAdapterBebida.open();

        List<Bebida> bebidas = dbAdapterBebida.listar();
        AdapterListBebida adapter = new AdapterListCarne(this, bebidas );

        lstBebida.setAdapter(adapter);

        dbAdapterBebida.close();
    }

}