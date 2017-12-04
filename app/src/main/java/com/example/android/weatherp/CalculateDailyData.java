package com.example.android.weatherp;

import java.util.ArrayList;

/**
 * Created by Jindal on 11/30/2017.
 */

public class CalculateDailyData {






    public Integer meanTemp(ArrayList<WeatherData2> arrayData)
    {
        Double TampretureAtPosition = 0.0;
        for (int i = 0; i < arrayData.size(); i++) {

            TampretureAtPosition+=arrayData.get(i).getTempreture();

        }
        Double TempreatureMean = TampretureAtPosition / (arrayData.size() - 1);
        Double d = TempreatureMean;
        Integer tempmeanInt = d.intValue();
        return tempmeanInt;
    }
}
