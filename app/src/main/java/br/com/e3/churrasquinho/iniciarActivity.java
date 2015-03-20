package br.com.e3.churrasquinho;

import android.content.Intent;
import android.renderscript.Int2;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class iniciarActivity extends ActionBarActivity {

    Button btnBebidas;
    EditText edtHomem;
    EditText edtMulher;
    EditText edtCrianca;
    EditText txtTotalConvidados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        btnBebidas = (Button) findViewById(R.id.btnIniciar);
        edtHomem = (EditText) findViewById(R.id.edtHomem);
        edtMulher = (EditText) findViewById(R.id.edtMulher);
        edtCrianca = (EditText) findViewById(R.id.edtCriancas);

    /*   txtTotalConvidados = (EditText) findViewById(R.id.txtTotalConvidados);
        Double homem = Double.parseDouble(edtHomem.getText().toString());
        Double mulher = Double.parseDouble(edtMulher.getText().toString());
        Double crianca = Double.parseDouble(edtCrianca.getText().toString());

        Double convidados= 0.0;
        convidados = homem + mulher + crianca;

        txtTotalConvidados.setText(convidados.toString());

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
