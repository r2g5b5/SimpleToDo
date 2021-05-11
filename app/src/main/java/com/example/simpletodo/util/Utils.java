package com.example.simpletodo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String formatDate(Date date){
        SimpleDateFormat simpleDateFormat= (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyLocalizedPattern("EEE, MMM d");
        return simpleDateFormat.format(date);
    }
}
