package com.mad.simpleweather.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.view.View;

import com.mad.simpleweather.R;
import com.mad.simpleweather.databinding.FragmentSettingsBinding;
import com.mad.simpleweather.model.data.CityListItem;
import com.mad.simpleweather.presenter.AbstractPresenter;
import com.mad.simpleweather.presenter.SettingsPresenter;
import com.mad.simpleweather.presenter.adapters.SettingsAdapter;
import com.mad.simpleweather.view.view.SettingsView;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends AbstractFragment implements SettingsView {


    private FragmentSettingsBinding mBinding;
    private ActivityCallback mCallback;
    private SettingsAdapter mAdapter;

    private SettingsPresenter mPresenter = new SettingsPresenter(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mAdapter = new SettingsAdapter(new ArrayList<>(), getActivity(), mPresenter);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        mBinding.recyclerView.setAdapter(mAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));

        mPresenter.onCreateView();
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCallback = (ActivityCallback)getActivity();
    }

    @Override
    public void onDataSaved() {
        mCallback.closeSettingsFragment();
    }

    @Override
    public void setList(List<CityListItem> items) {
        mAdapter.setItems(items);
    }

    @Override
    public AbstractPresenter getPresenter() {
        return mPresenter;
    }
}
