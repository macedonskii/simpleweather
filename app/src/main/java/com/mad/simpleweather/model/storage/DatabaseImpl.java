package com.mad.simpleweather.model.storage;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mad.simpleweather.model.api.response.Data;
import com.mad.simpleweather.model.api.response.WeatherResponse;
import com.mad.simpleweather.model.data.CityWeather;
import com.mad.simpleweather.model.data.containers.WeathersContainer;

public class DatabaseImpl implements Database {
    private static final String LAST_UPDATE_TIME = "TIME";
    private static final String WEATHER = "TIME";

    private SharedPreferences mPreferences;

    public DatabaseImpl(Context context) {
        mPreferences = context.getSharedPreferences("pref name", Context.MODE_PRIVATE);
    }

    @Override
    public CityWeather getCurrentWeather() {
        WeathersContainer weather = getWeather();
        Data currentWeather = weather.getCurrentWeather();
        if (currentWeather == null) {
            throw new NullPointerException(new Gson().toJson(currentWeather));
        }
        return new CityWeather(currentWeather.getWeather().get(0).getDescription(),
                currentWeather.getWeather().get(0).getIcon(),
                currentWeather.getMain().getTempMin(),
                currentWeather.getMain().getTempMax(),
                currentWeather.getMain().getTemp()
        );
    }

    @Override
    public void setWeather(WeatherResponse response) {
        WeathersContainer weathersContainer = getWeather();
        weathersContainer.setList(response.getList());
        setWeather(weathersContainer);
    }

    @Override
    public void setCurrentCity(int cityId) {
        WeathersContainer weathersContainer = getWeather();
        weathersContainer.setId(cityId);
        setWeather(weathersContainer);
    }

    @Override
    public void setLastUpdateTime(long l) {
        mPreferences.edit().putLong(LAST_UPDATE_TIME, l).commit();
    }

    @Override
    public long getLastUpdateTime() {
        return mPreferences.getLong(LAST_UPDATE_TIME, 0);
    }

    private WeathersContainer getWeather() {
        Gson gson = new Gson();
        return gson.fromJson(mPreferences.getString(WEATHER, ""), WeathersContainer.class);
    }
    private void setWeather(WeathersContainer container) {
        Gson gson = new Gson();
        mPreferences.edit().putString(WEATHER, gson.toJson(container)).commit();
    }
}
