package com.example.weatherapp.service;

import com.example.weatherapp.model.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWeatherService {

    @GET("weather")
    Call<City> getCity(@Query("q") String city,@Query("appid") String key);

}
