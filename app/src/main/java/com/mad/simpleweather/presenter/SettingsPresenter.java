package com.mad.simpleweather.presenter;


import com.mad.simpleweather.App;
import com.mad.simpleweather.R;
import com.mad.simpleweather.model.data.CityListItem;
import com.mad.simpleweather.presenter.adapters.SettingsAdapter;
import com.mad.simpleweather.view.SettingsFragment;
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
    public void onClick(CityListItem item) {
        Subscription subscribe = mModel.setCurrentWeather(item.getId()).subscribe(aBoolean -> mView.onDataSaved(), mView::onError);
        mCompositeSubscription.add(subscribe);
    }

    public void onCreateView() {
        int[] intArray = App.getContext().getResources().getIntArray(R.array.cities_id);
        String[] stringArray = App.getContext().getResources().getStringArray(R.array.cities);
        List<CityListItem> items = new ArrayList<>();
        for (int i = 0; i < intArray.length; i++) {
            items.add(new CityListItem(stringArray[i],intArray[i]));
        }
        mView.setList(items);
    }
}
