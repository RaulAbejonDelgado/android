package com.example.weatherapp.api;


import com.example.weatherapp.desarializer.MyDeserializer;
import com.example.weatherapp.model.City;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static String API_KEY = "f16e2506b08ddfb48663f49d90f1e68d";
    private static Retrofit retrofit = null;
    public static String IMG_URL = "http://openweathermap.org/img/w/";

    public static Retrofit getApi(){
        if(retrofit == null){

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(City.class, new MyDeserializer());

             retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }
        return retrofit;
    }
}
