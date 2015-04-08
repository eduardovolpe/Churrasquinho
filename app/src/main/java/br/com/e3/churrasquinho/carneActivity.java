package br.com.e3.churrasquinho;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class carneActivity extends ActionBarActivity {

    ListView lstCarne;
    DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carne);

        lstCarne = (ListView) findViewById(R.id.lstCarne);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        List<Carne> carne = dbAdapter.listar();
        AdapterListCarne adapter = new AdapterListCarne(this, carneActivity);

        lstCarne.setAdapter(adapter);

        dbAdapter.close();

    }
}
