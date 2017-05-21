package com.mad.simpleweather.view.view;

import com.mad.simpleweather.model.data.CityListItem;

import java.util.List;

public interface SettingsView {

    void onDataSaved();

    void setList(List<CityListItem> items);

}
