package br.com.e3.churrasquinho;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
                        Toast.makeText(InserirCarne.this, "Nome ou valor preenchido incorretamente", Toast.LENGTH_SHORT).show();
                    }

                }
        });

    btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
}

