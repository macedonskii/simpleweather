package com.mad.simpleweather.model.data.containers;


import android.support.annotation.Nullable;

import com.mad.simpleweather.model.api.response.Data;

import java.util.ArrayList;
import java.util.List;

public class WeathersContainer {

    private List<Data> mList = new ArrayList<>();
    //TODO change to 1 ID
    private int position = 0;

    public List<Data> getList() {
        return mList;
    }

    public int getPosition() {
        return position;
    }

    public void setList(List<Data> list) {
        mList = list;
    }

    @Nullable
    public Data getCurrentWeather(){
        for (Data data : mList) {
            if (data.getId() == position) {
                return data;
            }
        }
        return null;
    }

    public void setId(int id) {
        position = id;
    }
}
