package br.com.e3.churrasquinho;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
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

        infoConvidados();

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

    public void infoConvidados(){
        Intent intent = getIntent();
        if(intent != null){
            Bundle params = intent.getExtras();
            if(params != null){
                txt = params.getString("informacao");
            }
        }

        Toast.makeText(carneActivity.this, "Quantidade de Convidados: " + txt, Toast.LENGTH_LONG).show();
    }
}
