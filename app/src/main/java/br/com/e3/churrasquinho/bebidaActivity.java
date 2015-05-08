package br.com.e3.churrasquinho;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class bebidaActivity extends ActionBarActivity {

    Button btnProsseguir;
    ListView lstBebida;
    DBAdapterBebida dbAdapterBebida;
    Button btnInserir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebida);

        lstBebida = (ListView) findViewById(R.id.lstBebidas);
        btnProsseguir = (Button) findViewById(R.id.btnProsseguir);
        btnInserir = (Button) findViewById(R.id.btnCadBebida);

        dbAdapterBebida = new DBAdapterBebida(this);
        dbAdapterBebida.open();

        List<Bebida> bebidas = dbAdapterBebida.listar();
        AdapterListBebida adapter = new AdapterListBebida(this, bebidas );
        lstBebida.setAdapter(adapter);
       dbAdapterBebida.close();

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bebidaActivity.this, InserirCarne.class);

                Bundle param = new Bundle();
                param.putString("texto", "bebida");
                intent.putExtras(param);

                startActivity(intent);
            }

        });

        btnProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bebidaActivity.this, acompanhamentoActivity.class);
                startActivity(intent);
            }
        });

    }

}