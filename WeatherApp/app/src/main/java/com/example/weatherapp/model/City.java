package com.example.weatherapp.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City {

    @Expose//to specified serialized property
    private int id;
    private String name;
    @SerializedName("main")
    private Temperature temperature;
    @SerializedName("weather")
    private Weather weather;
    private String country;


    public City(){};

    public City(int id, String name, Temperature temperature, Weather weather,String country ) {
        this.id = id;
        this.name = name;
        this.temperature = temperature;
        this.weather = weather;
        this.country = country;

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
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
                ", temperature=" + temperature +
                ", weather=" + weather +
                ", country='" + country + '\'' +
                '}';
    }

    //    public static Temperature parseJson(String response){
//        Gson gson = new GsonBuilder().create();
//        Temperature temperature = gson.fromJson(response, Temperature.class);
//        return Temperature;
//
//    }



}
