package com.example.climaplant;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherEntry implements Serializable {
    @SerializedName("dt_txt")
    private String dtTxt;

    @SerializedName("weather")
    private List<Weather> weather;

    public String getDtTxt() {
        return dtTxt;
    }

    public String getWeatherDescription() {
        return weather != null && !weather.isEmpty() ? weather.get(0).getDescription() : "";
    }
}