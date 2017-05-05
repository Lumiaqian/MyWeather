package com.example.cao156.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cao15 on 2016/12/11.
 */

public class Basic {
    //使用@SerializedName注解来来JSON字段与Java字段之间建立映射关系
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;
    public Update update;
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
