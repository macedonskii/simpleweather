package com.mad.simpleweather.presenter;


import android.view.View;

import com.mad.simpleweather.WeatherUpdaterService;
import com.mad.simpleweather.view.view.WeatherView;

import rx.Subscription;

public class WeatherPresenter extends AbstractPresenter {

    private final WeatherView mView;

    public WeatherPresenter(WeatherView view) {
        mView = view;
    }

    public void onResume() {
        Subscription subscribe = mModel.getWeather().subscribe(mView::setWeather, mView::onError);
        mCompositeSubscription.add(subscribe);
    }

    public void onClickBtn(View view) {
        view.setVisibility(View.INVISIBLE);
        WeatherUpdaterService.startService(mContext, true);
    }
}
