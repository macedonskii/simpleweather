package com.mad.simpleweather;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.mad.simpleweather.model.Model;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class WeatherUpdaterService extends Service {

    // TODO: 21.05.2017 Restart service && broadcasts

    private final String TAG = getClass().getSimpleName();
    public static void startService(Context context, boolean isImmediately){
        Intent intent = new Intent(context, WeatherUpdaterService.class);
        intent.putExtra("",isImmediately);
        context.startService(intent);
    }
    @Inject
    Model mModel;

    CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public WeatherUpdaterService() {
        App.getGraph().inject(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Subscription subscribe = mModel.updateWeather(true).subscribe(aLong -> {
            Log.d(TAG, "updateWeather() called " + aLong);
        }, Throwable::printStackTrace);
        mCompositeSubscription.add(subscribe);

    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.clear();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
