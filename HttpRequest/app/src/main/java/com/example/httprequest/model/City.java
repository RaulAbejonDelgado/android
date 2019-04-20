package com.example.httprequest.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City {

    @Expose//to specified serialized property
    private int id;
    private String name;
    @SerializedName("streets") //we can set specific names of jsonObject
    private List<Street> streets ;

    public City(){};

    public City(int id, String name, List<Street> streets) {
        this.id = id;
        this.name = name;
        this.streets = streets;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", streets=" + streets +
                '}';
    }

    public static Street parseJson(String response){

        Gson gson = new GsonBuilder().create();
        Street street = gson.fromJson(response,Street.class);
        return  street;

    }
}
