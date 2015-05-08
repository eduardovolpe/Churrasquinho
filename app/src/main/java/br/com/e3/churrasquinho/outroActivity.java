package br.com.e3.churrasquinho;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class outroActivity extends ActionBarActivity {

    ListView lstOutro;
    DBAdapterOutro dbAdapterOutro;
    Button btnInserir;
    Button btnProsseguir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outro);

        lstOutro = (ListView) findViewById(R.id.lstOutro);
        btnInserir = (Button) findViewById(R.id.btnCadOutro);
        btnProsseguir = (Button) findViewById(R.id.btnProsOutro);

        dbAdapterOutro = new DBAdapterOutro(this);
        dbAdapterOutro.open();

        List<Outro> outro = dbAdapterOutro.listar();
        AdapterListOutro adapter = new AdapterListOutro(this, outro);

        lstOutro.setAdapter(adapter);
        dbAdapterOutro.close();

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(outroActivity.this, InserirCarne.class);
                Bundle param = new Bundle();
                param.putString("texto", "outro");
                intent.putExtras(param);
                startActivity(intent);
            }
        });
        btnProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(outroActivity.this, ResumoActivity.class);
                startActivity(intent);
            }
        });
    }
}
