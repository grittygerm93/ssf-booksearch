package com.example.ssfassessment.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Sample {

    public JsonObject toJson() {
        return Json.createObjectBuilder()
//                .add("main", main)
//                .add("description", description)
//                .add("icon", icon)
//                .add("cityName", cityName)
//                .add("temp", temp)
                .build();
    }

    public static Sample create(JsonObject o) {
        Sample sample = new Sample();
//        sample.setMain(o.getString("main"));
//        sample.setDescription(o.getString("description"));
//        sample.setIcon(String.format("http://openweathermap.org/img/wn/%s@2x.png", o.getString("icon")));
//        System.out.println(String.format("http://openweathermap.org/img/wn/%s@2x.png", o.getString("icon")));
        return sample;
    }
}
