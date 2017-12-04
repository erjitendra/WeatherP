package com.example.android.weatherp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;



public class WeatherData1 implements Serializable {

    @SerializedName("time")
    private Long Time;
    @SerializedName("summary")
    private String Summary;
    @SerializedName("icon")
    private String Icon;

    @SerializedName("temperature")
    private Double Temperature;
    @SerializedName("humidity")
    private Double Visibility;
    @SerializedName("data")
    private ArrayList<WeatherData2> Data;

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


    public ArrayList<WeatherData2> getData() {
        return Data;
    }

    public void setData(ArrayList<WeatherData2> data) {
        Data = data;
    }
}
