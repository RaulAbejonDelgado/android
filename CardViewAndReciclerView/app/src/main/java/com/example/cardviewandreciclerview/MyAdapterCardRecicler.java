package com.example.cardviewandreciclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cardviewandreciclerview.model.BandejaSandiwch;

import java.util.List;

public class MyAdapterCardRecicler extends RecyclerView.Adapter<MyAdapterCardRecicler.ViewHolder> {

    private List<BandejaSandiwch> bandejas;
    private int layout;
    private OnItemClickListener oICL;

    public MyAdapterCardRecicler(List<BandejaSandiwch> bandejas, int layout, OnItemClickListener oICL) {
        this.bandejas = bandejas;
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
        viewHolder.bind(bandejas.get(i),oICL);
    }

    @Override
    public int getItemCount() {
        return bandejas.size();
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
        public void bind(final BandejaSandiwch bandeja, final OnItemClickListener listener){
            this.textViewName.setText(bandeja.getNombre());
            bandejaView.setImageResource(bandeja.getImagen());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(bandeja,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(BandejaSandiwch bandeja, int position);
    }
}
