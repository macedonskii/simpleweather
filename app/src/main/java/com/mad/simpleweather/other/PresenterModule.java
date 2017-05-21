package com.mad.simpleweather.other;


import com.mad.simpleweather.model.Model;
import com.mad.simpleweather.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    @Singleton
    public Model provideModel(){
        return new ModelImpl();
    }
}
