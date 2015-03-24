package br.com.e3.churrasquinho;

import android.content.Intent;
import android.renderscript.Int2;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class iniciarActivity extends ActionBarActivity {

    Button btnBebidas;
    EditText edtHomem;
    EditText edtMulher;
    EditText edtCrianca;
    EditText txtTotalConvidados;
    ImageView menosH;
    ImageView maisH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        btnBebidas = (Button) findViewById(R.id.btnIniciar);
        edtHomem = (EditText) findViewById(R.id.edtHomem);
        edtMulher = (EditText) findViewById(R.id.edtMulher);
        edtCrianca = (EditText) findViewById(R.id.edtCriancas);
        menosH = (ImageView) findViewById(R.id.menosH);
        maisH = (ImageView) findViewById(R.id.maisH);

/*
        txtTotalConvidados = (EditText) findViewById(R.id.txtTotalConvidados);
        int homem = Integer.parseInt(edtHomem.getText().toString());
        int mulher = Integer.parseInt(edtMulher.getText().toString());
        int crianca = Integer.parseInt(edtCrianca.getText().toString());

        int convidados= 0;
        convidados = homem + mulher + crianca;

        txtTotalConvidados.setText(convidados);


        maisH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int totalHomem =  Integer.parseInt(edtHomem.getText().toString());
               totalHomem ++;
            }
        });

        menosH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalHomem =  Integer.parseInt(edtHomem.getText().toString());
                totalHomem --;
            }
        });
*/
        btnBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(iniciarActivity.this, bebidaActivity.class);
                startActivity(intent);
            }
        });
    }

}
