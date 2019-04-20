package com.example.httprequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.httprequest.model.City;
import com.example.httprequest.model.Town;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String jsonCity = "{\"id\":1," +
                        "\"name\":\"CityObject\"}";

    String jsonTown = "{ \"id\":1," +
                            "\"city\" :{ "+
                                    "\"id\":1,"+
                                    "\"name\":\"Bilbao\"," +
                                    "\"streets\" : [" +
                                                    "{"+
                                                        "\"id\" : 1,"+
                                                        "\"streetName\":\"Uribarri\""+
                                                    "}," +
                                                    "{"+
                                                        "\"id\" : 2,"+
                                                        "\"streetName\":\"Zurbaran\""+
                                                    "}" +
                                                "]"+

                                "}"+
                        "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //native method
        //City city = nativeParseCity(jsonCity);


        //we have using Gson library to parse json objects to java objects
        Gson gson = new Gson();
        Town town = gson.fromJson(jsonTown,Town.class);
        City city1 = gson.fromJson(jsonCity,City.class);

        //Toast.makeText(this, city.toString() +"\n"+ city1.toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, city1.toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, town.toString(), Toast.LENGTH_LONG).show();
    }




//    /**
//     * This method works vainila style, is a native method to do parse
//     * @param json data
//     * @return City object
//     */
//    private City nativeParseCity(String json){
//        City city = null;
//        //native method
//        try{
//
//            JSONObject jsonObject = new JSONObject(json);
//
//            city = new City(jsonObject.getInt("id"),
//                    jsonObject.getString("name"));
//
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//        return city;
//    }
}
