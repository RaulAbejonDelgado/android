package com.example.cardviewandreciclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.cardviewandreciclerview.model.BandejaSandiwch;

import java.util.ArrayList;
import java.util.List;

public class CardReciclerViewActivity extends AppCompatActivity {

    private List<BandejaSandiwch> bandejas;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ImageView imageView;

    boolean isImageFitToScreen;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_recicler_view);
        bandejas = getAllNames();

        recyclerView = (RecyclerView) findViewById(R.id.reciclerCardView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);


        // specify an adapter (see also next example)
        mAdapter = new MyAdapterCardRecicler(bandejas, R.layout.card_recicler_view_item, new MyAdapterCardRecicler.OnItemClickListener() {
            @Override
            public void onItemClick(BandejaSandiwch sandiwch, int position) {
                //Toast.makeText(CardReciclerViewActivity.this,name + " - "+ (position +1) + " will be deleted !!",Toast.LENGTH_SHORT).show();
                delete(position);
            }
        });

        recyclerView.setHasFixedSize(true);
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
        bandejas.add(position,new BandejaSandiwch("Bandeja4",12,"Bandeja variada",R.drawable.hojaldresycroissants)); //actualizamos array
        mAdapter.notifyItemInserted(position); // actualizamos adaptador
        layoutManager.scrollToPosition(position);
    }

    private void delete(int position){
        bandejas.remove(position ); //actualizamos array
        mAdapter.notifyItemRemoved(position); // actualizamos adaptador
    }


    private List<BandejaSandiwch> getAllNames(){
        return new ArrayList<BandejaSandiwch>(){{
            add(new BandejaSandiwch("Bandeja1",12,"Bandeja variada",R.drawable.bandejadecanapes));
            add(new BandejaSandiwch("Bandeja2",12,"Bandeja variada",R.drawable.bandejadecroissants));
            add(new BandejaSandiwch("Bandeja3",12,"Bandeja sanwich..",R.drawable.bandejatortilla));
            add(new BandejaSandiwch("Bandeja4",12,"Bandeja de croisants variada",R.drawable.hojaldresycroissants));
            add(new BandejaSandiwch("Bandeja5",12,"Bandeja sanwich..",R.drawable.montaditos));
            add(new BandejaSandiwch("Bandeja6",12,"Bandeja sanwich..",R.drawable.sandwichesvariadosenenva));
            add(new BandejaSandiwch("Bandeja7",12,"Bandeja sanwich..",R.drawable.sandwichesvariadosenfilm));
            add(new BandejaSandiwch("Bandeja8",12,"Bandeja sanwich..",R.drawable.sandwichesycroissantsen));
            add(new BandejaSandiwch("Bandeja9",12,"Bandeja sanwich..",R.drawable.zapatillas));

        }};
    }
}
