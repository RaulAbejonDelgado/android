package com.example.cardviewandreciclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void customReciclerView(View view) {
        Intent reciclerIntent = new Intent(MainActivity.this, ReciclerActivity.class);

        startActivity(reciclerIntent);
    }

    public void cardView(View view) {
    }
}
