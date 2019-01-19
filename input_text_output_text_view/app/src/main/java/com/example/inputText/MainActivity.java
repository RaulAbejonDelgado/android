package com.example.inputText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inputText.pojo.Texto;
import com.example.saludargente.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Texto> mensajes = new ArrayList<>();
    Texto mensaje;
    String finalMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void processData(View view) {

        EditText inputText = findViewById(R.id.inputText);
        TextView outputText = findViewById(R.id.outputText);

        mensaje = new Texto();
        mensaje.setTexto(String.valueOf(inputText.getText()+"\n"));
        mensajes.add(mensaje);

        finalMsg = fixString(mensajes);

        outputText.setText(finalMsg);

        inputText.setText("");
    }

    private String fixString(ArrayList<Texto> mensajes) {

        String finalMsg ;

        finalMsg = mensajes.toString().replace("[","");
        finalMsg = finalMsg.replace("]","");
        finalMsg = finalMsg.replace(",","");
        finalMsg = finalMsg.replace(" ","");

        return finalMsg;
    }

    public void clearData(View view) {
        TextView outputText = findViewById(R.id.outputText);

        outputText.setText("");

    }
}
