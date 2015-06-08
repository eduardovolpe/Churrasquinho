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


public class acompanhamentoActivity extends ActionBarActivity {

    ListView lstAcompanhamento;
    DBAdapterAcompanhamento dbAdapterAcompanhamento;
    Button btnInserir;
    Button btnProsseguir;

    int j = 0;

    public static List<String> selecionadas = new ArrayList<>();

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
        final AdapterListAcompanhamento adapter = new AdapterListAcompanhamento(this, acompanhamentos);

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
        selecionadas.clear();
        btnProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("Acompanhamentos selecionados: \n");

                List<Acompanhamento> acompanhamentosList = adapter.getAcompanhamentos();

                for(int i=0;i<acompanhamentosList.size();i++){
                    Acompanhamento acomp = acompanhamentosList.get(i);
                    if(acomp.isMarcado()){
                        responseText.append("\n" + acomp.getNomeAcompanhamento());
                        responseText.append(" - R$: " + acomp.getValorAcompanhamento());

                        selecionadas.add(acomp.getNomeAcompanhamento());
                        j++;
                    }

                }

                if (j > 0) {
                    Toast.makeText(acompanhamentoActivity.this, responseText, Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(acompanhamentoActivity.this, outroActivity.class);
                startActivity(intent);
            }
        });

    }
}
