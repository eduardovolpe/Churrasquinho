package br.com.e3.churrasquinho;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.*;

import org.json.JSONException;
import org.json.JSONObject;


public class localActivity extends ActionBarActivity {

    EditText edtCep, edtLogradouro, edtBairro, edtCidade, edtUf, edtComplemento, edtReferencia, edtNumero;
    Button btnConsultar, btnInserir;
    final Context context = this;

    public static String[] endereco = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        //edtComplemento = (EditText) findViewById(R.id.edtComplemento);
        edtReferencia = (EditText) findViewById(R.id.edtReferencia);
        edtNumero = (EditText) findViewById(R.id.edtNumero);

        btnConsultar = (Button) findViewById(R.id.btnBuscar);
        btnInserir = (Button) findViewById(R.id.btnSalvarEndereco);

        edtCep = (EditText) findViewById(R.id.etCep);
        edtLogradouro = (EditText) findViewById(R.id.etLogradouro);
        edtBairro = (EditText) findViewById(R.id.etBairro);
        edtCidade = (EditText) findViewById(R.id.etCidade);
        edtUf = (EditText) findViewById(R.id.etUf);

        edtCep.findFocus();

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String cep = edtCep.getText().toString();
                final String url = "http://viacep.com.br/ws/"+cep+"/json/";

                //if(edtCep.length() == 9) {
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    JsonObjectRequest getRequest =
                            new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        limpar();

                                        if (jsonObject.has("logradouro")) {
                                            edtLogradouro.setText(jsonObject.getString("logradouro"));
                                        }
                                        if (jsonObject.has("localidade")) {
                                            edtCidade.setText(jsonObject.getString("localidade"));
                                        }
                                        if (jsonObject.has("bairro")) {
                                            edtBairro.setText(jsonObject.getString("bairro"));
                                        }
                                        if (jsonObject.has("uf")) {
                                            edtUf.setText(jsonObject.getString("uf"));
                                        }
                                        if (jsonObject.has("erro")) {
                                            Toast.makeText(localActivity.this, "CEP " + cep + " inválido!", Toast.LENGTH_SHORT).show();
                                            edtCep.setText("");
                                        }

                                        Log.d("Resposta: ", jsonObject.toString());

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Log.d("Error.Response", volleyError.getMessage());
                                }
                            });
                    queue.add(getRequest);
                /*} else {
                    Toast.makeText(localActivity.this, "CEP " + cep + " inválido!", Toast.LENGTH_SHORT).show();
                }*/
                final String finalCep = cep;
            }
        });

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(localActivity.this, ResumoActivity.class);

                Bundle param = new Bundle();
                param.putInt("end", 1);
                intent.putExtras(param);

                endereco[0] = edtLogradouro.getText().toString();
                endereco[1] = edtNumero.getText().toString();
                endereco[2] = edtBairro.getText().toString();
                endereco[3] = edtCidade.getText().toString();
                endereco[4] = edtUf.getText().toString();
                endereco[5] = edtCep.getText().toString();
                endereco[6] = edtReferencia.getText().toString();

                localActivity.this.finish();
                startActivity(intent);

            }
        });
    }

    public void limpar(){
        edtBairro.setText(" ");
        edtLogradouro.setText(" ");
        edtUf.setText(" ");
        edtCidade.setText(" ");
        edtReferencia.setText(" ");
        edtNumero.setText(" ");

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
