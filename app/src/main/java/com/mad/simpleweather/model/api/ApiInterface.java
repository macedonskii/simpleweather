package com.mad.simpleweather.model.api;

import com.mad.simpleweather.model.api.response.WeatherResponse;

import java.util.ArrayList;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {

    @GET("/data/2.5/group")
    Observable<Response<WeatherResponse>> getWeather(@Query("units") String units, @Query("appid") String appId, @Query("id") String cities);

}
