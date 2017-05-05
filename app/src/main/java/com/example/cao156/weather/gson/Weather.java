package com.example.cao156.weather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cao15 on 2016/12/11.
 */

public class Weather {
    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
