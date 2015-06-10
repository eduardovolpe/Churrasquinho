package br.com.e3.churrasquinho;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class ResumoActivity extends ActionBarActivity {

    Button btnProsseguir;
    Button btnLocalizacao;
    TextView txtCarnes, txtBebidas, txtAcompanhamentos, txtDespesas, vlrDespesas, txtData, txtEndereco, txtNumero,
            txtBairro, txtCidade, txtUf, txtCep, txtReferencia, txtLocal, vlrAcompanhamentos, vlrCarnes, vlrBebidas;
    ImageView imgLine, imgData;

    StringBuffer txtCarne = new StringBuffer();
    StringBuffer txtBebida = new StringBuffer();
    StringBuffer txtAcompanhamento = new StringBuffer();
    StringBuffer txtDespesa = new StringBuffer();

    double ttlCarne = 0.0;
    double ttlBebida = 0.0;
    double ttlAcompanhamento = 0.0;
    double ttlDespesa = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        txtCarnes = (TextView) findViewById(R.id.txtCarnes);
        txtBebidas = (TextView) findViewById(R.id.txtBebidas);
        txtAcompanhamentos = (TextView) findViewById(R.id.txtAcompanhamentos);
        txtDespesas = (TextView) findViewById(R.id.txtDespesas);
        txtData = (TextView) findViewById(R.id.txtData);

        txtEndereco = (TextView) findViewById(R.id.txtEndereco);
        txtNumero = (TextView) findViewById(R.id.txtNumero);
        txtBairro = (TextView) findViewById(R.id.txtBairro);
        txtCidade = (TextView) findViewById(R.id.txtCidade);
        txtUf = (TextView) findViewById(R.id.txtUf);
        txtReferencia = (TextView) findViewById(R.id.txtReferencia);
        txtLocal = (TextView) findViewById(R.id.txtLocal);
        txtCep = (TextView) findViewById(R.id.txtCep);

        vlrCarnes = (TextView) findViewById(R.id.vlrCarnes);
        vlrBebidas = (TextView) findViewById(R.id.vlrBebidas);
        vlrAcompanhamentos = (TextView) findViewById(R.id.vlrAcompanhamentos);
        vlrDespesas = (TextView) findViewById(R.id.vlrDespesas);

        btnProsseguir = (Button) findViewById(R.id.btnProsResumo);
        btnLocalizacao = (Button) findViewById(R.id.btnLocalizacao);

        txtLocal.setVisibility(ViewGroup.GONE);

        /* LINHA DIVISÓRIA LOCALIZAÇÃO */
        imgLine = (ImageView) findViewById(R.id.line);
        imgLine.setVisibility(ViewGroup.GONE);

        /* CALENDÁRIO */
        imgData = (ImageView) findViewById(R.id.imgData);
        imgData.setVisibility(ViewGroup.GONE);

        txtCarne.delete(0, txtCarne.length());
        txtBebida.delete(0, txtBebida.length());
        txtDespesa.delete(0, txtDespesa.length());
        txtAcompanhamento.delete(0, txtAcompanhamento.length());

        /* LIMPA AS LABELS DO ENDEREÇO */
        txtCep.setText("");
        txtEndereco.setText("");
        txtNumero.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtUf.setText("");
        txtReferencia.setText("");

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

            double despesa = 0.0;
            despesa = Double.parseDouble(String.valueOf(acompanhamentoActivity.valores.get(i)));

            ttlAcompanhamento = despesa + ttlAcompanhamento;
        }
        txtAcompanhamento.append("\n");
        txtAcompanhamentos.setText(txtAcompanhamento);
        vlrAcompanhamentos.setText("R$: " + ttlAcompanhamento);

        /* DESPESAS */
        txtDespesa.append("Despesas: ");
        for (int i = 0; i < outroActivity.selecionadas.size(); i++){

            txtDespesa.append(outroActivity.selecionadas.get(i) + ", ");

            double despesa = 0.0;
            despesa = Double.parseDouble(String.valueOf(outroActivity.valores.get(i)));

            ttlDespesa = despesa + ttlDespesa;
        }
        txtDespesa.append("\n");
        txtDespesas.setText(txtDespesa);
        vlrDespesas.setText("R$: " + ttlDespesa);



        /* INSERE ENDEREÇO */
        Intent end = getIntent();
        if(end != null){
            Bundle params = end.getExtras();
            if(params != null){
                int txt = params.getInt("end");
                if(txt == 1) {

                    txtLocal.setVisibility(ViewGroup.VISIBLE);
                    imgLine.setVisibility(ViewGroup.VISIBLE);

                    txtEndereco.setText(localActivity.endereco[0].toString());
                    txtNumero.setText(localActivity.endereco[1].toString());
                    txtBairro.setText(localActivity.endereco[2].toString());
                    txtCidade.setText(localActivity.endereco[3].toString());
                    txtUf.setText(localActivity.endereco[4].toString());
                    txtCep.setText(localActivity.endereco[5].toString());
                    txtReferencia.setText(localActivity.endereco[6].toString());

                }
            }
        }

        /* INSERE A DATA */
        imgData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendario();

                    }
            });

        btnProsseguir.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(ResumoActivity.this, FinalizarActivity.class);

                 calendario();

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

    public void calendario(){
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(ResumoActivity.this);
        View promptView = layoutInflater.inflate(R.layout.prompt_data, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ResumoActivity.this);

        // set prompts.xml to be the layout file of the alertdialog builder
        alertDialogBuilder.setView(promptView);
        final EditText input = (EditText) promptView.findViewById(R.id.edtData);

        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Inserir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // get user input and set it to result
                        txtData.setText(input.getText());
                    }
                })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,	int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }
}