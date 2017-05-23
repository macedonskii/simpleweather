package com.mad.simpleweather.presenter;


import android.content.IntentFilter;
import android.view.View;

import com.mad.simpleweather.WeatherUpdateReceiver;
import com.mad.simpleweather.WeatherUpdaterService;
import com.mad.simpleweather.view.view.WeatherView;

import rx.Subscription;

public class WeatherPresenter extends AbstractPresenter implements WeatherUpdateReceiver.Callback {

    private final WeatherView mView;
    private WeatherUpdateReceiver mReceiver = new WeatherUpdateReceiver(this);

    public WeatherPresenter(WeatherView view) {
        mView = view;
    }

    public void onResume() {
        mContext.registerReceiver(mReceiver, new IntentFilter(WeatherUpdateReceiver.class.getName()));
        updateWeather();
    }

    public void onClickBtn(View view) {
        view.setVisibility(View.INVISIBLE);
        WeatherUpdaterService.startService(mContext);
    }

    public void onPause() {
        mContext.unregisterReceiver(mReceiver);
    }

    @Override
    public void onReceive() {
        updateWeather();
    }

    private void updateWeather() {
        Subscription subscribe = mModel.getWeather().subscribe(mView::setWeather, mView::onError);
        mCompositeSubscription.add(subscribe);
    }
}
