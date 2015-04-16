package br.com.e3.churrasquinho;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class carneActivity extends ActionBarActivity {

    ListView lstCarne;
    DBAdapterCarne dbAdapterCarne;
    Button btnProsseguir;
    Button btnInserir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carne);

        lstCarne = (ListView) findViewById(R.id.lstCarne);
        btnProsseguir = (Button) findViewById(R.id.btnProsCarne);
        btnInserir = (Button) findViewById(R.id.btnCadCarne);

        dbAdapterCarne = new DBAdapterCarne(this);
        dbAdapterCarne.open();

        List<Carne> carne = dbAdapterCarne.listar();
        AdapterListCarne adapter = new AdapterListCarne(this, carne);

        lstCarne.setAdapter(adapter);
        dbAdapterCarne.close();


        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(carneActivity.this, InserirCarne.class);
                    startActivity(intent);
            }

       });

        btnProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(carneActivity.this, bebidaActivity.class);
                startActivity(intent);
            }
        });
    }
}
