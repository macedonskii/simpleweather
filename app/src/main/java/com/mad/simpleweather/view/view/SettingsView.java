package com.mad.simpleweather.view.view;

import com.mad.simpleweather.model.data.CityItem;

import java.util.List;

public interface SettingsView extends View {

    void onDataSaved();

    void setList(List<CityItem> items);

}
