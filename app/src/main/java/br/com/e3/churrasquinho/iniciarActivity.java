package br.com.e3.churrasquinho;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class iniciarActivity extends ActionBarActivity {

    Button btnCarne;
    EditText edtHomem;
    EditText edtMulher;
    EditText edtCrianca;
    EditText txtTotalConvidados;
    ImageView menosH;
    ImageView maisH;
    ImageView menosM;
    ImageView maisM;
    ImageView menosC;
    ImageView maisC;

    Integer total = 0;
    Integer totalHomem = 0;
    Integer totalMulher = 0;
    Integer totalCrianca = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        btnCarne = (Button) findViewById(R.id.btnCarne);
        edtHomem = (EditText) findViewById(R.id.edtHomem);
        edtMulher = (EditText) findViewById(R.id.edtMulher);
        edtCrianca = (EditText) findViewById(R.id.edtCriancas);
        menosH = (ImageView) findViewById(R.id.menosH);
        maisH = (ImageView) findViewById(R.id.maisH);
        menosM = (ImageView) findViewById(R.id.menosM);
        maisM = (ImageView) findViewById(R.id.maisM);
        menosC = (ImageView) findViewById(R.id.menosC);
        maisC = (ImageView) findViewById(R.id.maisC);

        txtTotalConvidados = (EditText) findViewById(R.id.txtTotalConvidados);
        txtTotalConvidados.setText("Total de Convidados: 0");

        totalMulher = Integer.parseInt(edtMulher.getText().toString());
        totalHomem = Integer.parseInt(edtHomem.getText().toString());
        totalCrianca = Integer.parseInt(edtCrianca.getText().toString());

        maisH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               totalHomem++;
                edtHomem.setText(totalHomem.toString());
                total = totalHomem + totalMulher + totalCrianca;

                atualizaTotal(total);
            }
        });

        menosH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalHomem--;
                edtHomem.setText(totalHomem.toString());

                total = totalHomem + totalMulher + totalCrianca;

                atualizaTotal(total);
            }
        });

        maisM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalMulher++;
                edtMulher.setText(totalMulher.toString());

                total = totalHomem + totalMulher + totalCrianca;
                atualizaTotal(total);
            }
        });

        menosM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                totalMulher--;
                edtMulher.setText(totalMulher.toString());

                total = totalHomem + totalMulher + totalCrianca;
                atualizaTotal(total);

            }
        });

        maisC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               totalCrianca++;
               edtCrianca.setText(totalCrianca.toString());

               total = totalHomem + totalMulher + totalCrianca;
                atualizaTotal(total);
            }
        });

        menosC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalCrianca--;
                edtCrianca.setText(totalCrianca.toString());
                total = totalHomem + totalMulher + totalCrianca;
                atualizaTotal(total);
            }
        });

        // MessageBox aparece caso nenhum convidado seja colocado e usuário queira prosseguir
        final AlertDialog alertaConvidados = new AlertDialog.Builder(this).create();
        alertaConvidados.setTitle("Nenhum Convidado");
        alertaConvidados.setMessage("Churrasquear sozinho não dá, né.\nConvide alguém aí!");
        alertaConvidados.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertaConvidados.setIcon(R.drawable.ic_launcher);

        btnCarne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total == 0) {
                    alertaConvidados.show();
                } else {
                    Intent intent = new Intent(iniciarActivity.this, carneActivity.class);
                    startActivity(intent);
                }
            }

        });
    }

    public void atualizaTotal(int total){
        txtTotalConvidados.setText(String.valueOf("Total de Convidados: " + total));
    }


}

