package com.mad.simpleweather.model.data;


public class CityWeather {

    private String description;
    private String icon;
    private int temperatureMin;
    private int temperatureMax;
    private int temperatureAverage;

    public CityWeather(String description, String icon, int temperatureMin, int temperatureMax, int temperatureAverage) {
        this.description = description;
        this.icon = String.format("http://openweathermap.org/img/w/%s.png",icon);
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.temperatureAverage = temperatureAverage;
    }


    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getTemperatureMin() {
        return temperatureMin < 0 ? String.valueOf(temperatureMin) : String.format("+%d", temperatureMin);
    }

    public String getTemperatureMax() {
        return temperatureMax < 0 ? String.valueOf(temperatureMax) : String.format("+%d",temperatureMax);
    }

    public String getTemperatureAverage() {
        return temperatureAverage < 0 ? String.valueOf(temperatureAverage) : String.format("+%d",temperatureAverage);
    }
}
