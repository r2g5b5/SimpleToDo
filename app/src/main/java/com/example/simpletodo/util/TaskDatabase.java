package com.example.simpletodo.util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.simpletodo.data.TaskDao;
import com.example.simpletodo.model.Task;

@Database(entities = Task.class, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class TaskDatabase extends RoomDatabase {

    private static volatile TaskDatabase instance;

    public abstract TaskDao taskDao();

    public static synchronized TaskDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TaskDatabase.class,
                    "todo_database")
                    .build();
        }
        return instance;
    }


}
