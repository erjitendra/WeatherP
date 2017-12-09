package com.example.android.weatherp.Weather;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.android.weatherp.R;
import com.example.android.weatherp.Weather.Models.WeatherApiResponseModel;

public class WeatherMainActivity extends AppCompatActivity {

    public TextView time, summary, humidity, avgTempreature, address;
    public TextView sunriseTime, sunsetTime, maxTempreature, minTempreature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle b = getIntent().getExtras();
        String DynamicLatitude = b.getString("latitude");
        String DynamicLongitude = b.getString("longitude");
        String DynamicTimeStamp = b.getString("timestamp");
        final String DynamicAddress = b.getString("address");


        //Progress Dialogue
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading WeatherDataArrayKeysModel....");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        // Fetch
        FetchWeatherData fetcher = new FetchWeatherData();
        WeatherApiResponseModel data = fetcher.fetch(DynamicLatitude, DynamicLongitude, DynamicTimeStamp);

        // Parse
        ParseWeatherData parser = new ParseWeatherData(data);
        ParsedOutput parsedOutput = parser.parse();

        progressDialog.dismiss();


        Log.v("Done", "" + data);
        setOutputInUI(DynamicAddress, parsedOutput);


    }

    private void setOutputInUI(String dynamicAddress, ParsedOutput parsedOutput) {
        // Get views by ids
        time = findViewById(R.id.currentTime);
        summary = findViewById(R.id.Summary);

        humidity = findViewById(R.id.Humidity);
        avgTempreature = findViewById(R.id.AvgTempreature);
        sunriseTime = findViewById(R.id.SunriseTime);
        sunsetTime = findViewById(R.id.SunsetTime);
        maxTempreature = findViewById(R.id.MaxTempreature);
        minTempreature = findViewById(R.id.MinTempreature);
        address = findViewById(R.id.address);

        // set Vales for views
        time.setText(parsedOutput.getSelectedDate());
        summary.setText(parsedOutput.getSummary());
        humidity.setText(String.valueOf(parsedOutput.getHumidity()));
        avgTempreature.setText(String.valueOf(parsedOutput.getTemperatureMean()));
        maxTempreature.setText(String.valueOf(parsedOutput.getTemperatureMax()));
        minTempreature.setText(String.valueOf(parsedOutput.getTemperatureMin()));
        address.setText(dynamicAddress);
        sunriseTime.setText(parsedOutput.getSunriseTime());
        sunsetTime.setText(parsedOutput.getSunsetTime());
    }
}
