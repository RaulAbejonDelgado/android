package com.example.cardviewandreciclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        TextView tV = (TextView) findViewById(R.id.textImage);
        tV.setText("Bandeja de canapes");
        tV.setTextColor(getResources().getColor(R.color.colorWhite));
    }
}
