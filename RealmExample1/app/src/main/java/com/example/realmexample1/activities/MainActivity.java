package com.example.realmexample1.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realmexample1.R;
import com.example.realmexample1.adapter.MyAdapterBoardRecicler;
import com.example.realmexample1.models.Board;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Board>> {

    private FloatingActionButton button;
    private Realm realm;

    private RealmResults<Board> boards;

    private Board b = new Board();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();

        boards = realm.where(Board.class).findAll();
        boards.addChangeListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.reciclerBoardView);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(MainActivity.this);

        mAdapter = new MyAdapterBoardRecicler(boards, R.layout.board_item_layout, new MyAdapterBoardRecicler.OnItemClickListener() {
            @Override
            public void onItemClick(Board board, int position) {
                Toast.makeText(MainActivity.this,board.getTitle() + " - "+ (position +1) + " added !!",Toast.LENGTH_SHORT).show();
                //delete(board.getId());
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("id",board.getId());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(Board board, int position) {
                Toast.makeText(MainActivity.this,"Context menu",Toast.LENGTH_SHORT).show();
                //onCreateContextMenu(this,);
                showAlertForEditingBoard("Edit","",board);

            }

        });


        button = findViewById(R.id.fabAddBoard);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertForCreatingBoard("Title","Message");

            }
        });



        recyclerView.setHasFixedSize(true);
        //si el item siempre va a ser del mismo tamaño mejora el rendimiento del recicler view
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        registerForContextMenu(recyclerView);



        //showAlertForCreatingBoard("Title","Message");
    }


    private void createNewBoard(final String boardName) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Board board = new Board(boardName);
                realm.copyToRealm(board);
            }
        });

    }

    private void  deleteBoard(final Board board){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                board.deleteFromRealm();
            }
        });

    }

    private void  deleteAllBoards(){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });

    }

    private void editBoard(final String newName, final Board board){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                board.setTitle(newName);
                realm.copyToRealmOrUpdate(board);
            }
        });
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
                String boardName = input.getText().toString().trim();
                if(boardName.length() > 0){
                    createNewBoard(boardName);
                }else{
                    Toast.makeText(getApplicationContext(),"Name required", Toast.LENGTH_LONG).show();
                }

            }
        });

        builder.create().show();

    }

    private void showAlertForEditingBoard(String title, String message, final Board board){

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
        input.setText(board.getTitle());


        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String boardName = input.getText().toString().trim();
                if(boardName.length() == 0){
                    Toast.makeText(getApplicationContext(),"Name required", Toast.LENGTH_LONG).show();
                }else if(boardName.equals(board.getTitle())){
                    Toast.makeText(getApplicationContext(),"Name is the same than it was before", Toast.LENGTH_LONG).show();

                }else{
                    editBoard(boardName,board);
                }

            }
        });

        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteBoard(board);
            }

        });

        builder.create().show();

    }


    @Override
    public void onChange(RealmResults<Board> boards) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all:

                deleteAllBoards();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(boards.get(info.position).getTitle());
        getMenuInflater().inflate(R.menu.context_menu_board_activity,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.delete_board:
                deleteBoard(boards.get(info.position));
                return true;
            case R.id.edit_board:
                showAlertForEditingBoard("TEST","TESt",boards.get(info.position));
            default:
                return super.onContextItemSelected(item);
        }
    }
}
