package com.example.realmexample1.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.realmexample1.R;
import com.example.realmexample1.models.Board;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MyAdapterBoardRecicler extends RecyclerView.Adapter<MyAdapterBoardRecicler.ViewHolder> {

    private List<Board> boards;
    private int layout;
    private OnItemClickListener oICL;

    public MyAdapterBoardRecicler(List<Board> boards, int layout, OnItemClickListener oICL) {
        this.boards = boards;
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
        viewHolder.bind(boards.get(i),oICL);
    }

    @Override
    public int getItemCount() {
        return boards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView itemTitle;
        public TextView itemNote;
        public TextView itemDate;


        public ViewHolder(View v){
            super(v);
            this.itemTitle = v.findViewById(R.id.boardItemTitleView);
            this.itemNote = v.findViewById(R.id.boardItemNoteView);
            this.itemDate = v.findViewById(R.id.boardItemDateView);

        }
        public void bind(final Board board, final OnItemClickListener listener){
            this.itemTitle.setText(board.getTitle());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String createdAt = dateFormat.format(board.getCreateAt());
            this.itemDate.setText(createdAt);
            String nNotes = board.getNotes().size() > 0 ? "Notes" : "Note";
            this.itemNote.setText(board.getNotes().size()+" "+ nNotes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(board,getAdapterPosition());
                }

            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    System.out.println("*********************");
                    listener.onLongItemClick(board,getAdapterPosition());
                    return true;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Board board, int position);
        void onLongItemClick(Board board, int position);
    }






}
