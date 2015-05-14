package br.com.e3.churrasquinho;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class iniciarActivity extends Activity implements View.OnTouchListener {

    Button btnCarne;

    TextView txtTotalHomem;
    TextView txtTotalMulher;
    TextView txtTotalCrianca;

    ImageView menosH;
    ImageView maisH;
    ImageView menosM;
    ImageView maisM;
    ImageView menosC;
    ImageView maisC;

    private int total = 0;
    private int totalHomem = 0;
    private int totalMulher = 0;
    private int totalCrianca = 0;

    TextView txtTotal;

    private int count = 0;
    private int velocidade = 0;

    private Handler handler;
    private boolean isPressed = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        handler = new Handler();

        btnCarne = (Button) findViewById(R.id.btnCarne);

        txtTotalHomem = (TextView) findViewById(R.id.txtTotalHomem);
        txtTotalMulher = (TextView) findViewById(R.id.txtTotalMulher);
        txtTotalCrianca = (TextView) findViewById(R.id.txtTotalCrianca);
        txtTotal = (TextView) findViewById(R.id.txtTotalConvidados);

        menosH = (ImageView) findViewById(R.id.menosH);
        maisH = (ImageView) findViewById(R.id.maisH);
        menosM = (ImageView) findViewById(R.id.menosM);
        maisM = (ImageView) findViewById(R.id.maisM);
        menosC = (ImageView) findViewById(R.id.menosC);
        maisC = (ImageView) findViewById(R.id.maisC);

        menosH.setOnTouchListener(this);
        maisH.setOnTouchListener(this);
        menosM.setOnTouchListener(this);
        maisM.setOnTouchListener(this);
        menosC.setOnTouchListener(this);
        maisC.setOnTouchListener(this);

        txtTotal.setText("Total Convidados: " + total);
//        txtTotalHomem.setText(totalHomem);

/*
        maisH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalHomem++;
                edtHomem.setText(totalHomem.toString());

                total = totalHomem + totalMulher + totalCrianca;
            }
        });

        menosH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalHomem <= 0)
                    totalHomem = 0;
                else
                    totalHomem--;


                edtHomem.setText(totalHomem.toString());

                total = totalHomem + totalMulher + totalCrianca;
            }
        });

        maisM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalMulher++;
                edtMulher.setText(totalMulher.toString());

                total = totalHomem + totalMulher + totalCrianca;
            }
        });

        menosM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalMulher <= 0)
                    totalMulher = 0;
                else
                    totalMulher--;

                edtMulher.setText(totalMulher.toString());

                total = totalHomem + totalMulher + totalCrianca;
            }
        });

        maisC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               totalCrianca++;
               edtCrianca.setText(totalCrianca.toString());

               total = totalHomem + totalMulher + totalCrianca;
            }
        });

        menosC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalCrianca <= 0)
                    totalCrianca = 0;
                else
                    totalCrianca--;

                edtCrianca.setText(totalCrianca.toString());

                total = totalHomem + totalMulher + totalCrianca;
            }
        });
*/

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
                if (total == 0) {
                    alertaConvidados.show();
                } else {
                    Intent intent = new Intent(iniciarActivity.this, carneActivity.class);
                    startActivity(intent);
                }
            }

        });
    }

    public boolean onTouch(View v, MotionEvent event) {
        // Pega o evento disparado
        int action = event.getAction() & MotionEvent.ACTION_MASK;

        // Verifica se é a view que queremos testar (no caso o botão)
        if (v.getId() == R.id.maisH) {

            // Verifica qual evento é
            switch (action) {
                // Evento pressionando
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN: {
                    // Altera o estado para pressionado
                    isPressed = true;
                    // Chama o handler que fica se "autou chamando" até que o estado
                    // do botão seja mudado para false
                    velocidade = 0;
                    incrementarHomem();
                    break;
                }

                // Evento soltando
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL: {
                    // Altera o estado para não pressionado
                    isPressed = false;
                    break;
                }
            }
            return true;
        }
        if (v.getId() == R.id.menosH) {

            // Verifica qual evento é
            switch (action) {
                // Evento pressionando
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN: {
                    // Altera o estado para pressionado
                    isPressed = true;
                    // Chama o handler que fica se "autou chamando" até que o estado
                    // do botão seja mudado para false
                    velocidade = 0;
                    decrementarHomem();
                    break;
                }

                // Evento soltando
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL: {
                    // Altera o estado para não pressionado
                    isPressed = false;
                    break;
                }
            }
            return true;
        }

        else {
            // Caso não seja a view que queremos tratar, não iremos tratar o
            // evento
            return false;
        }
    }

    /**
     * Usada para incrementar o contador.
     */
    public void incrementarHomem() {
        // Se o botão está pressionado, incrementa o contador e depois chama
        // novamente o updater() para seguir incrementando o valor

        if (isPressed) {
            velocidade++;
            totalHomem++;

            updateTextView();

            Runnable notification = new Runnable() {
                public void run() {
                    incrementarHomem();
                }
            };
            // Regula a velocidade do contador
            VelocidadeContador(notification);
        }
    }

    public void decrementarHomem() {
        // Se o botão está pressionado, incrementa o contador e depois chama
        // novamente o updater() para seguir incrementando o valor
        if (isPressed) {
            velocidade++;
            totalHomem--;

            updateTextView();

            Runnable notification = new Runnable() {
                public void run() {
                    decrementarHomem();
                }
            };

            VelocidadeContador(notification);
        }

    }

    private void updateTextView() {
        total = totalHomem + totalCrianca + totalMulher;

        txtTotalHomem.setText(totalHomem);
        txtTotalMulher.setText(totalMulher);
        txtTotalCrianca.setText(totalCrianca);
        txtTotal.setText("Incremento: " + total);

    }

    public void VelocidadeContador(Runnable notification){
        // Regula a velocidade do contador
        if(velocidade <= 2)
            handler.postDelayed(notification, 300);
        if(velocidade >= 3 && velocidade <= 5)
            handler.postDelayed(notification, 200);
        if(velocidade > 5 && velocidade <= 7)
            handler.postDelayed(notification, 120);
        if(velocidade > 7)
            handler.postDelayed(notification, 60);
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

