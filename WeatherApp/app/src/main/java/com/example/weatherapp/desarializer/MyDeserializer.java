package com.example.weatherapp.desarializer;


import com.example.weatherapp.model.City;
import com.example.weatherapp.model.Temperature;
import com.example.weatherapp.model.Weather;
import com.google.gson.JsonDeserializationContext;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class MyDeserializer implements JsonDeserializer<City> {

    @Override
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        City city = new City();
        Temperature temperature = new Temperature();
        Weather weather = new Weather();

        //city data
        int id = json.getAsJsonObject().get("id").getAsInt();
        String name = json.getAsJsonObject().get("name").getAsString();
        String country =json.getAsJsonObject().get("sys").getAsJsonObject().get("country").getAsString() ;

        weather.setIcon(json.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString());
        weather.setId(json.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt());
        weather.setMain(json.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString());
        weather.setDescription(json.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString());

        temperature.setTemp(json.getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsFloat());
        temperature.setPreassure(json.getAsJsonObject().get("main").getAsJsonObject().get("pressure").getAsFloat());
        temperature.setHumidity(json.getAsJsonObject().get("main").getAsJsonObject().get("humidity").getAsFloat());
        temperature.setTempMin(json.getAsJsonObject().get("main").getAsJsonObject().get("temp_min").getAsFloat());
        temperature.setTempMax(json.getAsJsonObject().get("main").getAsJsonObject().get("temp_max").getAsFloat());

        city.setId(id);
        city.setName(name);
        city.setCountry(country);
        city.setTemperature(temperature);
        city.setWeather(weather);

        return city;

    }
}
