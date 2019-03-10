package com.example.cardviewandreciclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReciclerGridActivity extends AppCompatActivity {

    private List<String> names;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recicler_grid);
        names = getAllNames();

        recyclerView = (RecyclerView) findViewById(R.id.reciclerGridView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new GridLayoutManager(this,2);


        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                Toast.makeText(ReciclerGridActivity.this,name + " - "+ (position +1) + " will be deleted !!",Toast.LENGTH_SHORT).show();
                delete(position);
            }
        });

        //si el item siempre va a ser del mismo tamaño mejora el rendimiento del recicler view
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    /*----------------opciones de menu------------------------------*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_item:
                this.addItem(0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addItem(int position) {
        names.add(position,"new item"+(++count)); //actualizamos array
        mAdapter.notifyItemInserted(position); // actualizamos adaptador
        layoutManager.scrollToPosition(position);
    }

    private void delete(int position){
        names.remove(position ); //actualizamos array
        mAdapter.notifyItemRemoved(position); // actualizamos adaptador
    }

    private List<String> getAllNames(){
        return new ArrayList<String>(){{
            add("Python");
            add("Java");
            add("Scala");
            add("Php");
        }};
    }
}
