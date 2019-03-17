package com.example.realmexample1.models;

import com.example.realmexample1.app.MyApplication;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Note extends RealmObject {

    @PrimaryKey
    private int id;

    @Required
    private String description ;

    @Required
    private Date createAt;

    public Note(){

    }

    public Note(String description) {
        this.id = MyApplication.NOTE_ID.incrementAndGet(); ;
        this.description = description;
        this.createAt = new Date();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateAt() {
        return createAt;
    }
}
