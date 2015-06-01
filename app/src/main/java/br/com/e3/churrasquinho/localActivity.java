package br.com.e3.churrasquinho;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class localActivity extends ActionBarActivity {

    String cep,logradouro,bairro,cidade,uf,resultado;
    EditText etCep, etLogradouro, etBairro, etCidade, etUf, edtComplemento, edtReferencia, edtNumero;
    Button btnBuscar, btnSalvar;
    private ProgressDialog dialog;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        etCep = (EditText) findViewById(R.id.etCep);

        etLogradouro = (EditText) findViewById(R.id.etLogradouro);
        etBairro = (EditText) findViewById(R.id.etBairro);
        etCidade = (EditText) findViewById(R.id.etCidade);
        etUf = (EditText) findViewById(R.id.etUf);
        edtComplemento = (EditText) findViewById(R.id.edtComplemento);
        edtReferencia = (EditText) findViewById(R.id.edtReferencia);
        edtNumero = (EditText) findViewById(R.id.edtNumero);

        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        //btnSalvos = (Button) findViewById(R.id.btnSalvos);
        //btnAlert = (Button) findViewById(R.id.btnAlert);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HttpGetter get = new HttpGetter();
                get.execute();

                etCep = (EditText) findViewById(R.id.etCep);
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(etCep.getWindowToken(), 0);
            }
        });

    }
        private class HttpGetter extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPreExecute() {

                if (localActivity.this.dialog != null) {
                    localActivity.this.dialog.dismiss();
                }
                localActivity.this.dialog = ProgressDialog.show(localActivity.this, "Aguarde", "Processando", true, false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                StringBuilder builder = new StringBuilder();
                HttpClient client = new DefaultHttpClient();

                cep = etCep.getEditableText().toString();

                HttpGet httpGet = new HttpGet("http://viacep.com.br/ws/" + cep + "/json/");

                try {
                    HttpResponse response = client.execute(httpGet);
                    StatusLine statusLine = response.getStatusLine();
                    int statusCode = statusLine.getStatusCode();
                    if (statusCode == 200) {
                        HttpEntity entity = response.getEntity();
                        InputStream content = entity.getContent();
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(content));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                        }

                        JSONObject jsonObj = new JSONObject(builder.toString());
                        resultado = jsonObj.getString("resultado_txt");

                        if (resultado.equalsIgnoreCase("sucesso")) {

                            logradouro = jsonObj.getString("logradouro");
                            bairro = jsonObj.getString("bairro");
                            cidade = jsonObj.getString("cidade");
                            uf = jsonObj.getString("uf");
                        }
                    } else {
                        Log.e("Falha", "Falha ao acessar o serviço.");
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void r) {
                localActivity.this.dialog.dismiss();

                if (resultado.equalsIgnoreCase("sucesso")) {
                    etLogradouro.setText(logradouro);
                    etBairro.setText(bairro);
                    etCidade.setText(cidade);
                    etUf.setText(uf);
                } else {
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                    etLogradouro.setText("");
                    etBairro.setText("");
                    etCidade.setText("");
                    etUf.setText("");
                }
            }
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
