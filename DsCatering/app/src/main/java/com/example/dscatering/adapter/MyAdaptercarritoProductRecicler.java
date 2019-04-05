package com.example.dscatering.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dscatering.R;
import com.example.dscatering.model.Producto;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdaptercarritoProductRecicler extends RecyclerView.Adapter<MyAdaptercarritoProductRecicler.ViewHolder> {

    private List<Producto> productos;
    private int layout;
    private OnItemClickListener oICL;
    private Context context;

    public MyAdaptercarritoProductRecicler(Context context, List<Producto> productos, int layout, OnItemClickListener oICL) {
        this.context = context;
        this.productos = productos;
        this.layout = layout;
        this.oICL = oICL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        ViewHolder vH = new ViewHolder(v);
        return vH;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {



        viewHolder.bind(productos.get(i),oICL);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewQuantity;

        public ViewHolder(View v){
            super(v);
            //this.textViewName = v.findViewById(R.id.textViewName);
            textViewName = (TextView) itemView.findViewById(R.id.productName);
            textViewQuantity = (TextView) itemView.findViewById(R.id.productQantity);
        }
        public void bind(final Producto producto, final OnItemClickListener listener){
            this.textViewName.setText(producto.getName());
            this.textViewQuantity.setText(String.valueOf(producto.getnItems()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(producto,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Producto producto, int position);
    }
}
