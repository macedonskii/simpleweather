package com.mad.simpleweather.model;

import com.mad.simpleweather.App;
import com.mad.simpleweather.model.api.ApiInterface;
import com.mad.simpleweather.model.data.CityWeather;
import com.mad.simpleweather.model.storage.Database;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ModelImpl implements AppModel {

    private static final int SECOND = 1000;
    private static final int MINUTE = SECOND * 60;
    private static final int TIME_TO_UPDATE = MINUTE * 30;


    private static final int[] CITIES = {1, 2, 3};

    @Inject
    Database mDatabase;
    @Inject
    ApiInterface mApi;

    public ModelImpl() {
        App.getGraph().inject(this);
    }

    @Override
    public Observable<CityWeather> getWeather() {
        return null;
    }

    @Override
    public Observable<Boolean> setCurrentWeather(int i) {
        return null;
    }

    @Override
    public Observable<Long> updateWeather(boolean isImmediatelyUpdate) {
        if (isImmediatelyUpdate) {
            return loadWeather().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        } else {
            return Observable.just(mDatabase).flatMap(database -> {
                long lastUpdateTime = database.getLastUpdateTime();
                long l = System.currentTimeMillis();
                if (l - lastUpdateTime > TIME_TO_UPDATE) {
                    return loadWeather().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                }
                return Observable.just(0L);
            });
        }
    }

    private Observable<Long> loadWeather() {
        return mApi.getWeather(CITIES, "metric", "b1b15e88fa797225412429c1c50c122a1").map(asd -> {
            if (!asd.isSuccessful()) {
                return 0L;
            }
            mDatabase.setWeather(asd.body());
            long l = System.currentTimeMillis();
            mDatabase.setLastUpdateTime(l);
            return l + TIME_TO_UPDATE;
        });
    }
}
