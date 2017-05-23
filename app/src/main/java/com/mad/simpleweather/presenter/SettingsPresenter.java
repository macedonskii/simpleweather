package com.mad.simpleweather.presenter;


import com.mad.simpleweather.R;
import com.mad.simpleweather.model.data.CityItem;
import com.mad.simpleweather.presenter.adapters.SettingsAdapter;
import com.mad.simpleweather.view.view.SettingsView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

public class SettingsPresenter extends AbstractPresenter implements SettingsAdapter.SettingsClickListener {
    private final SettingsView mView;


    public SettingsPresenter(SettingsView view) {
        mView = view;
    }

    @Override
    public void onClick(CityItem item) {
        Subscription subscribe = mModel.setCurrentWeather(item.getId()).subscribe(aBoolean -> mView.onDataSaved(), mView::onError);
        mCompositeSubscription.add(subscribe);
    }

    public void onCreateView() {
        Subscription subscribe = mModel.getCityList().subscribe(mView::setList, mView::onError);
        mCompositeSubscription.add(subscribe);
    }
}
