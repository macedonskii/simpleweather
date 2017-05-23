package com.mad.simpleweather;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.mad.simpleweather.model.Model;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class WeatherUpdaterService extends Service {

    private final String TAG = getClass().getSimpleName();

    public static void startService(Context context) {
        Intent intent = new Intent(context, WeatherUpdaterService.class);
        context.startService(intent);
    }

    @Inject
    Model mModel;
    CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public WeatherUpdaterService() {
        App.getGraph().inject(this);
        Log.d(TAG, "WeatherUpdaterService: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        Subscription subscribe = mModel.updateWeather().subscribe(aLong -> {
            Log.d(TAG, "updateWeather() called " + aLong);
            if (aLong) {
                sendBroadcast(new Intent(WeatherUpdateReceiver.class.getName()));
            }else{
                onError();
            }
            stopSelf();
        }, throwable -> {
            throwable.printStackTrace();
            onError();
        });
        mCompositeSubscription.add(subscribe);
    }

    private void onError() {
        Toast.makeText(this, R.string.error_weather_update_service, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        mCompositeSubscription.clear();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
