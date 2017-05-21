package com.mad.simpleweather.presenter;


import com.mad.simpleweather.App;
import com.mad.simpleweather.model.AppModel;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public abstract class AbstractPresenter {
    CompositeSubscription mSubscription = new CompositeSubscription();
    @Inject
    AppModel mAppModel;

    public AbstractPresenter() {
        App.getGraph().inject(this);
    }

    public void onStop(){
        if (mSubscription != null) {
            mSubscription.clear();
        }
    }
}
