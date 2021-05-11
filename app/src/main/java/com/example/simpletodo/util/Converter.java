package com.example.simpletodo.util;

import androidx.room.TypeConverter;

import com.example.simpletodo.model.TaskPriority;

import java.util.Date;

public class Converter {

    @TypeConverter
    public static Date toDate(Long value) {
        if (value == null) {
            return null;
        } else {
            return new Date(value);
        }
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }

    @TypeConverter
    public static String fromPriority(TaskPriority priority) {
        if (priority == null) {
            return null;
        } else {
            return priority.name();
        }
    }

    @TypeConverter
    public static TaskPriority toPriority(String string) {
        if (string == null) {
            return null;
        } else {
            return TaskPriority.valueOf(string);
        }
    }

}
