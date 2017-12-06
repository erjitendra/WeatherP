package com.example.android.weatherp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jindal on 11/24/2017.
 */

public class WeatherModel implements Serializable {
    @SerializedName("daily")
    private WeatherData Daily;
    @SerializedName("hourly")
    private WeatherData Hourly;
    @SerializedName("currently")
    private WeatherData currently;

    public WeatherData getDaily() {
        return Daily;
    }

    public void setDaily(WeatherData daily) {
        Daily = daily;
    }

    public WeatherData getHourly() {
        return Hourly;
    }

    public void setHourly(WeatherData hourly) {
        Hourly = hourly;
    }

    public WeatherData getCurrently() {
        return currently;
    }

    public void setCurrently1(WeatherData currently) {
        this.currently = currently;
    }


}
