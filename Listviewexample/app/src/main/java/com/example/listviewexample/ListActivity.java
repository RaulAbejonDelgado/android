package com.example.listviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView lw ;
    List<String> names;
    MyAdapter mA;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

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

                Toast.makeText(ListActivity.this, "You clicked on -> "+ names.get(position),Toast.LENGTH_LONG).show();

            }
        });

        //link with custom adapter
        mA = new MyAdapter(this,R.layout.list_item, names);
        lw.setAdapter(mA);
    }

    //to add option button on activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater mI = getMenuInflater();
        mI.inflate(R.menu.action_bar_name,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                //add new name
                this.names.add("Adder nº"+(++counter));
                //notify to adapter the updated
                this.mA.notifyDataSetChanged();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //to inflate button to delete delete
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mI = getMenuInflater();
        mI.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.delete_item:
                this.names.remove(info.position);
                //notify to adapter the updated
                this.mA.notifyDataSetChanged();


                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
