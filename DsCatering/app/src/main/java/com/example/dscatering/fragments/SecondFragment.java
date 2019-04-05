package com.example.dscatering.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dscatering.R;
import com.example.dscatering.adapter.MyAdapterCardRecicler;
import com.example.dscatering.adapter.MyAdapterServiceCardRecicler;
import com.example.dscatering.model.Producto;
import com.example.dscatering.model.Servicio;

import java.util.ArrayList;
import java.util.List;


public class SecondFragment extends Fragment {

    private List<Servicio> servicios;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        servicios = getServicios();

        recyclerView = view.findViewById(R.id.reciclerServiceCardView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        // use a linear layout manager
        layoutManager = new GridLayoutManager(getActivity(),1);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapterServiceCardRecicler(getContext(),servicios, R.layout.product_card_item, new MyAdapterServiceCardRecicler.OnItemClickListener() {
            @Override
            public void onItemClick(Servicio servicio, int position) {
                Toast.makeText(getActivity(),servicio.getName() + " - "+ (position +1) + " added !!",Toast.LENGTH_SHORT).show();
                //delete(position);
            }
        });

        recyclerView.setHasFixedSize(true);
        //si el item siempre va a ser del mismo tamaño mejora el rendimiento del recicler view
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    private List<Servicio> getServicios() {
        return new ArrayList<Servicio>(){{
            add(new Servicio("Catering","Servicio de catering",R.drawable.service1));
            add(new Servicio("Entregamos en tu puerta","Servicio de entrega",R.drawable.service2));

        }};
    }


}
