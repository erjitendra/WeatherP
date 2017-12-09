package com.example.android.weatherp.Weather.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jindal on 11/29/2017.
 */

public class WeatherDataArrayKeysModel {
    @SerializedName("sunriseTime")
    private long SunriseTime;
    @SerializedName("sunsetTime")
    private long SunsetTime;
    @SerializedName("temperature")
    private double Tempreture;
    @SerializedName("summary")
    private String Summary;
    @SerializedName("humidity")
    private Double Humidity;

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

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public Double getHumidity() {
        return Humidity;
    }

    public void setHumidity(Double humidity) {
        Humidity = humidity;
    }
}
