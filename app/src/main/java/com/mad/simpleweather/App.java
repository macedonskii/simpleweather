package com.mad.simpleweather;

import android.app.Application;
import android.content.Context;

import com.mad.simpleweather.model.storage.DatabaseImpl;
import com.mad.simpleweather.other.AppComponents;
import com.mad.simpleweather.other.DaggerAppComponents;
import com.mad.simpleweather.other.ModelModule;

/**
 * Created by mad on 21.05.2017.
 */

public class App extends Application {

    private static Context sContext;

    private static AppComponents sGraph;

    public static AppComponents getGraph() {
        return sGraph;
    }

    public static Context getContext(){
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sGraph = DaggerAppComponents.builder().modelModule(new ModelModule(this)).build();
        sContext = this;
    }
}
