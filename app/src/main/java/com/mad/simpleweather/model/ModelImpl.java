package com.mad.simpleweather.model;

import android.content.Context;
import android.support.annotation.Nullable;

import com.mad.simpleweather.App;
import com.mad.simpleweather.R;
import com.mad.simpleweather.model.api.ApiInterface;
import com.mad.simpleweather.model.data.CityItem;
import com.mad.simpleweather.model.data.CityWeather;
import com.mad.simpleweather.model.storage.Database;

import java.util.ArrayList;
import java.util.List;

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
    public Observable<Boolean> updateWeather() {
        return Observable.just(null).map(o -> {
            StringBuilder str = new StringBuilder();
            for (int city : mContext.getResources().getIntArray(R.array.cities_id)) {
                str.append(city).append(",");
            }
            str.deleteCharAt(str.length() - 1);
            return str.toString();
        }).flatMap(s -> mApi.getWeather("metric", "366a16719c97c87cb1b3d62d04f1b0b4", s)).map(asd -> {
            if (!asd.isSuccessful()) {
                return false;
            }
            mDatabase.setWeather(asd.body());
            return true;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<CityItem>> getCityList() {
        return Observable.zip(Observable.just(mContext.getResources().getStringArray(R.array.cities)),
                Observable.just(mContext.getResources().getIntArray(R.array.cities_id)), (strings, ints) -> {
                    List<CityItem> items = new ArrayList<>();
                    for (int i = 0; i < strings.length; i++) {
                        items.add(new CityItem(strings[i], ints[i]));
                    }
                    return items;
                });
    }

}
