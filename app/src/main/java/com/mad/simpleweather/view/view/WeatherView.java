package com.mad.simpleweather.view.view;

import com.mad.simpleweather.model.data.CityWeather;

public interface WeatherView extends View{
    void setWeather(CityWeather cityWeather);
}
