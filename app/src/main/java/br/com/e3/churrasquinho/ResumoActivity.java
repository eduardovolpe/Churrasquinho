package br.com.e3.churrasquinho;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ResumoActivity extends ActionBarActivity {

    Button btnProsseguir;
    Button btnLocalizacao;
    TextView txtCarnes, txtBebidas, txtAcompanhamentos, txtDespesas, vlrDespesas;
    ImageView imgLine;

    StringBuffer txtCarne = new StringBuffer();
    StringBuffer txtBebida = new StringBuffer();
    StringBuffer txtAcompanhamento = new StringBuffer();
    StringBuffer txtDespesa = new StringBuffer();

    StringBuffer vlrDespesa = new StringBuffer();


    double ttlDespesa = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        txtCarnes = (TextView) findViewById(R.id.txtCarnes);
        txtBebidas = (TextView) findViewById(R.id.txtBebidas);
        txtAcompanhamentos = (TextView) findViewById(R.id.txtAcompanhamentos);
        txtDespesas = (TextView) findViewById(R.id.txtDespesas);

        vlrDespesas = (TextView) findViewById(R.id.vlrDespesas);

        btnProsseguir = (Button) findViewById(R.id.btnProsResumo);
        btnLocalizacao = (Button) findViewById(R.id.btnLocalizacao);

        /* LINHA DIVISÓRIA LOCALIZAÇÃO */
        imgLine = (ImageView) findViewById(R.id.line);
        imgLine.setVisibility(ViewGroup.GONE);

        txtCarne.delete(0, txtCarne.length());
        txtBebida.delete(0, txtBebida.length());
        txtDespesa.delete(0, txtDespesa.length());
        txtAcompanhamento.delete(0, txtAcompanhamento.length());


        /* CARNES */
       txtCarne.append("Carnes: ");
       for (int i = 0;  i < carneActivity.selecionadas.size(); i++) {
           txtCarne.append(carneActivity.selecionadas.get(i) + ", ");
       }
        txtCarne.append("\n");
        txtCarnes.setText(txtCarne);

        /* BEBIDAS */
        txtBebida.append("Bebidas: ");
        for (int i = 0; i < bebidaActivity.selecionadas.size(); i++){
            txtBebida.append(bebidaActivity.selecionadas.get(i) + ", ");
        }
        txtBebida.append("\n");
        txtBebidas.setText(txtBebida);

        /* ACOMPANHAMENTOS */
        txtAcompanhamento.append("Acompanhamentos: ");
        for (int i = 0; i < acompanhamentoActivity.selecionadas.size(); i++){
            txtAcompanhamento.append(acompanhamentoActivity.selecionadas.get(i) + ", ");
        }
        txtAcompanhamento.append("\n");
        txtAcompanhamentos.setText(txtAcompanhamento);

        /* DESPESAS */
        txtDespesa.append("Despesas: ");
        for (int i = 0; i < outroActivity.selecionadas.size(); i++){
            txtDespesa.append(outroActivity.selecionadas.get(i) + ", ");
            vlrDespesa.append(outroActivity.valores.get(i));

           // ttlDespesa = Double.parseDouble(String.valueOf(vlrDespesa)) + ttlDespesa;
        }
        txtDespesa.append("\n");
        txtDespesas.setText(txtDespesa);
        vlrDespesas.setText("R$: " + vlrDespesa);


         btnProsseguir.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(ResumoActivity.this, FinalizarActivity.class);
                 startActivity(intent);
             }
         });

        btnLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumoActivity.this, localActivity.class);
                startActivity(intent);
            }
        });
    }
}