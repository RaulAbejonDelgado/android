package com.example.cardviewandreciclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReciclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recicler_view);
    }

    public void reciclerGridViewClick(View view) {
    }

    public void reciclerListViewClick(View view) {
        Intent reciclerIntent = new Intent(ReciclerActivity.this, ReciclerListActivity.class);

        startActivity(reciclerIntent);
    }
}
