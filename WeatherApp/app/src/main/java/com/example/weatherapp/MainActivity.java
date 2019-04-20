package com.example.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.weatherapp.model.City;
import com.example.weatherapp.service.IWeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IWeatherService iWeatherService = retrofit.create(IWeatherService.class);

        Call<City> cityCall = iWeatherService.getCity("Bilbao,es","f16e2506b08ddfb48663f49d90f1e68d");

        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> request, Response<City> response) {
                //parsed with gson
                City city = response.body();
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
