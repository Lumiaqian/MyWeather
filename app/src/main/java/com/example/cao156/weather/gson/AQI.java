package com.example.cao156.weather.gson;

/**
 * Created by cao15 on 2016/12/11.
 */

public class AQI {
    public AQICity city;
    public class AQICity{
        public String aqi;
        public String pm25;
        public String qlty;
    }
}
