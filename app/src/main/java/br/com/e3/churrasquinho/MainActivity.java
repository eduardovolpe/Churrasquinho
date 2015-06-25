package br.com.e3.churrasquinho;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    ImageView btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = (ImageView) findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, iniciarActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder alertaSair = new AlertDialog.Builder(this);
        alertaSair.setTitle("Sair");
        alertaSair.setMessage("Tem certeza que deseja sair?");
        alertaSair.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertaSair.setNegativeButton("Não",new DialogInterface.OnClickListener(){
           public void onClick(DialogInterface dialog, int which){

           }
        });
        alertaSair.setIcon(R.drawable.ic_launcher);

        alertaSair.create().show();
    }

}
