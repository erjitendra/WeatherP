package com.example.android.weatherp.Weather;

import android.util.Log;

/**
 * Created by Jindal on 12/9/2017.
 */

public class FetchHelperWeatherData {

    public String constructUrl(String latitude, String longitude, String timestamp) {
        String DynamicUrl = "fcc784e094a6fa1ab4d7d1a0c39b84c5/" + latitude + "," + longitude + "," + timestamp + "?units=si";
        Log.v("WeatherApp", "Fetching URL: " + WeatherConstants.WEATHER_API_BASE_URL + DynamicUrl);
        return DynamicUrl;
    }

}
