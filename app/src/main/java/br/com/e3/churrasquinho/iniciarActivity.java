package br.com.e3.churrasquinho;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
//import android.renderscript.Int2;
import android.preference.DialogPreference;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
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
    EditText txtTotalConvidados;
    ImageView menosH;
    ImageView maisH;
    ImageView menosM;
    ImageView maisM;
    ImageView menosC;
    ImageView maisC;

    Integer total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        btnBebidas = (Button) findViewById(R.id.btnEditar);
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

        edtCrianca.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        aumentar(Integer.parseInt(edtCrianca.getText().toString()));
                    }
        });

        edtCrianca.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                aumentar(Integer.parseInt(edtCrianca.getText().toString()));
                return false;
            }
        });

// NÃO MEXA NO MEU LINDO E MARAVILHOSO CÓDIGO...Att O PROGRAMADOR

        maisH.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Integer totalHomem =  Integer.parseInt(edtHomem.getText().toString());
                totalHomem ++;
                aumentar();
                edtHomem.setText(totalHomem.toString());
                return true;
            }
        });

        menosH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Short totalHomem =  Short.parseShort(edtHomem.getText().toString());

                if (totalHomem > 0) {
                    totalHomem --;
                    diminuir();
                } else {
                    totalHomem = 0;
                }

                edtHomem.setText(totalHomem.toString());

            }
        });

        maisM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Short totalMulher =  Short.parseShort(edtMulher.getText().toString());
                totalMulher ++;
                aumentar();
                edtMulher.setText(totalMulher.toString());

            }
        });

        menosM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Short totalMulher =  Short.parseShort(edtMulher.getText().toString());
                if (totalMulher > 0) {
                    totalMulher --;
                    diminuir();
                } else {
                    totalMulher = 0;
                }

                edtMulher.setText(totalMulher.toString());

            }
        });

        maisC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Short totalCrianca =  Short.parseShort(edtCrianca.getText().toString());
                totalCrianca ++;
                aumentar();
                edtCrianca.setText(totalCrianca.toString());

            }
        });

        menosC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Short totalCrianca =  Short.parseShort(edtCrianca.getText().toString());
                if (totalCrianca > 0) {
                    totalCrianca--;
                    diminuir();
                } else {
                    totalCrianca = 0;
                }
                edtCrianca.setText(totalCrianca.toString());

            }
        });



/* Volpe
        txtTotalConvidados = (EditText) findViewById(R.id.txtTotalConvidados);

        //int convidados ;

        int totalH =  Integer.parseInt(edtHomem.getText().toString());
        int totalM =  Integer.parseInt(edtMulher.getText().toString());
        int totalC =  Integer.parseInt(edtCrianca.getText().toString());

       int convidados = totalC + totalH + totalM;

        txtTotalConvidados.setText("Total de Convidados: " + convidados);

*/

/* Favarin
        txtTotalConvidados = (EditText) findViewById(R.id.txtTotalConvidados);
        int convidados = 0;
        convidados = homem + mulher + crianca;
        txtTotalConvidados.setText("Total de Convidados: " + convidados);

*/
        // MessageBox aparece caso nenhum convidado seja colocado e usuário queira prosseguir
        final AlertDialog alertaConvidados = new AlertDialog.Builder(this).create();
        alertaConvidados.setTitle("Nenhum Convidado");
        alertaConvidados.setMessage("Churrasco sem convidados não é uma boa não. Convida alguém aí!");
        alertaConvidados.setButton("OK", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int which){}
        });
        alertaConvidados.setIcon(R.drawable.ic_launcher);

        btnBebidas.setOnClickListener(new View.OnClickListener() {
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


    private void aumentar(){
        total++;
        txtTotalConvidados.setText(String.valueOf("Total de Convidados: " + total));
    }
private void aumentar(Integer ttl){
        total += ttl;
        txtTotalConvidados.setText(String.valueOf("Total de Convidados: " + total));
    }

    private void diminuir(){
        total--;
        txtTotalConvidados.setText(String.valueOf("Total de Convidados: " + total));
    }

    private void zerar(Integer ttl){
        total = 0;
    }
}
