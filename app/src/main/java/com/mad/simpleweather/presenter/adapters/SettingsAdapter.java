package com.mad.simpleweather.presenter.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mad.simpleweather.R;
import com.mad.simpleweather.model.data.CityItem;

import java.util.ArrayList;
import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsHolder> {

    private List<CityItem> mItems = new ArrayList<>();
    private Context mContext;
    private SettingsClickListener mListener;

    public SettingsAdapter(@NonNull List<CityItem> item, @NonNull Context context, @NonNull SettingsClickListener listener) {
        this.mItems = item;
        mContext = context;
        mListener = listener;
    }

    @Override
    public SettingsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SettingsHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list_settings, parent, false));
    }

    @Override
    public void onBindViewHolder(SettingsHolder holder, int position) {
        holder.root.setOnClickListener(v -> mListener.onClick(mItems.get(holder.getAdapterPosition())));
        holder.root.setText(mItems.get(position).getCityName());
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public void setItems(@NonNull List<CityItem> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    protected class SettingsHolder extends RecyclerView.ViewHolder {
        TextView root;

        SettingsHolder(View itemView) {
            super(itemView);
            root = (TextView) itemView.findViewById(R.id.root);
        }
    }

    public interface SettingsClickListener {
        void onClick(CityItem item);
    }
}
