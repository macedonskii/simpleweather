package com.mad.simpleweather;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;

import com.mad.simpleweather.model.storage.DatabaseImpl;

public class WeatherUpdaterService extends Service {

    public static void startService(Context context, boolean isImmediately){
        Intent intent = new Intent(context, WeatherUpdaterService.class);
        intent.putExtra("",isImmediately);
        context.startService(intent);
    }

    public WeatherUpdaterService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
