package com.mad.simpleweather.model.storage;

import com.mad.simpleweather.model.api.response.WeatherResponse;
import com.mad.simpleweather.model.data.CityWeather;

public interface Database {

    long getLastUpdateTime();

    CityWeather getCurrentWeather();

    void setWeather(WeatherResponse response);

    void setCurrentCity(int cityId);

    void setLastUpdateTime(long l);
}
