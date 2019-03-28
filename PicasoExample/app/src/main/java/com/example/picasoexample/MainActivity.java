package com.example.picasoexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.picasoexample.adapter.ProdImageAdapter;
import com.example.picasoexample.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProdImageAdapter adapter ;
    private List<Producto> productos;
    private RecyclerView rView;
    private RecyclerView.LayoutManager lManager;
    private int[] imageProdudIds;

    private final int PERMISSION_READ_EXTERNAL_MEMORY = 1;  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        productos = getProductos();
        //imageProdudIds = getImageProdudIds();

        rView = (RecyclerView) findViewById(R.id.reciclerView);
        lManager = new GridLayoutManager(this,2);
        adapter = new ProdImageAdapter(this, productos, R.layout.image_layout, new ProdImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {
                Toast.makeText(MainActivity.this,producto.getName() + " - "+ (position +1) + " added !!",Toast.LENGTH_SHORT).show();

            }
        });

        rView.setHasFixedSize(true);
        rView.setItemAnimator(new DefaultItemAnimator());

        rView.setLayoutManager(lManager);
        rView.setAdapter(adapter);
        
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.fromApp:
                rView.setAdapter(adapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private List<Producto> getProductos() {
        return new ArrayList<Producto>(){{
            add(new Producto("Bandeja1",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.bandejadecanapes));
            add(new Producto("Bandeja2",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.bandejadecroissants));
            add(new Producto("Bandeja3",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.bandejatortilla));
            add(new Producto("Bandeja4",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.hojaldresycroissants));
            add(new Producto("Bandeja5",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.montaditos));
            add(new Producto("Bandeja6",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.sandwichesvariadosenenva));
            add(new Producto("Bandeja7",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.sandwichesvariadosenfilm));
            add(new Producto("Bandeja8",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.sandwichesycroissantsen));
            add(new Producto("Bandeja9",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.zapatillas));

        }};
    }

//    private int[] getImageProdudIds() {
//
//        int[] values = {
//             R.drawable.bandejadecanapes,
//             R.drawable.bandejadecroissants,
//             R.drawable.bandejatortilla,
//        };
//        return values;
//
//            }


}
