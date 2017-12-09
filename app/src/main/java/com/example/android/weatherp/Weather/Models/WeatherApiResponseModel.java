package com.example.android.weatherp.Weather.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jindal on 11/24/2017.
 */

public class WeatherApiResponseModel implements Serializable {
    @SerializedName("daily")
    private WeatherDataObjectKeyModel Daily;
    @SerializedName("hourly")
    private WeatherDataObjectKeyModel Hourly;
    @SerializedName("currently")
    private WeatherDataObjectKeyModel currently;

    public WeatherDataObjectKeyModel getDaily() {
        return Daily;
    }

    public void setDaily(WeatherDataObjectKeyModel daily) {
        Daily = daily;
    }

    public WeatherDataObjectKeyModel getHourly() {
        return Hourly;
    }

    public void setHourly(WeatherDataObjectKeyModel hourly) {
        Hourly = hourly;
    }

    public WeatherDataObjectKeyModel getCurrently() {
        return currently;
    }

    public void setCurrently1(WeatherDataObjectKeyModel currently) {
        this.currently = currently;
    }


}
