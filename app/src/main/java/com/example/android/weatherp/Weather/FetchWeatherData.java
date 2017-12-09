package com.example.android.weatherp.Weather;

import android.util.Log;

import com.example.android.weatherp.R;
import com.example.android.weatherp.Weather.Models.WeatherApiResponseModel;
import com.example.android.weatherp.WeatherClient;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jindal on 12/9/2017.
 */

public class FetchWeatherData {

    public static String BASE_URL = "https://api.darksky.net/forecast/";
    WeatherApiResponseModel data = new WeatherApiResponseModel();

    public void fetch(String latitude, String longitude, String timestamp) {
        String ApiDynamicUrl = constructUrl(latitude, longitude, timestamp);
        callApi(ApiDynamicUrl);

    }

    public String constructUrl(String latitude, String longitude, String timestamp) {
        String DynamicUrl = "fcc784e094a6fa1ab4d7d1a0c39b84c5/" + latitude + "," + longitude + "," + timestamp + "?units=si";
        Log.v("Done", "" + BASE_URL + DynamicUrl);
        return DynamicUrl;
    }

    public WeatherApiResponseModel callApi(String ApiDynamicUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        WeatherClient client = retrofit.create(WeatherClient.class);
        Call<WeatherApiResponseModel> call = client.fetchProducts(ApiDynamicUrl);
        call.enqueue(new Callback<WeatherApiResponseModel>() {
            @Override
            public void onResponse(Call<WeatherApiResponseModel> call, Response<WeatherApiResponseModel> response) {

                data = response.body();


            }

            @Override
            public void onFailure(Call<WeatherApiResponseModel> call, Throwable t) {

            }
        });

        return data;

    }
}
