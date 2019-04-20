package com.example.httprequest.model;

import com.google.gson.annotations.Expose;

public class Street {
    @Expose//to specified serialized property
    private int id;
    private String streetName;

    public Street(){};

    public Street(int id, String name) {
        this.id = id;
        this.streetName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return streetName;
    }

    public void setName(String name) {
        this.streetName = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + streetName + '\'' +
                '}';
    }

}
