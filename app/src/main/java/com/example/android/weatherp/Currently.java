package com.example.android.weatherp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jindal on 11/24/2017.
 */

public class Currently implements Serializable {
    @SerializedName("time")
    private Long Time;
    @SerializedName("summary")
    private String Summary;
    @SerializedName("icon")
    private String Icon;
    @SerializedName("temperature")
    private Double Temperature;
    @SerializedName("precipIntensity")
    private Double Visibility;

    public Double getTemperature() {
        return Temperature;
    }

    public void setTemperature(Double temperature) {
        Temperature = temperature;
    }

    public Double getVisibility() {
        return Visibility;
    }

    public void setVisibility(Double visibility) {
        Visibility = visibility;
    }

    public Long getTime() {
        return Time;
    }

    public void setTime(Long time) {
        Time = time;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }


}
