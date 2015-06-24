package br.com.e3.churrasquinho;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class bebidaActivity extends ActionBarActivity {

    Button btnProsseguir;
    ListView lstBebida;
    DBAdapterBebida dbAdapterBebida;
    Button btnInserir;

    int j = 0;

    double homens, mulheres, criancas, ttlHomem, ttlMulher, ttlCrianca;

    double ttl269, cLata269;
    double ttl350, cLata350;
    double ttl473, cLata473;
    double ttl550, cLata550;
    double ln250, cLn250;
    double ln275, cLn275;
    double ln355, cLn355;
    double gr300, cGr300;
    double gr500, cGr500;
    double gr600, cGr600;
    double gr970, cGr970;
    double gr1L, cGr1L;
    double gr15L, cGr15L;
    double gr2L, cGr2L;
    double gr25L, cGr25L;
    double gr3L, cGr3L;

    public static List<String> selecionadas = new ArrayList<>();
    public static List<String> categoria = new ArrayList<>();
    public static List<Double> valores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebida);

        lstBebida = (ListView) findViewById(R.id.lstBebidas);
        btnProsseguir = (Button) findViewById(R.id.btnProsseguir);
        btnInserir = (Button) findViewById(R.id.btnCadBebida);

        dbAdapterBebida = new DBAdapterBebida(this);
        dbAdapterBebida.open();

        List<Bebida> bebidas = dbAdapterBebida.listar();
        final AdapterListBebida adapter = new AdapterListBebida(this, bebidas );
        lstBebida.setAdapter(adapter);
        dbAdapterBebida.close();

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bebidaActivity.this, InserirCarne.class);

                Bundle param = new Bundle();
                param.putString("texto", "bebida");
                intent.putExtras(param);

                startActivity(intent);
            }

        });


        btnProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("Bebidas selecionadas: \n");

                selecionadas.clear();
                categoria.clear();
                valores.clear();

                /* RECUPERANDO A QUANTIDADE DE CONVIDADOS */
                homens = iniciarActivity.convidados[0];
                mulheres = iniciarActivity.convidados[1];
                criancas = iniciarActivity.convidados[2];

                List<Bebida> bebidaList = adapter.getBebidas();
                for(int i=0;i<bebidaList.size();i++){
                    Bebida bebidis = bebidaList.get(i);
                    if(bebidis.isMarcado()){
                        responseText.append("\n" + bebidis.getNomeBebida() + " " + bebidis.getTipoBebida());
                        responseText.append(" - R$: " + bebidis.getValorBebida());

/*                        switch (bebidis.getTipoBebida()) {
                            case "Lata 269ml" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cLata269 = criancas * 6.00;
                                }

                                ttl269 = ((homens * 12.0) + (mulheres * 10.0) + cLata269);

                                break;
                            case "Lata 350ml" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cLata350 = cLata350 * 4.00;
                                }

                                ttl350 = ((homens * 10.0) + (mulheres * 18.0) + cLata350);

                                break;
                            case "Latão 473ml" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cLata473 = criancas * 4.00;
                                }

                                ttl473 = ((homens * 8.0) + (mulheres * 6.0) + cLata473);
                                break;
                            case "Latão 550ml" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cLata550 = criancas * 2.00;
                                }

                                ttl550 = ((homens * 6.0) + (mulheres * 4.0) + cLata550);
                                break;
                            case "Long Neck 250ml" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cLn250 = criancas * 6.00;
                                }

                                ln250 = ((homens * 13.0) + (mulheres * 11.0) + cLn250);
                                break;
                            case "Long Neck 275ml" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cLn275 = criancas * 6.00;
                                }

                                ln275 = ((homens * 12.0) + (mulheres * 10.0) + cLn275);

                                break;
                            case "Long Neck 355ml" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cLn355 = criancas * 5.00;
                                }

                                ln355 = ((homens * 9.0) + (mulheres * 7.0) + cLn355);
                                break;
                            case "Garrafa 300ml" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cGr300 = criancas * 6.00;
                                }

                                gr300 = ((homens * 10.0) + (mulheres * 8.0) + cGr300);
                                break;
                            case "Garrafa 500ml" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cGr500 = criancas * 3.00;
                                }

                                gr500 = ((homens * 7.0) + (mulheres * 5.0) + cGr500);

                                break;
                            case "Garrafa 600ml" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cGr600 = criancas * 2.00;
                                }

                                gr600 = ((homens * 6.0) + (mulheres * 4.0) + cGr600);

                                break;
                            case "Garrafa 970ml" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cGr970 = criancas * 1.5;
                                }

                                gr970 = ((homens * 5.0) + (mulheres * 3.0) + cGr970);

                                break;
                            case "Garrafa 1L" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cGr1L = criancas * 1.5;
                                }

                                gr1L = ((homens * 5.0) + (mulheres * 3.0) + cGr1L);

                                break;
                            case "Garrafa 1,5L" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cGr15L = criancas * 1.0;
                                }

                                gr15L = ((homens * 2.0) + (mulheres * 1.5) + cGr15L);

                                break;
                            case "Garrafa 2L" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cGr2L = criancas * 0.5;
                                }

                                gr2L = ((homens * 1.0) + (mulheres * 1.0) + cGr2L);

                                break;
                            case "Garrafa 2,5L" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cGr25L = criancas * 0.3;
                                }

                                gr25L = ((homens * 0.5) + (mulheres * 0.5) + cGr25L);

                                break;
                            case "Garrafa 3L" :

                                if (bebidis.getCategoria() != "Alcoólica"){
                                    cGr3L = criancas * 0.2;
                                }

                                gr3L = ((homens * 0.4) + (mulheres * 0.3) + cGr300);

                                break;
                        }

                        responseText.append(" Tipo: " + ttl269);
                    */
                        selecionadas.add(bebidis.getNomeBebida());
                        categoria.add(bebidis.getTipoBebida());
                        valores.add(bebidis.getValorBebida());

                        //j++;

                    }
                }
                /*
                // Média das Latas
                ttl269 = ttl269 / j;
                ttl350 = ttl350 / j;
                ttl473 = ttl473 / j;
                ttl550 = ttl550 / j;
                // Médias das Long Necks
                ln355 = ln355 / j;
                ln275 = ln275 / j;
                ln250 = ln250 / j;
                //Média das Garrafas
                gr300 = gr300 / j;
                gr500 = gr500 / j;
                gr600 = gr600 / j;
                gr970 = gr970 / j;
                gr1L = gr1L / j;
                gr15L = gr15L / j;
                gr2L = gr2L / j;
                gr25L = gr25L / j;
                gr3L = gr3L / j;
                */

                if (j > 0) {
                    Toast.makeText(bebidaActivity.this, responseText, Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(bebidaActivity.this, acompanhamentoActivity.class);
                startActivity(intent);
            }
        });

    }

}