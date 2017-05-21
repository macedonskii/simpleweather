package com.mad.simpleweather.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.mad.simpleweather.R;

public class MainActivity extends AppCompatActivity implements ActivityCallback {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (savedInstanceState == null)
            setFragment(new WeatherFragment(), false);

    }

    private void setFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.replace(R.id.root, fragment).addToBackStack(null);
        } else {
            fragmentTransaction.add(R.id.root, fragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void closeSettingsFragment() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void openSettingsFragment() {
        setFragment(new SettingsFragment(), true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: ");
        if (item.getItemId() == R.id.btnSettings) {
            openSettingsFragment();
        }
        if (item.getItemId() == R.id.btnClose) {
            closeSettingsFragment();
        }
        invalidateOptionsMenu();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d(TAG, "onPrepareOptionsMenu: ");
        boolean visible = getSupportFragmentManager().getBackStackEntryCount() == 1;
        menu.getItem(1).setVisible(visible);
        menu.getItem(0).setVisible(!visible);
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        invalidateOptionsMenu();
    }
}
