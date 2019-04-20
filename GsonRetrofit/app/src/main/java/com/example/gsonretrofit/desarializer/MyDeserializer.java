package com.example.gsonretrofit.desarializer;

import com.example.gsonretrofit.model.City;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class MyDeserializer implements JsonDeserializer<City> {

    @Override
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        City city = new City();
        int id = json.getAsJsonObject().get("id").getAsInt();
        String name = json.getAsJsonObject().get("name").getAsString();
        String country = json.getAsJsonObject().get("sys").getAsJsonObject().get("country").getAsString();

        city.setId(id);
        city.setName(name);
        city.setCountry(country);

        return city;

    }
}
