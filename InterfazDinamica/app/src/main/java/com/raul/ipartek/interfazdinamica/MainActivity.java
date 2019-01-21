package com.raul.ipartek.interfazdinamica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int secuencia = 0;
    private TextView tvDisplayGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplayGlobal = (TextView) findViewById(R.id.tvDisplay);
        //recogemos el table layout
        final TextView tvDisplay = findViewById(R.id.tvDisplay);
        final TableLayout tl = findViewById(R.id.tlBotonera);


        //generado desde 0
        TableRow tr = null;

        //creamos boton
        Button b ;
        for(int i= 9; i >= 0 ; i--){

            //Para divir las lineas en 3 elementos
            if(i % 3 == 0){
                tr = new TableRow(this);
                //al table layout le agragamos la linea
                tl.addView(tr);
            }

            b =  new Button(this);
            //agregamos valor al boton
            b.setText(String.valueOf(i));
           //  creamos un parametros para el ultimo boton
            TableRow.LayoutParams bParams = new TableRow.LayoutParams();

            bParams.weight = 1;

            b.setLayoutParams(bParams);
            //escuchador en el boton
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Button bPulsado = (Button) v;
                    String res = bPulsado.getText().toString();
                    String previusText = tvDisplay.getText().toString();

                    if(!"0".equals(previusText)){

                       res = previusText + res;

                    }

                    tvDisplay.setText(res);
                }
            });


            //agregamos el boton a la linea
            tr.addView(b);
        }

    }
    public void doCe(View view) {
        tvDisplayGlobal.setText("0");
    }
}
