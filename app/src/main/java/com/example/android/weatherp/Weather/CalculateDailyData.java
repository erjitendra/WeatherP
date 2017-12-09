package com.example.android.weatherp.Weather;

import com.example.android.weatherp.Weather.Models.WeatherDataArrayKeysModel;

import java.util.ArrayList;

/**
 * Created by Jindal on 11/30/2017.
 */

public class CalculateDailyData {

    public Double maxTemp(ArrayList<WeatherDataArrayKeysModel> arrayData) {
        double max = arrayData.get(0).getTempreture();
        for (int counter = 1; counter < arrayData.size(); counter++)

        {
            if (arrayData.get(counter).getTempreture() > max) {
                max = arrayData.get(counter).getTempreture();
            }
        }
        return max;
    }


    public Double minTemp(ArrayList<WeatherDataArrayKeysModel> arrayData) {
        double min = arrayData.get(0).getTempreture();
        for (int counter = 1; counter < arrayData.size(); counter++)

        {
            if (arrayData.get(counter).getTempreture() < min) {
                min = arrayData.get(counter).getTempreture();
            }
        }
        return min;
    }


    public Double meanTemp(ArrayList<WeatherDataArrayKeysModel> arrayData) {
        Double TampretureAtPosition = 0.0;
        for (int i = 0; i < arrayData.size(); i++) {

            TampretureAtPosition += arrayData.get(i).getTempreture();
        }
        Double TempreatureMean = TampretureAtPosition / (arrayData.size() - 1);
        return TempreatureMean;
    }
}
