package com.mad.simpleweather.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;
import android.view.View;

import com.mad.simpleweather.R;
import com.mad.simpleweather.databinding.FragmentWeatherBinding;
import com.mad.simpleweather.model.api.response.Weather;
import com.mad.simpleweather.presenter.WeatherPresenter;
import com.mad.simpleweather.view.view.WeatherView;

public class WeatherFragment extends AbstractFragment implements WeatherView {

    private FragmentWeatherBinding mBinding;
    private WeatherPresenter mPresenter = new WeatherPresenter(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }
}
