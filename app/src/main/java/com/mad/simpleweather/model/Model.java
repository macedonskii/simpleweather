package com.mad.simpleweather.model;

import android.support.annotation.Nullable;

import com.mad.simpleweather.model.data.CityWeather;

import rx.Observable;

/**
 * Created by mad on 21.05.2017.
 */

public interface Model {

    /**
     * @return Weather to show or null if weather wasn't updated
     */
    @Nullable
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
