package com.mad.simpleweather.other;


import android.content.Context;

import com.mad.simpleweather.model.api.ApiInterface;
import com.mad.simpleweather.model.api.ApiProvider;
import com.mad.simpleweather.model.storage.Database;
import com.mad.simpleweather.model.storage.DatabaseImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

    private Context mContext;

    public ModelModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public Database provideModel(){
        return new DatabaseImpl(mContext);
    }

    @Provides
    @Singleton
    public ApiInterface provideApi(){
        return ApiProvider.getApi();
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return mContext;
    }
}
