package com.example.android.weatherp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WeatherGet extends AppCompatActivity {
private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_get);
        Button button=findViewById(R.id.buttonhit);
        final TextView latitude=findViewById(R.id.etLatitude);
        final TextView longitude=findViewById(R.id.etLongitude);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Bundle b=new Bundle();
                b.putString("latitude",latitude.getText().toString());
                b.putString("longitude",longitude.getText().toString());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
