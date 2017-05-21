package com.mad.simpleweather.other;

import com.mad.simpleweather.WeatherUpdaterService;
import com.mad.simpleweather.model.ModelImpl;
import com.mad.simpleweather.presenter.AbstractPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PresenterModule.class, ModelModule.class})
public interface AppComponents {
    void inject(AbstractPresenter abstractPresenter);

    void inject(ModelImpl appModel);

    void inject(WeatherUpdaterService weatherUpdaterService);
}
