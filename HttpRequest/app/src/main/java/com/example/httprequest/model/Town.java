package com.example.httprequest.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Town {

    private int id;
    private City city;

    public Town(){};

    public Town(int id, City city) {
        this.id = id;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Town{" +
                "id=" + id +
                ", city=" + city +
                '}';
    }

    /**
     * This method is used to Gson can convert City object inside Town object
     * @param response String with city data
     * @return City Object
     */
    public static City parseJson(String response){

        Gson gson = new GsonBuilder().create();
        City city = gson.fromJson(response,City.class);
        return  city;

    }
}
