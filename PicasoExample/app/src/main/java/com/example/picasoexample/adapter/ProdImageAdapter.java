package com.example.picasoexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picasoexample.model.Producto;
import com.example.picasoexample.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProdImageAdapter extends RecyclerView.Adapter<ProdImageAdapter.ViewHolder> {

    private Context context;
    private List<Producto> productos;
    private int layout;
    private OnItemClickListener oICL;

    public ProdImageAdapter(Context context, List<Producto> productos, int layout, OnItemClickListener oICL) {
        this.context = context;
        this.productos = productos;
        this.layout = layout;
        this.oICL = oICL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        ViewHolder vH = new ViewHolder(v);
        return vH;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(productos.get(i).getImage()).fit().placeholder(R.drawable.bandejadecanapes).into(viewHolder.bandejaView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();

            }
        });

        viewHolder.bind(productos.get(i),oICL);


    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //public TextView textViewName;
        public ImageView bandejaView;

        public ViewHolder(View v){
            super(v);
            //this.textViewName = v.findViewById(R.id.textViewName);
            //textViewName = (TextView) itemView.findViewById(R.id.textBandejaView);
            bandejaView = (ImageView) itemView.findViewById(R.id.imgView);
        }
        public void bind(final Producto producto, final OnItemClickListener listener){
            //this.textViewName.setText(producto.getName());
            bandejaView.setImageResource(producto.getImage());
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
