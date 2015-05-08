package br.com.e3.churrasquinho;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


public class InserirCarne extends ActionBarActivity {

    EditText edtNome;
    EditText edtValor;
    Button btnCadastrar;
    Button btnVoltar;

    DBAdapterCarne dbAdapterCarne;
    DBAdapterBebida dbAdapterBebida;
    DBAdapterAcompanhamento dbAdapterAcompanhamento;
    DBAdapterOutro dbAdapterOutro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_carne);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtValor = (EditText) findViewById(R.id.edtValor);
        btnCadastrar = (Button) findViewById(R.id.btnSalvar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        String txt = "";

        Intent intent = getIntent();
        if(intent != null){
            Bundle params = intent.getExtras();
            if(params != null){
                txt = params.getString("texto");
            }
        }

        final String finalTxt = txt;

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (finalTxt) {
                    case "carne":
                        inserirCarne();
                        Intent intCarne = new Intent(InserirCarne.this, carneActivity.class);
                        startActivity(intCarne);
                        break;
                    case "bebida":
                        inserirBebida();
                        Intent intBebida = new Intent(InserirCarne.this, bebidaActivity.class);
                        startActivity(intBebida);
                        break;
                    case "acompanhamento":
                        inserirAcompanhamento();
                        Intent intAcompanhamento = new Intent(InserirCarne.this, acompanhamentoActivity.class);
                        startActivity(intAcompanhamento);
                        break;
                    case "outro":
                        inserirDespesa();
                        Intent intOutro = new Intent(InserirCarne.this, outroActivity.class);
                        startActivity(intOutro);
                        break;
                    default:
                        Toast.makeText(InserirCarne.this,"Erro no Cadastro", Toast.LENGTH_LONG).show();
                }
                alertaSair(finalTxt);
            }
        });

    btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarTela(finalTxt);
            }
        });
    }

    public void inserirCarne() {
        dbAdapterCarne = new DBAdapterCarne(InserirCarne.this);
        dbAdapterCarne.open();
        dbAdapterCarne.adicionar(edtNome.getText().toString(), Double.parseDouble(edtValor.getText().toString()));
        dbAdapterCarne.close();
        Toast.makeText(InserirCarne.this,"Carne inserida", Toast.LENGTH_LONG).show();
    }
    public void inserirBebida() {
        dbAdapterBebida = new DBAdapterBebida(InserirCarne.this);
        dbAdapterBebida.open();
        dbAdapterBebida.adicionar(edtNome.getText().toString(), Double.parseDouble(edtValor.getText().toString()));
        dbAdapterBebida.close();
        Toast.makeText(InserirCarne.this,"Bebida inserida", Toast.LENGTH_LONG).show();
    }
    public void inserirAcompanhamento() {
        dbAdapterAcompanhamento = new DBAdapterAcompanhamento(InserirCarne.this);
        dbAdapterAcompanhamento.open();
        dbAdapterAcompanhamento.adicionar(edtNome.getText().toString(), Double.parseDouble(edtValor.getText().toString()));
        dbAdapterAcompanhamento.close();
        Toast.makeText(InserirCarne.this,"Acompanhamento inserido", Toast.LENGTH_LONG).show();
    }
    public void inserirDespesa() {
        dbAdapterOutro = new DBAdapterOutro(InserirCarne.this);
        dbAdapterOutro.open();
        dbAdapterOutro.adicionar(edtNome.getText().toString(), Double.parseDouble(edtValor.getText().toString()));
        dbAdapterOutro.close();
        Toast.makeText(InserirCarne.this,"Despesa inserida", Toast.LENGTH_LONG).show();
    }
    public void voltarTela(String finalTxt){

        switch (finalTxt) {
            case "carne":
                Intent intCarne = new Intent(InserirCarne.this, carneActivity.class);
                startActivity(intCarne);
                break;
            case "bebida":
                Intent intBebida = new Intent(InserirCarne.this, bebidaActivity.class);
                startActivity(intBebida);
                break;
            case "acompanhamento":

                Intent intAcompanhamento = new Intent(InserirCarne.this, acompanhamentoActivity.class);
                startActivity(intAcompanhamento);
                break;
            case "outro":
                Intent intOutro = new Intent(InserirCarne.this, outroActivity.class);
                startActivity(intOutro);
                break;
        }

        InserirCarne.this.finish();
    }
    public void alertaSair(final String finalTxt){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Inserir");
        alertDialogBuilder
                .setMessage("Deseja adicionar mais " + finalTxt + " ?")
                .setCancelable(false)
                .setPositiveButton("Sim",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Não",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        voltarTela(finalTxt);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}