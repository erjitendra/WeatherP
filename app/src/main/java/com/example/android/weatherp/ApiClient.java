package com.example.android.weatherp;




import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiClient  {

    @GET
    Call<WeatherModel>  fetchProducts(@Url String url);
}

