package br.com.e3.churrasquinho;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static br.com.e3.churrasquinho.carneActivity.*;


public class ResumoActivity extends ActionBarActivity {

    Button btnProsseguir;
    Button btnLocalizacao;
    TextView txtCarnes;

    List<Carne> carnesSelecionadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        txtCarnes = (TextView) findViewById(R.id.txtCarnes);
        btnProsseguir = (Button) findViewById(R.id.btnProsResumo);
        btnLocalizacao = (Button) findViewById(R.id.btnLocalizacao);


        //carneActivity.selecionadas = carnesSelecionadas;

        //txtCarnes.setText(carnesSelecionadas.size());

         btnProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumoActivity.this, FinalizarActivity.class);
                startActivity(intent);
            }
        });

        btnLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumoActivity.this, localActivity.class);
                startActivity(intent);
            }
        });
    }
}