package com.example.android.weatherp.Weather;

import android.util.Log;

import com.example.android.weatherp.Weather.Models.WeatherApiResponseModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jindal on 12/9/2017.
 */

public class ParseWeatherData {

    WeatherApiResponseModel fetchedData;
    CalculateDailyData calculator = new CalculateDailyData();

    public ParseWeatherData(WeatherApiResponseModel fetchedData) {
        this.fetchedData = fetchedData;
    }

    public ParsedOutput parse() {

        ParsedOutput output = new ParsedOutput();

        Log.v("WeatherApp", "Working on summary and humisity");
        output.setSummary(summary());
        output.setHumidity(humidity());

        Log.v("WeatherApp", "Working on temps");
        output.setTemperatureMean(getTempMean());
        output.setTemperatureMin(getTempMin());
        output.setTemperatureMax(getTempMax());

        Log.v("WeatherApp", "Working on sunrise and sunset");
        output.setSunriseTime(getSunrise());
        output.setSunsetTime(getSunset());

        Log.v("WeatherApp", "working on selected date");
        output.setSelectedDate(getselectedDate());

        return output;

    }

    public String summary() {
        return fetchedData.getDaily().getData().get(0).getSummary();
    }

    public Double humidity() {
        return fetchedData.getDaily().getData().get(0).getHumidity() * 100;

    }


    public Double getTempMax() {
        return calculator.maxTemp(fetchedData.getHourly().getData());
    }

    public Double getTempMin() {
        return calculator.minTemp(fetchedData.getHourly().getData());
    }

    public Double getTempMean() {
        return calculator.meanTemp(fetchedData.getHourly().getData());
    }

    public String getselectedDate() {
        Date sunriseTimeStamp = new Date(fetchedData.getDaily().getData().get(0).getSunriseTime() * 1000);
        return formatDate(sunriseTimeStamp);
    }


    public String getSunrise() {
        Date sunriseTimeStamp = new Date(fetchedData.getDaily().getData().get(0).getSunriseTime() * 1000);
        return formatTime(sunriseTimeStamp);
    }

    public String getSunset() {
        Date sunsetTimeStamp = new Date(fetchedData.getDaily().getData().get(0).getSunsetTime() * 1000);
        return formatTime(sunsetTimeStamp);
    }

    private String formatDate(Date timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(timestamp);
    }

    private String formatTime(Date timestamp) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(timestamp);
    }


}
