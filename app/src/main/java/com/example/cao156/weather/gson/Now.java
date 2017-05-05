package com.example.cao156.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cao15 on 2016/12/11.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;
    @SerializedName("fl")
    public String flTempweature;//体感温度
    @SerializedName("pcpn")
    public String pcpNum;//降水量
    @SerializedName("pres")
    public String preStree;//气压
    @SerializedName("hum")
   public String huM;//相对湿度
    public class More{
        @SerializedName("txt")
        public String info;
    }
}
