package com.mad.simpleweather.model.data;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CityWeather extends RealmObject {

    @PrimaryKey
    private int mId;
    private String mDescription;
    private String mIcon;
    private double mTemperatureMin;
    private double mTemperatureMax;
    private double mTemperatureAverage;

    public CityWeather() {
    }

    public CityWeather(String description, String icon, double temperatureMin, double temperatureMax, double temperatureAverage, int id) {
        mDescription = description;
        mIcon = String.format("http://openweathermap.org/img/w/%s.png", icon);
        mTemperatureMin = temperatureMin;
        mTemperatureMax = temperatureMax;
        mTemperatureAverage = temperatureAverage;
        mId = id;
    }


    public String getDescription() {
        return mDescription;
    }

    public String getIcon() {
        return mIcon;
    }

    public String getTemperatureMin() {
        return mTemperatureMin < 0 ? String.valueOf(mTemperatureMin) : String.format("+%d", (int) mTemperatureMin);
    }

    public String getTemperatureMax() {
        return mTemperatureMax < 0 ? String.valueOf(mTemperatureMax) : String.format("+%d", (int) mTemperatureMax);
    }

    public String getTemperatureAverage() {
        return mTemperatureAverage < 0 ? String.valueOf(mTemperatureAverage) : String.format("+%d", (int) mTemperatureAverage);
    }
}
