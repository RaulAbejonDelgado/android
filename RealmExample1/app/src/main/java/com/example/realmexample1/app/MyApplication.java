package com.example.realmexample1.app;

import android.app.Application;

import com.example.realmexample1.models.Board;
import com.example.realmexample1.models.Note;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApplication extends Application {

    public static AtomicInteger BOARD_ID = new AtomicInteger();
    public static AtomicInteger NOTE_ID = new AtomicInteger();


    //Global config
    //Always execute before of MainActivity
    @Override
    public void onCreate() {

        super.onCreate();
        Realm realm = Realm.getDefaultInstance();
        BOARD_ID = getIdByTable(realm, Board.class);
        NOTE_ID = getIdByTable(realm, Note.class);
        realm.close();

    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){

        RealmResults<T> results = realm.where(anyClass).findAll();

        return (results.size() > 0 ) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();


    }
}
