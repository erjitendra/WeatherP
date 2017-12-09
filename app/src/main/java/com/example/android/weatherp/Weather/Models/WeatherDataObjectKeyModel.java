package com.example.android.weatherp.Weather.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class WeatherDataObjectKeyModel implements Serializable {

    @SerializedName("time")
    private Long Time;

    @SerializedName("icon")
    private String Icon;


    @SerializedName("data")
    private ArrayList<WeatherDataArrayKeysModel> Data;





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


    public ArrayList<WeatherDataArrayKeysModel> getData() {
        return Data;
    }

    public void setData(ArrayList<WeatherDataArrayKeysModel> data) {
        Data = data;
    }
}
