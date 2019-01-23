package com.raul.ipartek.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =  MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE =
            "com.raul.ipartek.twoactivities.extra.MESSAGE";
    private EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageEditText = findViewById(R.id.editText);
    }

    public void launchSecondActivity(View view) {
    //le pasamos los datos a la segunda actividad
        Log.d(LOG_TAG, "Button clicked!");

        Intent intent = new Intent(this,SecondActivity.class);

        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }
}
