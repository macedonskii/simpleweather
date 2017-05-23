package com.mad.simpleweather;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

public class WeatherUpdateReceiver extends BroadcastReceiver {

    private final String TAG = getClass().getSimpleName();

    private Callback mCallback;

    public WeatherUpdateReceiver(@NonNull Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() called with: context = [" + context + "], intent = [" + intent + "]");
        mCallback.onReceive();
    }

    public interface Callback{
        void onReceive();
    }
}
