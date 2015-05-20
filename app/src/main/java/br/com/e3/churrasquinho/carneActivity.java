package br.com.e3.churrasquinho;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class carneActivity extends ActionBarActivity {

    ListView lstCarne;
    DBAdapterCarne dbAdapterCarne;
    Button btnProsseguir;
    Button btnInserir;

    String txt = "";

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
        final AdapterListCarne adapter = new AdapterListCarne(this, carne);

        lstCarne.setAdapter(adapter);
        dbAdapterCarne.close();

        lstCarne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(carneActivity.this, "LISTA" + id, Toast.LENGTH_SHORT).show();
/*
                Carne carne = adapter.getItem(position);
                Intent intent = new Intent(view.getContext(), editarActivity.class);

                Bundle param = new Bundle();
                Bundle idItem = new Bundle();

                param.putString("texto", "carne");
                idItem.putLong("id", carne.getIdCarne());

                intent.putExtras(param);
                intent.putExtras(idItem);

                startActivity(intent);
*/            }
        });

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(carneActivity.this, InserirCarne.class);

                      Bundle param = new Bundle();
                      param.putString("texto", "carne");
                      intent.putExtras(param);

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
