package com.example.android.weatherp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static String BASE_URL = "https://api.darksky.net/forecast/";
    public TextView time, summary, tempreature, visibility, avgTempreature, sunriseTime, sunsetTime;
    WeatherModel products = new WeatherModel();
    Date dateObject, SunriseTimeStamp, SunsetTimeStamp;
    CalculateDailyData calculateDailyData=new CalculateDailyData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dynamic Url
        Bundle b = getIntent().getExtras();
        String DynamicLatitude = b.getString("latitude");
        String DynamicLongitude = b.getString("longitude");
        String DynamicTimeStamp = b.getString("timestamp");
        final String DynamicUrl = "fcc784e094a6fa1ab4d7d1a0c39b84c5/" + DynamicLatitude + "," + DynamicLongitude + "," + DynamicTimeStamp + "?units=si";
Log.v("Url",""+BASE_URL+DynamicUrl);
        //...........................................

        //Progress Dialogue
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading WeatherData2....");
        //progressDialog;
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        //......................................................


        //Retrofit  Api Call

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiClient client = retrofit.create(ApiClient.class);
        Call<WeatherModel> call = client.fetchProducts(DynamicUrl);

        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, retrofit2.Response<WeatherModel> response) {
                progressDialog.dismiss();
                 products = response.body();
                 Log.v("Pune",""+products);

                time = findViewById(R.id.currentTime);
                summary = findViewById(R.id.Summary);

                tempreature = findViewById(R.id.Tempreature);
                visibility = findViewById(R.id.Visibility);
                avgTempreature = findViewById(R.id.AvgTempreature);
                sunriseTime = findViewById(R.id.SunriseTime);
                sunsetTime = findViewById(R.id.SunsetTime);

//                Double TampretureAtPosition = 0.0;
//
//                for (int i = 0; i < products.getHourly().getData().size(); i++) {
//
//                    TampretureAtPosition += products.getHourly().getData().get(i).getTempreture();
//
//                }
//                Double TempreatureMean = TampretureAtPosition / (products.getHourly().getData().size() - 1);
//                Double d = TempreatureMean;
//


               // For Change Time in current Date time form TimeStamp

                dateObject = new Date(products.getCurrently().getTime() * 1000);
                String formattedDate = formatDate(dateObject);
                String formattedTime = formatTime(dateObject);
                time.setText(formattedDate + "   " + formattedTime);


                //******************************

                Integer tempmeanInt = calculateDailyData.meanTemp(products.getHourly().getData());
                Log.v("temp",""+tempmeanInt);


                summary.setText("Summary: " + products.getCurrently().getSummary());
                tempreature.setText("Tempreature: " + Double.toString(products.getCurrently().getTemperature()));
                Log.v("Pune",""+Double.toString(products.getCurrently().getVisibility()));
                visibility.setText("PrecipIntensity: " + Double.toString(products.getCurrently().getVisibility()));
                avgTempreature.setText("Tempreature Mean Of the Day: " + tempmeanInt);
                //...........................................
                SunriseTimeStamp = new Date(products.getDaily().getData().get(0).getSunriseTime() * 1000);
                SunsetTimeStamp = new Date(products.getDaily().getData().get(0).getSunsetTime() * 1000);
                String formattedSunriseTime = formatTime(SunriseTimeStamp);
                String formattedSunsetTime = formatTime(SunsetTimeStamp);
                sunriseTime.setText("Sunrise At:" + formattedSunriseTime);
                sunsetTime.setText("Sunset At:" + formattedSunsetTime);

                //...............................................


            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {

                Toast.makeText(getBaseContext(), "Sorry, Product listing failed", Toast.LENGTH_SHORT).show();

            }
        });

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
