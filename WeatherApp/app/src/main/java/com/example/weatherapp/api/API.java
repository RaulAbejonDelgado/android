package com.example.weatherapp.api;


import com.example.weatherapp.model.City;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static String API_KEY = "f16e2506b08ddfb48663f49d90f1e68d";
    private static Retrofit retrofit = null;

    public static Retrofit getApi(){
        if(retrofit == null){
             retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
