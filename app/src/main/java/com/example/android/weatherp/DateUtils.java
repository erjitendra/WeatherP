package com.example.android.weatherp;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jindal on 12/9/2017.
 */

public class DateUtils {

    public static StringBuilder getDate(int year, int month, int day) {
        return new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year);
    }

    public static Date getDateObj(int year, int month, int day) {


        Date dateObj = new Date();

        String str_date = String.valueOf(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        Log.v("Date17", "" + str_date);
        DateFormat formatter;

        formatter = new SimpleDateFormat("dd/MM/yyyy");


        try {
            dateObj = (Date) formatter.parse(str_date);

        } catch (ParseException e) {
            e.printStackTrace();

        }

        return dateObj;
    }

}

