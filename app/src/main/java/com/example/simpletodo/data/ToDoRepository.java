package com.example.simpletodo.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.simpletodo.model.Task;
import com.example.simpletodo.util.TaskDatabase;

import java.util.List;

public class ToDoRepository {

    private final TaskDao taskDao;
    private final LiveData<List<Task>> liveData;

    public ToDoRepository(Application application) {
        TaskDatabase taskDatabase=TaskDatabase.getInstance(application);
        this.taskDao=taskDatabase.taskDao();
        this.liveData = taskDao.getTasks();
    }
}
