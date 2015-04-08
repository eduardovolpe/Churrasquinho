package br.com.e3.churrasquinho;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;


public class carneActivity extends ActionBarActivity {

    ListView lstCarne;
    DBAdapterCarne dbAdapterCarne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carne);

        lstCarne = (ListView) findViewById(R.id.lstCarne);

        dbAdapterCarne = new DBAdapterCarne(this);
        dbAdapterCarne.open();

        List<Carne> carne = dbAdapterCarne.listar();
        AdapterListCarne adapter = new AdapterListCarne(this, carneActivity);

        lstCarne.setAdapter(adapter);

        dbAdapterCarne.close();

    }
}
