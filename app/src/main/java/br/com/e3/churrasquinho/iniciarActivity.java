package br.com.e3.churrasquinho;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class iniciarActivity extends Activity {

    Button btnCarne;

    EditText txtTotalHomem;
    EditText txtTotalMulher;
    EditText txtTotalCrianca;

    private int total;
    private int totalHomem = 0;
    private int totalMulher = 0;
    private int totalCrianca = 0;

    public static Integer[] convidados = new Integer[3];
    public static ProgressDialog pConvidados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        btnCarne = (Button) findViewById(R.id.btnProsseguir);
        convidados[0] = 0;
        convidados[1] = 0;
        convidados[2] = 0;

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
                pConvidados = ProgressDialog.show(iniciarActivity.this, "Processando", "Aguarde...", true, false);

                totalHomem = Integer.valueOf(txtTotalHomem.getText().toString());
                totalMulher = Integer.valueOf(txtTotalMulher.getText().toString());
                totalCrianca = Integer.valueOf(txtTotalCrianca.getText().toString());

                total = totalHomem + totalMulher + totalCrianca;

                if (total == 0) {
                    pConvidados.dismiss();
                    alertaConvidados.show();
                } else {

                    convidados[0] = totalHomem;
                    convidados[1] = totalMulher;
                    convidados[2] = totalCrianca;

                    Intent intent = new Intent(iniciarActivity.this, carneActivity.class);
                    Toast.makeText(iniciarActivity.this, "Quantidade de Convidados: " + total, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

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

