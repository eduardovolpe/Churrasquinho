package br.com.e3.churrasquinho;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

public class FinalizarActivity extends ActionBarActivity {

    Button btnShareChurras;
    GridView grid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar);

        btnShareChurras = (Button) findViewById(R.id.btnShareChurras);
        grid = (GridView) findViewById(R.id.gridView);

        String rua = localActivity.endereco[0];
        String numero = localActivity.endereco[1];
        String bairro = localActivity.endereco[2];
        String cidade = localActivity.endereco[3];
        String referencia = localActivity.endereco[6];

        String vHomem = ResumoActivity.data[0];
        String vMulher = ResumoActivity.data[1];
        String vCrianca = ResumoActivity.data[2];
        String data = ResumoActivity.data[3];

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ResumoActivity.itens );

        grid.setAdapter(adapter);

        final StringBuffer shareText = new StringBuffer();

        shareText.append("⚠⚠🚨" + " ATENÇÃO GALERA!! " + "\n");
        shareText.append("Churras dia " + data);
        shareText.append(", na " + rua + " nº" + numero + ", no " + bairro + ", em " + cidade + ", perto do(a) " + referencia + "." + "\n");
        shareText.append("O valor da vaquinha ficou: \n" +
                         "Homem - R$:" + vHomem + ", \n" +
                         "Mulher - R$:" + vMulher + " e \n" +
                         "Criança - R$:" + vCrianca + ".\n");
        shareText.append("Quem for, da um grito ae!🙊🙉"+"\n" + "\n");
        shareText.append("\n" + "-- Churrasquinho. BAIXEM!");


        btnShareChurras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(shareText));
                startActivity(Intent.createChooser(intent, "Compartilhar"));

            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finalizar, menu);
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
