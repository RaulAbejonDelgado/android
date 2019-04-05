package com.example.dscatering.fragments;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dscatering.R;
import com.example.dscatering.adapter.MyAdapterCardRecicler;
import com.example.dscatering.adapter.MyAdaptercarritoProductRecicler;
import com.example.dscatering.dao.CarritoDao;
import com.example.dscatering.model.Carrito;
import com.example.dscatering.model.Producto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FloatingActionButton fab;
    private EditText name;
    private EditText address;
    private EditText email;
    private EditText phone;
    private EditText note;
    private TextView pedido;
    private Date orderDate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String orderDateFormater = dateFormat.format(orderDate);

    private CarritoDao carritoDao;

    EditText editText;
    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        //editText = (EditText) view.findViewById(R.id.phone_text);
        carritoDao = CarritoDao.getInstance();

        recyclerView = (RecyclerView) view.findViewById(R.id.reciclerCarritoProducts);
        layoutManager = new GridLayoutManager(getActivity(),1);

        name = view.findViewById(R.id.name_text);
        address = view.findViewById(R.id.address_text);
        email = view.findViewById(R.id.electronic_address_text);
        phone = view.findViewById(R.id.phone_text);
        note = view.findViewById(R.id.note_text);
        fab = view.findViewById(R.id.fab);
        pedido = view.findViewById(R.id.order_textview);
        pedido.setText(phone.getText() + orderDateFormater);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final Intent intent = new Intent(Intent.ACTION_VIEW)
//                        .setType("plain/text")
//                        .setData(Uri.parse("test@gmail.com"))
//                        .setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail")
//                        .putExtra(Intent.EXTRA_EMAIL, new String[]{"test@gmail.com"})
//                        .putExtra(Intent.EXTRA_SUBJECT, "test")
//                        .putExtra(Intent.EXTRA_TEXT, "hello. this is a message sent from my demo app :-)");
//                startActivity(intent);

                Intent emailTo = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+"drohne@gmail.com"));
                emailTo.putExtra("subject", "Pedido : " + pedido.getText()+"/"+phone.getText());
                emailTo.putExtra(
                        Intent.EXTRA_TEXT,
                        Html.fromHtml(new StringBuilder()
                                .append("<h1>Pedido : "+ pedido.getText()+"</h1>")
                                .append("<p><b>Datos de contacto</b></p>")
                                .append("<p><b>Nombre: </b>"+ name.getText()+"</p>")
                                .append("<p><b>Dirección de entrega: </b>"+ address.getText()+"</p>")
                                .append("<p><b>Correo electrónico: </b>"+ email.getText()+"</p>")
                                .append("<p><b>Teléfono de contacto: </b>"+ phone.getText()+"</p>")
                                .append("<p><b>Notas: </b>"+ note.getText()+"</p>")
                                .append("<p><b>Detalle del pedido </b></p>")
                                .append("<p>"+ getProductosString() +"</p>")


                                .toString())
                );

                emailTo.setPackage("com.google.android.gm");
                if (emailTo.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivity(emailTo);
                    //force to user to choose app
                    //startActivity(Intent.createChooser(emailTo, "Choose email app"))

                }else{
                    Toast.makeText(getActivity(),"Gmail App is not installed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAdapter = new MyAdaptercarritoProductRecicler(getContext(),carritoDao.getCarritos().getProductos(), R.layout.carrito_item, new MyAdaptercarritoProductRecicler.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {
                Toast.makeText(getActivity(),producto.getName() + " - "+ (position +1) + " added !!",Toast.LENGTH_SHORT).show();
                //delete(position);
                showEditDeleteDialog(producto,position);
            }

        });

        refresRecicler();

        return view;
    }

    private void showEditDeleteDialog(final Producto producto, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View viewLayoutInflater = LayoutInflater.from(getActivity()).inflate(R.layout.pop_quantity_dialog,null);

        builder.setView(viewLayoutInflater);
        final EditText input = (EditText) viewLayoutInflater.findViewById(R.id.pop_quantity_text);
        final TextView textView = viewLayoutInflater.findViewById(R.id.productPrice);
        textView.setText(producto.getPrecio());
        input.setText(String.valueOf(producto.getnItems()));

        builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(getActivity(),"Closed !!",Toast.LENGTH_SHORT).show();
                carritoDao.delete(producto, position);
                refresRecicler();

            }
        });
        builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
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

                carritoDao.updateItemOnCarrito(new Producto(producto.getName(),x,producto.getDescription(),producto.getImage(),producto.getPrecio()),position);
                //showInfoAndMore(producto, position);
                refresRecicler();



            }
        });

        builder.create().show();
    }

    private void refresRecicler() {

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }


    private String getProductosString() {
        String productosToEmail ="";

        for (Producto p : carritoDao.getCarritos().getProductos()){

            productosToEmail +="Producto : "+ p.getName() + " Cantidad: " + p.getnItems() + "<br>";

        }

        return productosToEmail;
    }

}
