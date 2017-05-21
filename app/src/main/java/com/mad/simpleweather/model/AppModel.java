package com.mad.simpleweather.model;

import com.mad.simpleweather.model.data.CityWeather;

import rx.Observable;

/**
 * Created by mad on 21.05.2017.
 */

public interface AppModel {

    /**
     * @return Weather to show or null if weather wasn't updated
     */
    Observable<CityWeather> getWeather();

    /**
     *
     * @param i position item in list
     * @return
     */
    Observable<Boolean> setCurrentWeather(int i);

    /**
     * @return time to next update or 0 if data isn't updated!
     * @param isImmediatelyUpdate - ignore last update time
     */
    Observable<Long> updateWeather(boolean isImmediatelyUpdate);
}
