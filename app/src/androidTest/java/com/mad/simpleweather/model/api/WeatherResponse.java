
package com.mad.simpleweather.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<com.mad.simpleweather.model.api.List> list = null;

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.mad.simpleweather.model.api.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.mad.simpleweather.model.api.List> list) {
        this.list = list;
    }

}
