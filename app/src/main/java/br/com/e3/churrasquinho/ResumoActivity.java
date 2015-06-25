package br.com.e3.churrasquinho;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


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
    int j,h;
    double homens, mulheres, criancas;
    String vHomem, vMulher, vCrianca;

    public static Integer[] quantidades = new Integer[4];
    public static String[] data = new String[4];
    static final List<String> itens = new ArrayList<>();
    public static ProgressDialog pResumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        outroActivity.pOutro.dismiss();
        itens.clear();
        final DecimalFormat dc = new DecimalFormat("0.00");

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

        /* RECUPERANDO A QUANTIDADE DE CONVIDADOS */
        homens = iniciarActivity.convidados[0];
        mulheres = iniciarActivity.convidados[1];
        criancas = iniciarActivity.convidados[2];

        /* LINHA DIVISÓRIA LOCALIZAÇÃO */
        imgLine = (ImageView) findViewById(R.id.line);
        imgLine.setVisibility(ViewGroup.GONE);

        /* CALENDÁRIO */
        imgData = (ImageView) findViewById(R.id.imgData);

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

        itens.add(" -- Nome -- ");
        itens.add("-- Valor Unitário --");

        /* CARNES */
        txtCarne.append("Carnes: ");
        for (int i = 0; i < carneActivity.selecionadas.size(); i++) {
            txtCarne.append(carneActivity.selecionadas.get(i) + ", ");

            double carnes;
            carnes = Double.parseDouble(String.valueOf(carneActivity.valores.get(i)));
            ttlCarne = carnes + ttlCarne;

            quantidades[0] = i;
            itens.add(carneActivity.selecionadas.get(i));
            itens.add("R$" + String.valueOf(dc.format(carnes)));
            j++;
        }
        txtCarne.append("\n");
        txtCarnes.setText(txtCarne);
        final double totalCarne = (((ttlCarne*homens)*0.6)+((ttlCarne*mulheres)*0.5)+((ttlCarne*criancas)*0.4))/j;
        vlrCarnes.setText("Total R$: " + dc.format(totalCarne));

        /* BEBIDAS */
        txtBebida.append("Bebidas: ");
        for (int i = 0; i < bebidaActivity.selecionadas.size(); i++) {
            txtBebida.append(bebidaActivity.selecionadas.get(i) + ", ");

            double bebida;
            bebida = Double.parseDouble(String.valueOf(bebidaActivity.valores.get(i)));

            ttlBebida = bebida + ttlBebida;

            quantidades[1] = i;
            itens.add(bebidaActivity.selecionadas.get(i) + " " + bebidaActivity.categoria.get(i));
            itens.add("R$" + String.valueOf(dc.format(bebida)));
            h++;
        }
        txtBebida.append("\n");
        txtBebidas.setText(txtBebida);
        final double totalBebida = (((ttlBebida*homens)*1)+((ttlBebida*mulheres)*0.6)+((ttlBebida*criancas)*0.4))/h;

        vlrBebidas.setText("Total R$: " + dc.format(totalBebida));

        /* ACOMPANHAMENTOS */
        txtAcompanhamento.append("Acompanhamentos: ");
        for (int i = 0; i < acompanhamentoActivity.selecionadas.size(); i++) {
            txtAcompanhamento.append(acompanhamentoActivity.selecionadas.get(i) + ", ");

            double acompanhamento;
            acompanhamento = Double.parseDouble(String.valueOf(acompanhamentoActivity.valores.get(i)));

            ttlAcompanhamento = acompanhamento + ttlAcompanhamento;

            quantidades[2] = i;
            itens.add(acompanhamentoActivity.selecionadas.get(i));
            itens.add("R$" + String.valueOf(dc.format(acompanhamento)));
        }
        txtAcompanhamento.append("\n");
        txtAcompanhamentos.setText(txtAcompanhamento);
        final double totalAcompanhamento = (((ttlAcompanhamento*homens)*0.8)+((ttlAcompanhamento*mulheres)*0.6)+((ttlAcompanhamento*criancas)*0.4));
        vlrAcompanhamentos.setText("Total R$: " + dc.format(totalAcompanhamento));

        /* DESPESAS */
        txtDespesa.append("Despesas: ");
        for (int i = 0; i < outroActivity.selecionadas.size(); i++) {

            txtDespesa.append(outroActivity.selecionadas.get(i) + ", ");

            double despesa;
            despesa = Double.parseDouble(String.valueOf(outroActivity.valores.get(i)));

            ttlDespesa = despesa + ttlDespesa;

            quantidades[3] = i;

            itens.add(outroActivity.selecionadas.get(i));
            itens.add("R$" + String.valueOf(dc.format(despesa)));

        }
        txtDespesa.append("\n");
        txtDespesas.setText(txtDespesa);
        vlrDespesas.setText("Total R$: " + dc.format(ttlDespesa));


        /* INSERE ENDEREÇO */
        Intent end = getIntent();
        if (end != null) {
            Bundle params = end.getExtras();
            if (params != null) {
                int txt = params.getInt("end");
                if (txt == 1) {

                    txtLocal.setVisibility(ViewGroup.VISIBLE);
                    imgLine.setVisibility(ViewGroup.VISIBLE);

                    txtEndereco.setText(localActivity.endereco[0]);
                    txtNumero.setText(localActivity.endereco[1]);
                    txtBairro.setText(localActivity.endereco[2]);
                    txtCidade.setText(localActivity.endereco[3]);
                    txtUf.setText(localActivity.endereco[4]);
                    txtCep.setText(localActivity.endereco[5]);
                    txtReferencia.setText(localActivity.endereco[6]);

                }
            }
        }

        imgData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario();
            }
        });

        btnProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pResumo = ProgressDialog.show(ResumoActivity.this, "Processando", "Aguarde...", true, false);
                Intent intent = new Intent(ResumoActivity.this, FinalizarActivity.class);
                startActivity(intent);

                itens.add("-- Totais --");
                itens.add("");
                itens.add("Carne:");
                itens.add(String.valueOf("R$" + dc.format(totalCarne)));
                itens.add("Bebida:");
                itens.add(String.valueOf("R$" + dc.format(totalBebida)));
                itens.add("Acompanhamentos:");
                itens.add(String.valueOf("R$" + dc.format(totalAcompanhamento)));
                itens.add("Despesas:");
                itens.add(String.valueOf("R$" + dc.format(ttlDespesa)));
                itens.add("Total:");
                itens.add("R$" + String.valueOf(dc.format(ttlDespesa + totalAcompanhamento + totalBebida + totalCarne)));

                Double total = ttlDespesa + totalAcompanhamento + totalBebida + totalCarne;

                if (homens != 0) {
                    vHomem = "R$" + dc.format((total * 0.47) / homens);
                }
                if (homens == 0) {
                    vHomem = "Nenhum selecionado.";
                }
                if (mulheres != 0) {
                    vMulher = "R$" + dc.format(((total - total * 0.47) * 0.6) / mulheres);
                }
                if (mulheres == 0){
                    vMulher = "Nenhuma selecionada.";
                }
                if (criancas != 0) {
                    vCrianca = "R$" + dc.format((total - (((total - total * 0.47) * 0.6) + (total * 0.47))) / criancas);
                }
                if (criancas == 0){
                    vCrianca = "Nenhuma selecionada.";
                }

                itens.add("-- Rateio --");
                itens.add("");
                itens.add("Cada Homem Pagará:");
                itens.add(vHomem);
                itens.add("Cada Mulher Pagará:");
                itens.add(vMulher);
                itens.add("Cada Criança Pagará:");
                itens.add(vCrianca);


                itens.add("-- Local --");
                itens.add("");
                itens.add("Endereço: " + localActivity.endereco[0]);
                itens.add("nº " + localActivity.endereco[1]);
                itens.add("Bairro: " + localActivity.endereco[2]);
                itens.add("CEP: " + localActivity.endereco[5]);
                itens.add("Cidade: " + localActivity.endereco[3]);
                itens.add("UF: " + localActivity.endereco[4]);
                itens.add("Referência: " + localActivity.endereco[6]);

                itens.add("Data: " + data[3].toString());

                data[0] = vHomem;
                data[1] = vMulher;
                data[2] = vCrianca;

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

    public void calendario() {
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
                        imgData.setVisibility(ViewGroup.VISIBLE);
                        data[3] = input.getText().toString();
                    }
                })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }

}