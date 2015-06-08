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


public class carneActivity extends ActionBarActivity {

    ListView lstCarne;
    DBAdapterCarne dbAdapterCarne;
    Button btnProsseguir;
    Button btnInserir;
    public static List<String> selecionadas = new ArrayList<>();

    int j = 0;

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

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(carneActivity.this, InserirCarne.class);

                      Bundle param = new Bundle();
                      param.putString("texto", "carne");
                      intent.putExtras(param);

                    carneActivity.this.finish();
                    startActivity(intent);

            }

       });

        selecionadas.clear();

        btnProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer responseText = new StringBuffer();

                responseText.append("Carnes selecionadas: \n");

                List<Carne> carnesList = adapter.getCarnes();
                for(int i=0;i<carnesList.size();i++){
                    Carne carn = carnesList.get(i);
                    if(carn.isMarcado()){
                        responseText.append("\n" + carn.getNomeCarne());
                        responseText.append(" - R$: " + carn.getValorCarne());

                        selecionadas.add(carn.getNomeCarne());
                        j++;
                    }

                }

                if (j > 0) {
                    Toast.makeText(carneActivity.this, responseText, Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(carneActivity.this, bebidaActivity.class);
                startActivity(intent);
            }
        });
    }
}
