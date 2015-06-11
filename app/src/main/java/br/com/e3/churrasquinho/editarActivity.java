package br.com.e3.churrasquinho;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class editarActivity extends ActionBarActivity {

    EditText edtNome;
    EditText edtValor;
    Button btnCadastrar;
    Button btnVoltar;
    Button btnExcluir;

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
        setContentView(R.layout.activity_editar);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtValor = (EditText) findViewById(R.id.edtValor);
        btnCadastrar = (Button) findViewById(R.id.btnSalvar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        String txt = "";
        long id = 0;

        // Adicionar novo item
        final Intent intentTxt = getIntent();
        if(intentTxt != null){
            Bundle params = intentTxt.getExtras();
            if(params != null){
                txt = params.getString("texto");
            }
        }

        // Se for editar
        final Intent intentId = getIntent();
        if (intentId != null) {
            Bundle paramId = intentId.getExtras();
            if (paramId != null) {
                id = paramId.getLong("id");
                switch (txt) {
                    case "carne" :
                        dbAdapterCarne = new DBAdapterCarne(this);
                        dbAdapterCarne.open();
                        carne = dbAdapterCarne.getCarne(id);
                        edtNome.setText(carne.getNomeCarne());
                        edtValor.setText((int) carne.getValorCarne());
                        dbAdapterCarne.close();
                        break;
                    case "bebida" :
                        dbAdapterBebida = new DBAdapterBebida(this);
                        dbAdapterBebida.open();
                        bebida = dbAdapterBebida.getBebida(id);
                        edtNome.setText(bebida.getNomeBebida());
                        edtValor.setText((int) bebida.getValorBebida());
                        dbAdapterBebida.close();
                        break;
                }
            }
        }

        Toast.makeText(editarActivity.this, "Texto: " + txt + " - ID: " + id, Toast.LENGTH_SHORT).show();

    }
/*
    public void editarCarne(){
        dbAdapterCarne = new DBAdapterCarne(this);
        dbAdapterCarne.open();
        carne.setNomeCarne(edtNome.getText().toString());
        carne.setValorCarne(Double.valueOf(edtValor.getText().toString()));
        dbAdapterCarne.editarCarne(carne);
    }
*/


}
