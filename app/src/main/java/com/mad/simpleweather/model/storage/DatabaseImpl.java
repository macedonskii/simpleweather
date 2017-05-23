package com.mad.simpleweather.model.storage;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import com.mad.simpleweather.model.api.response.Data;
import com.mad.simpleweather.model.api.response.WeatherResponse;
import com.mad.simpleweather.model.data.CityWeather;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class
DatabaseImpl implements Database {
    private static final String LAST_UPDATE_TIME = "TIME";
    private static final String CITY = "CITY";

    private SharedPreferences mPreferences;

    public DatabaseImpl(Context context) {
        mPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
    }

    @Override
    @Nullable
    @UiThread
    public CityWeather getCurrentWeather() {
        Realm instance = Realm.getDefaultInstance();
        final CityWeather[] currentWeather = new CityWeather[1];
        instance.executeTransaction(realm -> {
            CityWeather mId = realm.where(CityWeather.class).equalTo("mId", getCurrentCityId()).findFirst();
            if (mId != null) {
                currentWeather[0] = realm.copyFromRealm(mId);
            }
        });
        instance.close();
        return currentWeather[0];
    }

    @Override
    public void setWeather(WeatherResponse response) {
        List<CityWeather> list = new ArrayList<>();
        for (Data data : response.getList()) {
            list.add(new CityWeather(
                    String.format("%s in\n%s", data.getWeather().get(0).getDescription(), data.getName()),
                    data.getWeather().get(0).getIcon(),
                    data.getMain().getTempMin(),
                    data.getMain().getTempMax(),
                    data.getMain().getTemp(),
                    data.getId()
            ));
        }
        setWeather(list);
    }

    private void setWeather(List<CityWeather> list) {
        Realm instance = Realm.getDefaultInstance();
        instance.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(list);
        });
        instance.close();
    }

    @Override
    public void setCurrentCity(int cityId) {
        mPreferences.edit().putInt(CITY, cityId).commit();
    }

    private int getCurrentCityId() {
        return mPreferences.getInt(CITY, 709930);
    }

}
