package com.example.simpletodo.util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.simpletodo.model.Task;
import com.example.simpletodo.model.TaskPriority;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyLocalizedPattern("EEE, MMM d");
        return simpleDateFormat.format(date);
    }

    public static void hideKeyBord(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static int priorityColor(Task task) {
        int color = Color.BLACK;
        if (task.getTaskPriority() == TaskPriority.HIGH) {
            color = Color.RED;
        } else if (task.getTaskPriority() == TaskPriority.MEDIUM) {
            color = Color.argb(200, 250, 200, 20);
        } else if (task.getTaskPriority() == TaskPriority.LOW) {
            color = Color.argb(200, 0, 50, 150);
        }
        return color;
    }


}
