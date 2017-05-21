package com.mad.simpleweather.presenter;


import com.mad.simpleweather.view.WeatherFragment;
import com.mad.simpleweather.view.view.WeatherView;

public class WeatherPresenter extends AbstractPresenter {

    private final WeatherView mView;

    public WeatherPresenter(WeatherView view) {
        mView = view;
    }

    public void onResume() {

    }
}
