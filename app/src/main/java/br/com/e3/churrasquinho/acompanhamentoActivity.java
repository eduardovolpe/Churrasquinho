package br.com.e3.churrasquinho;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;


public class acompanhamentoActivity extends ActionBarActivity {

    ListView lstAcompanhamento;
    DBAdapterAcompanhamento dbAdapterAcompanhamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanhamento);

        lstAcompanhamento = (ListView) findViewById(R.id.lstAcompanhamentos);

        dbAdapterAcompanhamento = new DBAdapterAcompanhamento(this);
        dbAdapterAcompanhamento.open();

        List<Acompanhamento> acompanhamento = dbAdapterAcompanhamento.listar();
        AdapterListAcompanhamento adapter = new AdapterListAcompanhamento(this, Acompanhamento);

        lstAcompanhamento.setAdapter(adapter);

        dbAdapterAcompanhamento.close();

    }
}
