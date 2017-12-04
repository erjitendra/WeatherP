package com.example.android.weatherp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jindal on 11/24/2017.
 */

public class WeatherModel implements Serializable {
    @SerializedName("daily")
    private WeatherData1 Daily;
    @SerializedName("hourly")
    private WeatherData1 Hourly;
    @SerializedName("currently")
    private WeatherData1 currently;

    public WeatherData1 getDaily() {
        return Daily;
    }

    public void setDaily(WeatherData1 daily) {
        Daily = daily;
    }

    public WeatherData1 getHourly() {
        return Hourly;
    }

    public void setHourly(WeatherData1 hourly) {
        Hourly = hourly;
    }

    public WeatherData1 getCurrently() {
        return currently;
    }

    public void setCurrently1(WeatherData1 currently) {
        this.currently = currently;
    }


}
