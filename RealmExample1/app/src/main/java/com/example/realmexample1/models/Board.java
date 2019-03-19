package com.example.realmexample1.models;

import com.example.realmexample1.activities.NoteActivity;
import com.example.realmexample1.app.MyApplication;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Board extends RealmObject {

    @PrimaryKey
    private int id;

    @Required
    private String title ;

    @Required
    private Date createAt;

    //with realmlist create relation on sqlite
    private RealmList<Note> notes;

    public Board(){

    }

    public Board(String title) {
        this.id = MyApplication.BOARD_ID.incrementAndGet();
        this.title = title;
        this.createAt = new Date();
        this.notes = new RealmList<Note>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public RealmList<Note> getNotes() {
        return notes;
    }


}
