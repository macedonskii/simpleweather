package com.mad.simpleweather.model.storage;

import android.support.annotation.Nullable;

import com.mad.simpleweather.model.api.response.WeatherResponse;
import com.mad.simpleweather.model.data.CityWeather;

public interface Database {
    @Nullable
    CityWeather getCurrentWeather();

    void setWeather(WeatherResponse response);

    void setCurrentCity(int cityId);
}
