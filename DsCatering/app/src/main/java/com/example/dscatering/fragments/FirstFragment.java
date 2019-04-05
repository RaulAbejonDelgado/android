package com.example.dscatering.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dscatering.R;
import com.example.dscatering.activities.HomeActivity;
import com.example.dscatering.adapter.MyAdapterCardRecicler;
import com.example.dscatering.dao.CarritoDao;
import com.example.dscatering.model.Producto;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private List<Producto> productos;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private CarritoDao carritoDao;
    private EditText quantity;


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        productos = getProducts();
        carritoDao = CarritoDao.getInstance();

        quantity = view.findViewById(R.id.pop_quantity_text);
        recyclerView = (RecyclerView) view.findViewById(R.id.reciclerProductCardView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        // use a linear layout manager
        layoutManager = new GridLayoutManager(getActivity(),1);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapterCardRecicler(getContext(),productos, R.layout.product_card_item, new MyAdapterCardRecicler.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {
                //Toast.makeText(getActivity(),producto.getName() + " - "+ (position +1) + " added !!",Toast.LENGTH_SHORT).show();
                //delete(position);
                showInfoAndMore(producto,position);
            }
        });

        recyclerView.setHasFixedSize(true);
        //si el item siempre va a ser del mismo tamaño mejora el rendimiento del recicler view
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);



        return view;
    }

    private void showInfoAndMore(final Producto producto, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View viewLayoutInflater = LayoutInflater.from(getActivity()).inflate(R.layout.pop_info_layout,null);

        builder.setView(viewLayoutInflater);
        //final EditText input = (EditText) viewLayoutInflater.findViewById(R.id.pop_quantity_text);
        final TextView description = viewLayoutInflater.findViewById(R.id.pop_description_view);
        final ImageView image = viewLayoutInflater.findViewById(R.id.pop_image_view);
        final TextView title = viewLayoutInflater.findViewById(R.id.pop_title_view);
        image.setImageResource(producto.getImage());
        title.setText(producto.getName());
        description.setText(producto.getDescription());

        Picasso.with(getContext()).load(producto.getImage()).fit().placeholder(R.drawable.dslogo).into(image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

                Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();

            }
        });

        builder.setNegativeButton("Atras", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(getActivity(),"Closed !!",Toast.LENGTH_SHORT).show();

            }
        });
        builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                String boardName = input.getText().toString().trim();
//                if(boardName.length() > 0){
//                    createNewBoard(boardName);
//                }else{
//                    Toast.makeText(getApplicationContext(),"Name required", Toast.LENGTH_LONG).show();
//                }
                //Toast.makeText(getActivity(),"Product added !!",Toast.LENGTH_SHORT).show();
                showQuantityDialog(producto, position);

            }
        });

        builder.create().show();


    }

    private void showQuantityDialog(final Producto producto, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View viewLayoutInflater = LayoutInflater.from(getActivity()).inflate(R.layout.pop_quantity_dialog,null);

        builder.setView(viewLayoutInflater);
        final EditText input = (EditText) viewLayoutInflater.findViewById(R.id.pop_quantity_text);
        final TextView textView = viewLayoutInflater.findViewById(R.id.productPrice);
        textView.setText(producto.getPrecio());

        builder.setNegativeButton("Atras", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(getActivity(),"Closed !!",Toast.LENGTH_SHORT).show();
                //showInfoAndMore(producto, position);

            }
        });
        builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                String boardName = input.getText().toString().trim();
//                if(boardName.length() > 0){
//                    createNewBoard(boardName);
//                }else{
//                    Toast.makeText(getApplicationContext(),"Name required", Toast.LENGTH_LONG).show();
//                }
                Toast.makeText(getActivity(),"Product added !!",Toast.LENGTH_SHORT).show();
                int x = Integer.parseInt(input.getText().toString());
                //productos.add(new Producto(producto.getName(),x,producto.getDescription(),producto.getImage()));

                carritoDao.setIntemOnCarrito(new Producto(producto.getName(),x,producto.getDescription(),producto.getImage(),producto.getPrecio()),0);
                //showInfoAndMore(producto, position);


            }
        });

        builder.create().show();
    }

    private List<Producto> getProducts(){
        return new ArrayList<Producto>(){{
            add(new Producto("Bandeja1",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.bandejadecanapes,"30 unidades por bandeja: 15€\""));
            add(new Producto("Bandeja2",12,"Lorem Ipsum is simply dummy text of the printing and typesetting intry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.bandejadecroissants,"30 unidades por bandeja: 15€\""));
            add(new Producto("Bandeja de tortilla",12,"\n" +
                    "Jamón y queso.\n" +
                    "Vegetal.\n" +
                    "Piminetos.\n" +
                    "Normal o con cebolla.\n"
                    ,R.drawable.bandejatortilla, "30 unidades por bandeja: 15€"));
            add(new Producto("Bandeja4",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.hojaldresycroissants,"30 unidades por bandeja: 15€\""));
            add(new Producto("Bandeja5",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.montaditos,"30 unidades por bandeja: 15€\""));
            add(new Producto("Bandeja6",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.sandwichesvariadosenenva,"30 unidades por bandeja: 15€\""));
            add(new Producto("Bandeja7",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.sandwichesvariadosenfilm,"30 unidades por bandeja: 15€\""));
            add(new Producto("Bandeja8",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.sandwichesycroissantsen,"30 unidades por bandeja: 15€\""));
            add(new Producto("Bandeja9",12,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",R.drawable.zapatillas,"30 unidades por bandeja: 15€\""));

        }};
    }

}
