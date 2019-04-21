package com.example.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.api.API;
import com.example.weatherapp.model.City;
import com.example.weatherapp.service.IWeatherService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView cityText;
    private TextView tempText;
    private TextView detailText;
    private ImageView iconView;
    private Button buttonFind;
    private EditText cityToFind;
    private String defaultCity = "Bilbao,es";
    private IWeatherService iWeatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityText = findViewById(R.id.city);
        tempText = findViewById(R.id.temp);
        detailText = findViewById(R.id.detail);
        iconView = findViewById(R.id.weatherIcon);
        buttonFind = findViewById(R.id.findCityButton);
        cityToFind = findViewById(R.id.findCity);

        iWeatherService = API.getApi().create(IWeatherService.class);
        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultCity = cityToFind.getText().toString();
                getCityWeather(defaultCity);
            }
        });

        getCityWeather(defaultCity);

    }

    private void getCityWeather(String city) {

        Call<City> cityCallCelsius = iWeatherService.getCityCelsius(defaultCity, API.API_KEY, "metric");

        cityCallCelsius.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> request, Response<City> response) {
                City city = response.body();

                cityText.setText(city.getName());
                city.getTemperature().getTemp();
                tempText.setText(String.valueOf(city.getTemperature().getTemp()));
                detailText.setText(city.toString());

                String iconUrl = API.IMG_URL + city.getWeather().getIcon() + ".png";
                Picasso.with(MainActivity.this).load(iconUrl).into(iconView);

            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
