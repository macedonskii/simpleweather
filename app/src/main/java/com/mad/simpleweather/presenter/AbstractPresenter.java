package com.mad.simpleweather.presenter;


import android.content.Context;

import com.mad.simpleweather.App;
import com.mad.simpleweather.model.Model;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public abstract class AbstractPresenter implements Presenter{
    CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    @Inject
    Model mModel;
    @Inject
    Context mContext;

    public AbstractPresenter() {
        App.getGraph().inject(this);
    }

    @Override
    public void onStop(){
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
    }


}
