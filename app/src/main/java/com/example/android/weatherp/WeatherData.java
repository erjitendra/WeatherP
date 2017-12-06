package com.example.android.weatherp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class WeatherData implements Serializable {

    @SerializedName("time")
    private Long Time;

    @SerializedName("icon")
    private String Icon;


    @SerializedName("data")
    private ArrayList<WeatherDataArrayKeys> Data;





    public Long getTime() {
        return Time;
    }

    public void setTime(Long time) {
        Time = time;
    }



    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }


    public ArrayList<WeatherDataArrayKeys> getData() {
        return Data;
    }

    public void setData(ArrayList<WeatherDataArrayKeys> data) {
        Data = data;
    }
}
