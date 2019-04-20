package com.example.gsonretrofit.service;

import com.example.gsonretrofit.model.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWeatherService {

    @GET("weather")
    Call<City> getCity(@Query("q") String city, @Query("appid") String key);

    @GET("weather")
    Call<City> getCityCelsius(@Query("q") String city, @Query("appid") String key, @Query("units") String units);

}
