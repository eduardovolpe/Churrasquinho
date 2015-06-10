package br.com.e3.churrasquinho;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class bebidaActivity extends ActionBarActivity {

    Button btnProsseguir;
    ListView lstBebida;
    DBAdapterBebida dbAdapterBebida;
    Button btnInserir;

    int j = 0;

    public static List<String> selecionadas = new ArrayList<>();
    public static List<Double> valores = new ArrayList<>();

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
        final AdapterListBebida adapter = new AdapterListBebida(this, bebidas );
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

        selecionadas.clear();
        btnProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("Bebidas selecionadas: \n");

                List<Bebida> bebidaList = adapter.getBebidas();
                for(int i=0;i<bebidaList.size();i++){
                    Bebida bebidis = bebidaList.get(i);
                    if(bebidis.isMarcado()){
                        responseText.append("\n" + bebidis.getNomeBebida());
                        responseText.append(" - R$: " + bebidis.getValorBebida());

                        selecionadas.add(bebidis.getNomeBebida());
                        valores.add(bebidis.getValorBebida());
                        j++;
                    }
                }

                if (j > 0) {
                    Toast.makeText(bebidaActivity.this, responseText, Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(bebidaActivity.this, acompanhamentoActivity.class);
                startActivity(intent);
            }
        });

    }

}