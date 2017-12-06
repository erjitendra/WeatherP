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

public class WeatherActivity extends AppCompatActivity {

    public static String BASE_URL = "https://api.darksky.net/forecast/";
    public TextView time, summary, tempreature, humidity, avgTempreature, address;
    public TextView sunriseTime, sunsetTime, maxTempreature, minTempreature;
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
        final String DynamicAddress = b.getString("address");
        final String DynamicUrl = "fcc784e094a6fa1ab4d7d1a0c39b84c5/" + DynamicLatitude + "," + DynamicLongitude + "," + DynamicTimeStamp + "?units=si";
        Log.v("Done", "" + BASE_URL + DynamicUrl);


        //Progress Dialogue
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading WeatherDataArrayKeys....");

        progressDialog.setIndeterminate(true);
        progressDialog.show();




        //Retrofit  Api Call

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        WeatherClient client = retrofit.create(WeatherClient.class);
        Call<WeatherModel> call = client.fetchProducts(DynamicUrl);

        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, retrofit2.Response<WeatherModel> response) {
                progressDialog.dismiss();
                 products = response.body();
                Log.v("Done", "" + products);

                time = findViewById(R.id.currentTime);
                summary = findViewById(R.id.Summary);


                humidity = findViewById(R.id.Humidity);
                avgTempreature = findViewById(R.id.AvgTempreature);
                sunriseTime = findViewById(R.id.SunriseTime);
                sunsetTime = findViewById(R.id.SunsetTime);
                maxTempreature = findViewById(R.id.MaxTempreature);
                minTempreature = findViewById(R.id.MinTempreature);
                address = findViewById(R.id.address);


                // For Change Time in current Date time form TimeStamp

                dateObject = new Date(products.getCurrently().getTime() * 1000);
                String formattedDate = formatDate(dateObject);
                String formattedTime = formatTime(dateObject);
                time.setText(formattedDate);
                Log.v("Done", "Date" + formattedDate);




                Integer tempmeanInt = calculateDailyData.meanTemp(products.getHourly().getData());
                Double tempMax = calculateDailyData.maxTemp(products.getHourly().getData());
                Double tempMin = calculateDailyData.minTemp(products.getHourly().getData());
//                Log.v("Done",""+tempMax+"/"+tempMin+"/"+tempmeanInt);
//                Log.v("Done","Summary"+products.getCurrently().getSummary());
//                Log.v("Done","Temp"+Double.toString(products.getCurrently().getTemperature()));
//
//                Log.v("Done","Address"+DynamicAddress);
//                Log.v("Done","Sunrise Ts"+products.getDaily().getData().get(0).getSunriseTime() * 1000);


                summary.setText(products.getDaily().getData().get(0).getSummary());

                // Log.v("Done","Humidity"+Double.toString(products.getCurrently().getHumidity()));
                humidity.setText(Double.toString(products.getDaily().getData().get(0).getHumidity() * 100));
                avgTempreature.setText(Integer.toString(tempmeanInt));
                maxTempreature.setText(Double.toString(tempMax));
                minTempreature.setText(Double.toString(tempMin));
                address.setText(DynamicAddress);
                //...........................................
                SunriseTimeStamp = new Date(products.getDaily().getData().get(0).getSunriseTime() * 1000);
                Log.v("ABCD", "" + SunriseTimeStamp);
                SunsetTimeStamp = new Date(products.getDaily().getData().get(0).getSunsetTime() * 1000);
                String formattedSunriseTime = formatTime(SunriseTimeStamp);
                String formattedSunsetTime = formatTime(SunsetTimeStamp);
                sunriseTime.setText(formattedSunriseTime);
                sunsetTime.setText(formattedSunsetTime);
                Log.v("Found", "SR" + formattedSunriseTime);

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
