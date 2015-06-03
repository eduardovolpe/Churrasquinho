package br.com.e3.churrasquinho;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class iniciarActivity extends Activity {

    Button btnCarne;

    EditText txtTotalHomem;
    EditText txtTotalMulher;
    EditText txtTotalCrianca;

/*  ImageView menosH;
    ImageView maisH;
    ImageView menosM;
    ImageView maisM;
    ImageView menosC;
    ImageView maisC; */

    private int total;
    private int totalHomem = 0;
    private int totalMulher = 0;
    private int totalCrianca = 0;
    public static List selecionadas = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        btnCarne = (Button) findViewById(R.id.btnProsseguir);

        txtTotalHomem = (EditText) findViewById(R.id.txtTotalHomem);
        txtTotalMulher = (EditText) findViewById(R.id.txtTotalMulher);
        txtTotalCrianca = (EditText) findViewById(R.id.txtTotalCrianca);

        txtTotalHomem.findFocus();
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

                totalHomem = Integer.valueOf(txtTotalHomem.getText().toString());
                totalMulher = Integer.valueOf(txtTotalMulher.getText().toString());
                totalCrianca = Integer.valueOf(txtTotalCrianca.getText().toString());

                total = totalHomem + totalMulher + totalCrianca;

                if (total == 0) {
                    alertaConvidados.show();
                } else {
                    Intent intent = new Intent(iniciarActivity.this, carneActivity.class);
                    Toast.makeText(iniciarActivity.this, "Quantidade de Convidados: " + total, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

                selecionadas.add(totalHomem);
                selecionadas.add(totalMulher);
                selecionadas.add(totalCrianca);
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_iniciar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

