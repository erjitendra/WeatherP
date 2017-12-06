package com.example.android.weatherp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class WeatherData implements Serializable {

    @SerializedName("time")
    private Long Time;
    @SerializedName("summary")
    private String Summary;
    @SerializedName("icon")
    private String Icon;

    @SerializedName("temperature")
    private Double Temperature;
    @SerializedName("humidity")
    private Double Humidity;
    @SerializedName("data")
    private ArrayList<WeatherDataArrayKeys> Data;

    public Double getTemperature() {
        return Temperature;
    }

    public void setTemperature(Double temperature) {
        Temperature = temperature;
    }

    public Double getHumidity() {
        return Humidity;
    }

    public void setHumidity(Double humidity) {
        Humidity = humidity;
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


    public ArrayList<WeatherDataArrayKeys> getData() {
        return Data;
    }

    public void setData(ArrayList<WeatherDataArrayKeys> data) {
        Data = data;
    }
}
