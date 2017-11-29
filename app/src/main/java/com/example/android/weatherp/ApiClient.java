package com.example.android.weatherp;




import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiClient  {

    @GET
    Call<Model>  fetchProducts(@Url String url);
}

