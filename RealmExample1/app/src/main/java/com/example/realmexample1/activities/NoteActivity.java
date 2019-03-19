package com.example.realmexample1.activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realmexample1.R;
import com.example.realmexample1.adapter.MyAdapterBoardRecicler;
import com.example.realmexample1.adapter.MyAdapterNoteRecicler;
import com.example.realmexample1.models.Board;
import com.example.realmexample1.models.Note;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObjectChangeListener;
import io.realm.RealmResults;

public class NoteActivity extends AppCompatActivity implements RealmChangeListener<Board>  {


    private FloatingActionButton button;
    private Realm realm;

    private Board board;

    private RealmList<Note> notes;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private int idBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        realm = Realm.getDefaultInstance();

        if(getIntent().getExtras() != null ){
            idBoard = getIntent().getExtras().getInt("id");
        }

        board = realm.where(Board.class).equalTo("id",idBoard).findFirst();
        board.addChangeListener(this);
        notes = board.getNotes();

        this.setTitle(board.getTitle());

        //((RealmResults<Note>) notes).addChangeListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.noteReciclerView);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(NoteActivity.this);

        mAdapter = new MyAdapterNoteRecicler(notes, R.layout.note_item_layout, new MyAdapterNoteRecicler.OnItemClickListener() {
            @Override
            public void onItemClick(Note note, int position) {
                Toast.makeText(NoteActivity.this,note.getId() + " - "+ (position +1) + " added !!",Toast.LENGTH_SHORT).show();
                //delete(board.getId());
            }

        });

        button = findViewById(R.id.fabAddNote);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertForCreatingBoard("New Note","Note:");

            }
        });

        //recyclerView.setHasFixedSize(true);
        //si el item siempre va a ser del mismo tamaño mejora el rendimiento del recicler view
        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onChange(Board board) {
        mAdapter.notifyDataSetChanged();
    }

    private void showAlertForCreatingBoard(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(title != null){
            builder.setTitle(title);
        }
        if(message != null){
            builder.setMessage(message);
        }

        View viewLayoutInflater = LayoutInflater.from(this).inflate(R.layout.dialog_create_board,null);

        builder.setView(viewLayoutInflater);
        final EditText input = (EditText) viewLayoutInflater.findViewById(R.id.editTextBoard);


        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String noteDescription = input.getText().toString().trim();
                if(noteDescription.length() > 0){
                    createNewComment(noteDescription);
                }else{
                    Toast.makeText(getApplicationContext(),"Note can not be empty", Toast.LENGTH_LONG).show();
                }

            }
        });

        builder.create().show();

    }

    private void createNewComment(final String noteDescription) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Note _note = new Note(noteDescription);
                realm.copyToRealm(_note);
                board.getNotes().add(_note);

            }
        });


    }


}
