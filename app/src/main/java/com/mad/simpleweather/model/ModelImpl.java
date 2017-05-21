package com.mad.simpleweather.model;

import android.content.Context;
import android.support.annotation.Nullable;

import com.mad.simpleweather.App;
import com.mad.simpleweather.R;
import com.mad.simpleweather.model.api.ApiInterface;
import com.mad.simpleweather.model.data.CityWeather;
import com.mad.simpleweather.model.storage.Database;

import java.util.Arrays;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ModelImpl implements Model {

    private static final int SECOND = 1000;
    private static final int MINUTE = SECOND * 60;
    private static final int TIME_TO_UPDATE = MINUTE * 30;


    private static final int[] CITIES = {1, 2, 3};

    @Inject
    Database mDatabase;
    @Inject
    ApiInterface mApi;
    @Inject
    Context mContext;

    public ModelImpl() {
        App.getGraph().inject(this);
    }

    @Override
    @Nullable
    public Observable<CityWeather> getWeather() {
        return Observable.just(mDatabase.getCurrentWeather());
    }

    @Override
    public Observable<Boolean> setCurrentWeather(int i) {
        return Observable.just(mDatabase).map(database -> {
            database.setCurrentCity(i);
            return true;
        });
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
        StringBuilder str = new StringBuilder();
        for (int city : mContext.getResources().getIntArray(R.array.cities_id)) {
            str.append(city).append(",");
        }
        str.deleteCharAt(str.length() - 1);
        return mApi.getWeather("metric", "366a16719c97c87cb1b3d62d04f1b0b4", str.toString()).map(asd -> {
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
