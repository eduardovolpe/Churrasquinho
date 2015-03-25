package br.com.e3.churrasquinho;

import android.content.Intent;
//import android.renderscript.Int2;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
//import android.widget.TextView;


public class iniciarActivity extends ActionBarActivity {

    Button btnBebidas;
    EditText edtHomem;
    EditText edtMulher;
    EditText edtCrianca;
    //EditText txtTotalConvidados;
    ImageView menosH;
    ImageView maisH;
    ImageView menosM;
    ImageView maisM;
    ImageView menosC;
    ImageView maisC;


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
        menosM = (ImageView) findViewById(R.id.menosM);
        maisM = (ImageView) findViewById(R.id.maisM);
        menosC = (ImageView) findViewById(R.id.menosC);
        maisC = (ImageView) findViewById(R.id.maisC);

// NÃO MEXA NO MEU LINDO E MARAVILHOSO CÓDIGO..Att O PROGRAMADOR

        maisH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Byte totalHomem =  Byte.parseByte(edtHomem.getText().toString());
                totalHomem ++;
                edtHomem.setText(totalHomem.toString());

            }
        });

        menosH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Byte totalHomem =  Byte.parseByte(edtHomem.getText().toString());
                totalHomem --;
                edtHomem.setText(totalHomem.toString());

            }
        });

        maisM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double totalMulher =  Double.parseDouble(edtMulher.getText().toString());
                totalMulher ++;
                edtMulher.setText(totalMulher.toString());

            }
        });

        menosM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double totalMulher =  Double.parseDouble(edtMulher.getText().toString());
                totalMulher --;
                edtMulher.setText(totalMulher.toString());

            }
        });

        maisC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double totalCrianca =  Double.parseDouble(edtCrianca.getText().toString());
                totalCrianca ++;
                edtCrianca.setText(totalCrianca.toString());

            }
        });

        menosC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double totalCrianca =  Double.parseDouble(edtCrianca.getText().toString());
                totalCrianca --;
                edtCrianca.setText(totalCrianca.toString());

            }
        });



/*
        txtTotalConvidados = (EditText) findViewById(R.id.txtTotalConvidados);
        int convidados = 0;
        convidados = homem + mulher + crianca;
        txtTotalConvidados.setText("Total de Convidados: " + convidados);

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
