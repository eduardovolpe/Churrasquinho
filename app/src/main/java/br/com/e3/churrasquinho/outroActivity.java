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

public class outroActivity extends ActionBarActivity {

    ListView lstOutro;
    DBAdapterOutro dbAdapterOutro;
    Button btnInserir;
    Button btnProsseguir;
    int j = 0;

    public static List<String> selecionadas = new ArrayList<>();
    public static List<String> categoria = new ArrayList<>();
    public static List<Double> valores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outro);

        lstOutro = (ListView) findViewById(R.id.lstOutro);
        btnInserir = (Button) findViewById(R.id.btnCadOutro);
        btnProsseguir = (Button) findViewById(R.id.btnProsOutro);

        dbAdapterOutro = new DBAdapterOutro(this);
        dbAdapterOutro.open();

        final List<Outro> outro = dbAdapterOutro.listar();
        final AdapterListOutro adapter = new AdapterListOutro(this, outro);

        lstOutro.setAdapter(adapter);
        dbAdapterOutro.close();

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(outroActivity.this, InserirCarne.class);
                Bundle param = new Bundle();
                param.putString("texto", "despesa");
                intent.putExtras(param);
                startActivity(intent);
            }
        });

        btnProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("Despesas selecionadas: \n");
                selecionadas.clear();
                categoria.clear();
                valores.clear();
                List<Outro> outroList = adapter.getOutros();
                for(int i=0;i<outroList.size();i++){
                    Outro outro = outroList.get(i);
                    if(outro.isMarcado()){
                        responseText.append("\n" + outro.getNomeOutro());
                        responseText.append(" - R$: " + outro.getValorOutro());

                        selecionadas.add(outro.getNomeOutro());
                        categoria.add(outro.getTipoOutro());
                        valores.add(outro.getValorOutro());
                        j++;
                    }
                }
                if (j > 0 ) {
                    Toast.makeText(outroActivity.this, responseText, Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(outroActivity.this, ResumoActivity.class);
                startActivity(intent);
            }
        });
    }
}
