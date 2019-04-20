package com.example.gsonretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gsonretrofit.api.API;
import com.example.gsonretrofit.model.City;
import com.example.gsonretrofit.service.IWeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IWeatherService iWeatherService = API.getApi().create(IWeatherService.class);

        Call<City> cityCall = iWeatherService.getCity("Bilbao,es", API.API_KEY);

        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> request, Response<City> response) {
                //parsed with gson
                City city = response.body();
                Toast.makeText(MainActivity.this, city.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        Call<City> cityCallCelsius = iWeatherService.getCityCelsius("Bilbao,es", API.API_KEY, "metric");
        cityCallCelsius.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> request, Response<City> response) {
                City city = response.body();
                Toast.makeText(MainActivity.this, city.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
