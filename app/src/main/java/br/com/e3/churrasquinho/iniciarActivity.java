package br.com.e3.churrasquinho;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class iniciarActivity extends ActionBarActivity {

    Button btnBebidas;
    EditText edtHomem;
    EditText edtMulher;
    EditText edtCrianca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        btnBebidas = (Button) findViewById(R.id.btnIniciar);
        edtHomem = (EditText) findViewById(R.id.edtHomem);
        edtMulher = (EditText) findViewById(R.id.edtMulher);
        edtCrianca = (EditText) findViewById(R.id.edtCriancas);


       TextView txtTotalConvidados = (TextView) findViewById(R.id.txtTotalConvidados);
        int homem = Integer.parseInt(edtHomem.toString());
        int mulher = Integer.parseInt(edtMulher.toString());
        int crianca = Integer.parseInt(edtCrianca.toString());

        int convidados = 0;
        convidados = homem + mulher + crianca;

        txtTotalConvidados.setText("Total de Convidados: " + convidados);


        btnBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(iniciarActivity.this, bebidaActivity.class);
                startActivity(intent);
            }
        });
    }

}
