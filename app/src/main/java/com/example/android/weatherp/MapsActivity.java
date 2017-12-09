package com.example.android.weatherp;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.weatherp.Weather.WeatherMainActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public static final int Request_Location_code = 99;
    LatLng desiredLatLng;
    Address desiredAddress;
    String addressResult;
    Date date;
    EditText desiredPlace;
    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentLocationMarker;
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
        setContentView(R.layout.activity_maps);

        desiredPlace = findViewById(R.id.desiredPlace);

        dateView = (TextView) findViewById(R.id.tvDate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);






        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CheckLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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
                .append(month).append("/").append(year));
        Log.v("Date17", "" + str_date);
        DateFormat formatter;

        formatter = new SimpleDateFormat("dd/MM/yyyy");


        try {
            date = (Date) formatter.parse(str_date);
            Log.v("Date13", "" + date.getTime());

            //timestamp.setText(String.valueOf(date.getTime() / 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }






    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Request_Location_code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission is granted
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (client == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else //permission Denied
                {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
                return;
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            buildGoogleApiClient();
        }


    }

    protected synchronized void buildGoogleApiClient() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }

    @Override
    public void onLocationChanged(final Location location) {
        lastLocation = location;
        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));
        if (client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        }

        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses != null && addresses.size() > 0) {
                desiredAddress = addresses.get(0);

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < desiredAddress.getMaxAddressLineIndex(); i++) {
                    sb.append(desiredAddress.getAddressLine(i)).append("\n");
                }
                sb.append(desiredAddress.getLocality()).append("\n");//village
                sb.append(desiredAddress.getAdminArea()).append("\n"); //state
                //sb.append(address.getPostalCode()).append("\n");
                sb.append(desiredAddress.getCountryName());


                //sb.append(address.getSubAdminArea()).append("\n");//district


                addressResult = sb.toString();
                Log.v("address", "Address " + addressResult);
                Log.v("latitude", "Address " + location.getLatitude());


            }


        } catch (IOException e) {
            e.printStackTrace();
        }






        Button searchButton=findViewById(R.id.searchPlaceMap);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String desiredLocation = desiredPlace.getText().toString();
                List<Address> addressList;
                Log.v("jeet",""+desiredLatLng);

                if(!desiredPlace.equals(""))
                {
                    mMap.clear();
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        addressList = geocoder.getFromLocationName(desiredLocation, 1);
                        Log.v("WrongAdd", "" + addressList);


                        if (addressList.size() > 0)
                        {

                            desiredLatLng = new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(desiredLatLng);
                                markerOptions.title(desiredLocation);
                                mMap.addMarker(markerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(desiredLatLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                                StringBuilder sb = new StringBuilder();
                            desiredAddress = addressList.get(0);
                            for (int j = 0; j < desiredAddress.getMaxAddressLineIndex(); j++) {
                                sb.append(desiredAddress.getAddressLine(j)).append("\n");
                                }
                            sb.append(desiredAddress.getLocality()).append("\n");//village
                            sb.append(desiredAddress.getAdminArea()).append("\n"); //state

                            // sb.append(desiredAddress.getPostalCode()).append("\n");
                            sb.append(desiredAddress.getCountryName());

                            // sb.append(desiredAddress.getSubAdminArea()).append("\n");//district


                                 addressResult = sb.toString();





                        } else {
                            Toast.makeText(getBaseContext(), "Enter Right Address", Toast.LENGTH_SHORT).show();
                            desiredPlace.setText("");

                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                    }

                }
            }

        });

        Button getWeather=findViewById(R.id.getWeather);
        getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WeatherMainActivity.class);
                Bundle b=new Bundle();
                b.putString("latitude",String.valueOf(desiredAddress.getLatitude()));
                b.putString("longitude",String.valueOf(desiredAddress.getLongitude()));
                b.putString("address",addressResult);
                b.putString("timestamp", String.valueOf(date.getTime() / 1000));
                intent.putExtras(b);
                startActivity(intent);
            }
        });



    }




    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }

    public boolean CheckLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)

        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_Location_code);
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_Location_code);
            }
            return false;
        } else
            return true;
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}