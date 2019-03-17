package com.example.realmexample1.activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Person;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realmexample1.R;
import com.example.realmexample1.models.Board;
import com.example.realmexample1.models.Note;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Note>> {

    private FloatingActionButton button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.fabAddBoard);


        showAlertForCreatingBoard("Title","Message");
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

        final EditText input = (EditText) findViewById(R.id.editTextBoard);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String boardName = input.getText().toString().trim();
                if(boardName.length() > 0){
                    createNewBoard(boardName);
                }else{
                    Toast.makeText(getApplicationContext(),"Name required", Toast.LENGTH_SHORT);
                }

            }
        });

        builder.create().show();


    }

    private void createNewBoard(String boardName) {
    }


    @Override
    public void onChange(RealmResults<Note> notes) {

    }
}
