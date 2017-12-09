package com.example.android.weatherp.Weather;


import com.example.android.weatherp.Weather.Models.WeatherApiResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherClient {

    @GET
    Call<WeatherApiResponseModel> fetchProducts(@Url String url);
}

