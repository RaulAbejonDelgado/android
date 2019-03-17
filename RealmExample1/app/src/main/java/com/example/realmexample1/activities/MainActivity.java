package com.example.realmexample1.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Person;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

        //button.setOnClickListener();

    }


    @Override
    public void onChange(RealmResults<Note> notes) {

    }
}
