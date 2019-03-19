package com.example.realmexample1.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.realmexample1.R;
import com.example.realmexample1.models.Board;
import com.example.realmexample1.models.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MyAdapterNoteRecicler extends RecyclerView.Adapter<MyAdapterNoteRecicler.ViewHolder> {

    private List<Board> boards;
    private List<Note> notes;
    private int layout;
    private OnItemClickListener oICL;

    public MyAdapterNoteRecicler(List<Note> notes, int layout, OnItemClickListener oICL) {
        this.notes = notes;
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
        viewHolder.bind(notes.get(i),oICL);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView itemDescription;
        public TextView itemDate;


        public ViewHolder(View v){
            super(v);
            this.itemDescription = v.findViewById(R.id.noteDescriptionTextView);
            this.itemDate = v.findViewById(R.id.noteCreatedAtTexView);

        }
        public void bind(final Note note, final OnItemClickListener listener){
            this.itemDescription.setText(note.getDescription());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String createdAt = dateFormat.format(note.getCreateAt());
            this.itemDate.setText(createdAt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(note,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note, int position);
    }


}
