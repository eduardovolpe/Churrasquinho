package br.com.e3.churrasquinho;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;


public class InserirCarne extends ActionBarActivity {

    EditText edtNome;
    EditText edtValor;
    Button btnCadastrar;
    Button btnVoltar;

    Spinner spnInserir;

    DBAdapterCarne dbAdapterCarne;
    DBAdapterBebida dbAdapterBebida;
    DBAdapterAcompanhamento dbAdapterAcompanhamento;
    DBAdapterOutro dbAdapterOutro;

    Carne carne;
    Bebida bebida;
    Acompanhamento acompanhamento;
    Outro outro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_carne);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtValor = (EditText) findViewById(R.id.edtValor);
        btnCadastrar = (Button) findViewById(R.id.btnSalvar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        spnInserir = (Spinner) findViewById(R.id.spnInserir);

        String txt = "";

        // Adicionar novo item
        final Intent intentTxt = getIntent();
        if(intentTxt != null){
            Bundle params = intentTxt.getExtras();
            if(params != null){
                txt = params.getString("texto");
            }
        }

        final String finalTxt = txt;

        carregarSpinner(finalTxt);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (edtNome.getText().toString() != null) {
                   try {
                       if (edtNome.length() <= 20) {
                           switch (finalTxt) {
                               case "carne":
                                   inserirCarne();
                                   break;
                               case "bebida":
                                   inserirBebida();
                                   break;
                               case "acompanhamento":
                                   inserirAcompanhamento();
                                   break;
                               case "outro":
                                   inserirDespesa();
                                   break;
                               default:
                                   Toast.makeText(InserirCarne.this, "Erro no Cadastro", Toast.LENGTH_SHORT).show();
                           }
                           alertaSair(finalTxt);
                       } else
                           Toast.makeText(InserirCarne.this, "Nome não pode exceder 20 caracteres", Toast.LENGTH_SHORT).show();
                   } catch (Exception e) {
                       Toast.makeText(InserirCarne.this, "Nome ou valor preenchido incorretamente", Toast.LENGTH_SHORT).show();
                       e.printStackTrace();
                       Log.e("Exceção", "Valor preenchido incorretamente");
                   }
               }

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
        dbAdapterCarne.adicionar(edtNome.getText().toString(), Double.parseDouble(edtValor.getText().toString()), spnInserir.getSelectedItem().toString());
        dbAdapterCarne.close();
        Toast.makeText(InserirCarne.this,"Carne inserida", Toast.LENGTH_SHORT).show();
    }
    public void inserirBebida() {
        dbAdapterBebida = new DBAdapterBebida(InserirCarne.this);
        dbAdapterBebida.open();
        dbAdapterBebida.adicionar(edtNome.getText().toString(), Double.parseDouble(edtValor.getText().toString()), spnInserir.getSelectedItem().toString());
        dbAdapterBebida.close();
        Toast.makeText(InserirCarne.this,"Bebida inserida", Toast.LENGTH_SHORT).show();
    }
    public void inserirAcompanhamento() {
        dbAdapterAcompanhamento = new DBAdapterAcompanhamento(InserirCarne.this);
        dbAdapterAcompanhamento.open();
        dbAdapterAcompanhamento.adicionar(edtNome.getText().toString(), Double.parseDouble(edtValor.getText().toString()), spnInserir.getSelectedItem().toString());
        dbAdapterAcompanhamento.close();
        Toast.makeText(InserirCarne.this,"Acompanhamento inserido", Toast.LENGTH_SHORT).show();
    }
    public void inserirDespesa() {
        dbAdapterOutro = new DBAdapterOutro(InserirCarne.this);
        dbAdapterOutro.open();
        dbAdapterOutro.adicionar(edtNome.getText().toString(), Double.parseDouble(edtValor.getText().toString()), spnInserir.getSelectedItem().toString());
        dbAdapterOutro.close();
        Toast.makeText(InserirCarne.this,"Despesa inserida", Toast.LENGTH_SHORT).show();
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Inserir");
        builder.setMessage("Deseja adicionar mais " + finalTxt + " ?");

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                edtNome.setText("");
                edtValor.setText("");
                edtNome.requestFocus();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                voltarTela(finalTxt);
                finish();
            }
        });
        builder.create().show();

    }

    public  void inserirItem(final String finalTxt){
        if (edtNome.length() <= 20) {
            switch (finalTxt) {
                case "carne":
                    inserirCarne();
                    break;
                case "bebida":
                    inserirBebida();
                    break;
                case "acompanhamento":
                    inserirAcompanhamento();
                    break;
                case "outro":
                    inserirDespesa();
                    break;
                default:
                    Toast.makeText(InserirCarne.this, "Erro no Cadastro", Toast.LENGTH_SHORT).show();
            }
            alertaSair(finalTxt);
        }
        else
           Toast.makeText(InserirCarne.this, "Nome não pode exceder 20 caracteres", Toast.LENGTH_SHORT).show();
    }

    public void carregarSpinner(String spinner){
        switch (spinner) {
            case "carne":
                ArrayAdapter<CharSequence> adapterC = ArrayAdapter.createFromResource(InserirCarne.this ,R.array.carnes, android.R.layout.simple_spinner_item);
                adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnInserir.setAdapter(adapterC);
                break;
            case "bebida":
                ArrayAdapter<CharSequence> adapterB = ArrayAdapter.createFromResource(InserirCarne.this ,R.array.bebidas, android.R.layout.simple_spinner_item);
                adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnInserir.setAdapter(adapterB);
                break;
            case "acompanhamento":
                ArrayAdapter<CharSequence> adapterA = ArrayAdapter.createFromResource(InserirCarne.this ,R.array.bebidas, android.R.layout.simple_spinner_item);
                adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnInserir.setAdapter(adapterA);
                break;
            case "outro":
                ArrayAdapter<CharSequence> adapterO = ArrayAdapter.createFromResource(InserirCarne.this ,R.array.bebidas, android.R.layout.simple_spinner_item);
                adapterO.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnInserir.setAdapter(adapterO);
                break;
        }
    }
}

