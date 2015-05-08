package br.com.e3.churrasquinho;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class acompanhamentoActivity extends ActionBarActivity {

    ListView lstAcompanhamento;
    DBAdapterAcompanhamento dbAdapterAcompanhamento;
    Button btnInserir;
    Button btnProsseguir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanhamento);

        lstAcompanhamento = (ListView) findViewById(R.id.lstAcompanhamentos);
        btnInserir = (Button) findViewById(R.id.btnCadAcompanhamento);
        btnProsseguir = (Button) findViewById(R.id.btnProsAcompanhamentos);

        dbAdapterAcompanhamento = new DBAdapterAcompanhamento(this);
        dbAdapterAcompanhamento.open();

        final List<Acompanhamento> acompanhamentos = dbAdapterAcompanhamento.listar();
        AdapterListAcompanhamento adapter = new AdapterListAcompanhamento(this, acompanhamentos);

        lstAcompanhamento.setAdapter(adapter);

        dbAdapterAcompanhamento.close();

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acompanhamentoActivity.this, InserirCarne.class);

                Bundle param = new Bundle();
                param.putString("texto", "acompanhamento");
                intent.putExtras(param);

                startActivity(intent);
            }
        });

        btnProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acompanhamentoActivity.this, outroActivity.class);
                startActivity(intent);
            }
        });

    }
}
