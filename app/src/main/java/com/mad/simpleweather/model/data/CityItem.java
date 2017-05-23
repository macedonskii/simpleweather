package com.mad.simpleweather.model.data;


public class CityItem {

    private String city;
    private int id;

    public CityItem(String city, int id) {
        this.city = city;
        this.id = id;
    }

    public String getCityName() {
        return city;
    }

    public int getId() {
        return id;
    }
}
