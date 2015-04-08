package br.com.e3.churrasquinho;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;


public class outroActivity extends ActionBarActivity {

    ListView lstOutro;
    DBAdapterOutro dbAdapterOutro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outro);
    }

    lstOutro = (ListView) findViewById(R.id.lstOutro);

    dbAdapterOutro = new DBAdapterOutro(this);
    dbAdapterOutro.open();

    List<Outro> outro = dbAdapterOutro.listar();
    AdapterListOutro adapter = new AdapterListOutro this, Outro);

    lstOutro.setAdapter(adapter);

    dbAdapterOutro.close();

}
