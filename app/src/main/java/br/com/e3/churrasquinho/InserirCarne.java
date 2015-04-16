package br.com.e3.churrasquinho;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class InserirCarne extends ActionBarActivity {

    EditText edtNomeCarne;
    EditText edtValorCarne;
    Button btnCadCarne;
    Button btnVoltar;

    DBAdapterCarne dbAdapterCarne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_carne);

        edtNomeCarne = (EditText) findViewById(R.id.edtNomeCarne);
        edtValorCarne = (EditText) findViewById(R.id.edtValorCarne);
        btnCadCarne = (Button) findViewById(R.id.btnSalvarCarne);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnCadCarne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapterCarne = new DBAdapterCarne(InserirCarne.this);

                dbAdapterCarne.open();
                dbAdapterCarne.adicionar(edtNomeCarne.getText().toString(), Double.parseDouble(edtValorCarne.getText().toString()));
                dbAdapterCarne.close();

                Intent intent = new Intent(InserirCarne.this, carneActivity.class);
                startActivity(intent);

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InserirCarne.this, carneActivity.class);
                startActivity(intent);

            }
        });
    }
}