package com.example.simpletodo.model;

import android.app.Application;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.simpletodo.data.ToDoRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private ToDoRepository repository;
    private final LiveData<List<Task>> allTasks;


    public TaskViewModel(@NotNull Application application) {
        super(application);
        repository = new ToDoRepository(application);
        allTasks = repository.getAllTasks();

    }


    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public LiveData<Task> getTask(int id) {
        return repository.getTask(id);
    }

    public void insertTask(Task task) {
        repository.insertTask(task);
    }

    public void updateTask(Task task) {
        repository.updateTask(task);
    }

    public void deleteTask(Task task) {
        repository.deleteTask(task);
    }


}
