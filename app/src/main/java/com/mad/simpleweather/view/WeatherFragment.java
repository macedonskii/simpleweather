package com.mad.simpleweather.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mad.simpleweather.R;
import com.mad.simpleweather.databinding.FragmentWeatherBinding;
import com.mad.simpleweather.model.api.response.Weather;
import com.mad.simpleweather.model.data.CityWeather;
import com.mad.simpleweather.presenter.AbstractPresenter;
import com.mad.simpleweather.presenter.WeatherPresenter;
import com.mad.simpleweather.view.view.WeatherView;

public class WeatherFragment extends AbstractFragment implements WeatherView {

    private FragmentWeatherBinding mBinding;
    private WeatherPresenter mPresenter = new WeatherPresenter(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false);
        mBinding.btnReload.setOnClickListener(mPresenter::onClickBtn);
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void setWeather(CityWeather cityWeather) {
        if (cityWeather == null) {
            mBinding.btnReload.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), R.string.error_weather_update, Toast.LENGTH_LONG).show();
            return;
        }
        mBinding.btnReload.setVisibility(View.INVISIBLE);
        mBinding.tvDescription.setText(cityWeather.getDescription());
        mBinding.tvTemperatureMain.setText(cityWeather.getTemperatureAverage());
        mBinding.tvMax.setText(cityWeather.getTemperatureMax());
        mBinding.tvTemperatureMin.setText(cityWeather.getTemperatureMin());
        Glide.with(this).load(cityWeather.getIcon()).into(mBinding.ivIcon);
    }

    @Override
    public AbstractPresenter getPresenter() {
        return mPresenter;
    }
}
