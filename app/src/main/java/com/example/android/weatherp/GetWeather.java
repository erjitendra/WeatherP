package com.example.android.weatherp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.weatherp.Weather.WeatherMainActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetWeather extends AppCompatActivity {


    Date date1;
    EditText timestamp;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_get);

        Button button = findViewById(R.id.buttonhit);
        TextView desiredAddress = findViewById(R.id.desiredAddress);
        final EditText latitude = findViewById(R.id.etLatitude);
        final EditText longitude = findViewById(R.id.etLongitude);
        timestamp = findViewById(R.id.etTimeStamp);

        Bundle b = getIntent().getExtras();

        latitude.setText(b.getString("latitude"));
        longitude.setText(b.getString("longitude"));
        desiredAddress.setText(b.getString("address"));

        dateView = (TextView) findViewById(R.id.desiredDate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchAndPopulateWeather(latitude, longitude);
            }
        });



    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

        setTimestamp(year, month, day);


    }

    private void setTimestamp(int year, int month, int day) {
        String str_date = String.valueOf(new StringBuilder().append(day).append("/")
                .append(month + 1).append("/").append(year));
        Log.v("Date17", "" + str_date);
        DateFormat formatter;

        formatter = new SimpleDateFormat("dd/MM/yyyy");


        try {
            date1 = (Date) formatter.parse(str_date);
            Log.v("Date13", "" + date1.getTime());

            timestamp.setText(String.valueOf(date1.getTime() / 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void fetchAndPopulateWeather(EditText latitude, EditText longitude) {
        Intent intent = new Intent(getApplicationContext(), WeatherMainActivity.class);
        Bundle b = new Bundle();
        b.putString("latitude", latitude.getText().toString());
        b.putString("longitude", longitude.getText().toString());
        b.putString("timestamp", timestamp.getText().toString());
        intent.putExtras(b);
        startActivity(intent);
    }


}
