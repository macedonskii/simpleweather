package com.mad.simpleweather;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;

import com.mad.simpleweather.model.storage.DatabaseImpl;
import com.mad.simpleweather.other.AppComponents;
import com.mad.simpleweather.other.DaggerAppComponents;
import com.mad.simpleweather.other.ModelModule;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static AppComponents sGraph;

    public static AppComponents getGraph() {
        return sGraph;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sGraph = DaggerAppComponents.builder().modelModule(new ModelModule(this)).build();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(configuration);
        firstStart();
    }

    private void firstStart() {
        SharedPreferences appPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);
        if (appPreferences.getBoolean("first_start", true)) {
            AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            WeatherUpdaterService.startService(this);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            Intent intent = new Intent(this, AlarmReceiver.class);
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, intent, 0);

            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_HALF_HOUR, broadcast);
            appPreferences.edit().putBoolean("first_start", false).apply();
        }
    }
}
