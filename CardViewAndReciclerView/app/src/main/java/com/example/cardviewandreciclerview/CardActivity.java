package com.example.cardviewandreciclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
    }

    public void cardViewExample(View view) {

        Intent reciclerIntent = new Intent(CardActivity.this, CardViewActivity.class);

        startActivity(reciclerIntent);
    }
}
