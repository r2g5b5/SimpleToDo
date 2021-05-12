package com.example.simpletodo.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String formatDate(Date date){
        SimpleDateFormat simpleDateFormat= (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyLocalizedPattern("EEE, MMM d");
        return simpleDateFormat.format(date);
    }
    public static void hideKeyBord(View view){
        InputMethodManager inputMethodManager=(InputMethodManager) view.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}
