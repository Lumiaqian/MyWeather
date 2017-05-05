package com.example.cao156.weather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by cao15 on 2016/12/11.
 */

public class HttpUtil {
    //
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
