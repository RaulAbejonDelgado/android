package com.example.listviewexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void openListView(View view) {
        Intent listIntent = new Intent(MainActivity.this, ListActivity.class);

        startActivity(listIntent);

    }

    public void openGridView(View view) {

        Intent gridIntent = new Intent(MainActivity.this, GridActivity.class);

        startActivity(gridIntent);


    }
}

