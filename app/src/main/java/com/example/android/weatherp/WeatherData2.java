package com.example.android.weatherp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jindal on 11/29/2017.
 */

public class WeatherData2 {
    @SerializedName("sunriseTime")
    private long SunriseTime;
    @SerializedName("sunsetTime")
    private long SunsetTime;
    @SerializedName("temperature")
    private double Tempreture;

    public long getSunriseTime() {
        return SunriseTime;
    }

    public void setSunriseTime(long sunriseTime) {
        SunriseTime = sunriseTime;
    }

    public long getSunsetTime() {
        return SunsetTime;
    }

    public void setSunsetTime(long sunsetTime) {
        SunsetTime = sunsetTime;
    }

    public double getTempreture() {
        return Tempreture;
    }

    public void setTempreture(double tempreture) {
        Tempreture = tempreture;
    }
}
