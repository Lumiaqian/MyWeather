package com.example.cao156.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cao15 on 2016/12/11.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;
    @SerializedName("cw")
    public CarWash carWash;
    public Sport sport;
    public Drsg drsg;
    public Flu flu;
    public Uv uv;
    public Trav trav;
    public class Trav{
        @SerializedName("txt")
        public String info;
    }
    public class Uv{
        @SerializedName("txt")
        public String info;
    }
    public class Flu{
        @SerializedName("txt")
        public String info;
    }
    public class Drsg{
        @SerializedName("txt")
        public String info;
    }
    public class Comfort{
        @SerializedName("txt")
        public String info;
    }
    public class CarWash{
        @SerializedName("txt")
        public String info;
    }
    public class Sport{
        @SerializedName("txt")
        public String info;
    }
}
