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
    //private RecyclerView recyclerView;
    //private RecyclerView.Adapter adapter;
    public static String BASE_URL = "https://api.darksky.net/forecast/";
    public TextView time, summary, tempreature, visibility;
    Model products = new Model();
    Date dateObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dynamic Url
        Bundle b = getIntent().getExtras();
        Log.v("Hi", "" + b.getString("latitude"));
        String DynamicLatitude = b.getString("latitude");
        String DynamicLongitude = b.getString("longitude");
        final String DynamicUrl = "fcc784e094a6fa1ab4d7d1a0c39b84c5/" + DynamicLatitude + "," + DynamicLongitude+"?units=si";

        //...........................................

//Progress Dialogue
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data....");
        //progressDialog;
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        //......................................................


//Retrofeit  Api Call

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiClient client = retrofit.create(ApiClient.class);
        Call<Model> call = client.fetchProducts(DynamicUrl);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                progressDialog.dismiss();
                products = response.body();

                time = (TextView) findViewById(R.id.currentTime);
                summary = (TextView) findViewById(R.id.Summary);

                tempreature = (TextView) findViewById(R.id.Tempreature);
                visibility = (TextView) findViewById(R.id.Visibility);

                Log.v("Pune", "" +BASE_URL+DynamicUrl);


                summary.setText("Summary: "+products.getCurrently().getSummary());
                tempreature.setText("Tempreature: "+Double.toString(products.getCurrently().getTemperature()));
                visibility.setText("Visibility: "+Double.toString(products.getCurrently().getVisibility()));
// For Change Time in current Date time form TimeStamp
                dateObject = new Date(products.getCurrently().getTime()*1000);
                String formattedDate = formatDate(dateObject);
                String formattedTime = formatTime(dateObject);
                time.setText(formattedDate + "   " + formattedTime);
                Log.v("Pune", "" +products.getCurrently().getTime()
                );

                //......................


            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

                Toast.makeText(getBaseContext(), "Sorry, Product listing failed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

}
