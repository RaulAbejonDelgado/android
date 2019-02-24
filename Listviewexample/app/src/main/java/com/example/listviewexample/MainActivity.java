package com.example.listviewexample;

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

    private ListView lw ;
    List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lw = findViewById(R.id.listView);

        names = new ArrayList<String>();

        names.add("Borja");
        names.add("Alvaro");
        names.add("David");
        names.add("Asier");
        //added more to check scrollable function
        names.add("Borja");
        names.add("Alvaro");
        names.add("David");
        names.add("Asier");
        names.add("Borja");
        names.add("Alvaro");
        names.add("David");
        names.add("Asier");
        names.add("Borja");
        names.add("Alvaro");
        names.add("David");
        names.add("Asier");

        //need adapter to connect values with listview
        //two ways

        //native method to show data
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names);
//        lw.setAdapter(adapter);

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "You clicked on -> "+ names.get(position),Toast.LENGTH_LONG).show();

            }
        });

        //link with custom adapter
        MyAdapter mA = new MyAdapter(this,R.layout.list_item, names);
        lw.setAdapter(mA);
    }
}

