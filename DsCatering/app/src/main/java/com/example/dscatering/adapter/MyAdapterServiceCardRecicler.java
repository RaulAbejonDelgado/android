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
import com.example.dscatering.activities.HomeActivity;
import com.example.dscatering.model.Servicio;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapterServiceCardRecicler extends RecyclerView.Adapter<MyAdapterServiceCardRecicler.ViewHolder> {

    private List<Servicio> servicios;
    private int layout;
    private OnItemClickListener oICL;
    private Context context;

    public MyAdapterServiceCardRecicler(Context context,List<Servicio> servicios, int layout, OnItemClickListener oICL) {
        this.context = context;
        this.servicios = servicios;
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

        Picasso.with(context).load(servicios.get(i).getImage()).fit().placeholder(R.drawable.bandejadecanapes).into(viewHolder.bandejaView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();

            }
        });
        viewHolder.bind(servicios.get(i),oICL);
    }

    @Override
    public int getItemCount() {
        return servicios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public ImageView bandejaView;

        public ViewHolder(View v){
            super(v);
            //this.textViewName = v.findViewById(R.id.textViewName);
            textViewName = (TextView) itemView.findViewById(R.id.textBandejaView);
            bandejaView = (ImageView) itemView.findViewById(R.id.imageViewBandeja);
        }
        public void bind(final Servicio servicio, final OnItemClickListener listener){
            this.textViewName.setText(servicio.getName());
            bandejaView.setImageResource(servicio.getImage());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(servicio,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Servicio servicio, int position);
    }
}
