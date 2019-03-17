package com.example.realmexample1.activities;

import android.support.v4.app.Person;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.realmexample1.R;
import com.example.realmexample1.models.Persona;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Persona>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onChange(RealmResults<Persona> personas) {

    }
}
