package com.example.simpletodo.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.simpletodo.model.Task;
import com.example.simpletodo.util.TaskDatabase;

import java.util.List;

public class ToDoRepository {

    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;

    public ToDoRepository(Application application) {
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        this.taskDao = taskDatabase.taskDao();
        this.allTasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insertTask(Task task) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                taskDao.insertTask(task);
            }
        }).start();
    }

    public LiveData<Task> getTask(int id) {
        return taskDao.getTask(id);
    }

    ;

    public void updateTask(Task task) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                taskDao.updateTask(task);
            }
        }).start();
    }

    public void deleteTask(Task task) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                taskDao.deleteTask(task);
            }
        }).start();
    }

}
