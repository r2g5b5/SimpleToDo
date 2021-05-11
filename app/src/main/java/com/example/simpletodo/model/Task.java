package com.example.simpletodo.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    private int taskId;

    private String task;

    private TaskPriority taskPriority;

    @ColumnInfo(name = "due_date")
    private Date dueDate;

    @ColumnInfo(name = "created_date")
    private Date createdDate;

    @ColumnInfo(name = "is_done")
    private boolean isDone;


    public Task(String task, TaskPriority taskPriority, Date dueDate, Date createdDate, boolean isDone) {
        this.task = task;
        this.taskPriority = taskPriority;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
        this.isDone = isDone;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTask() {
        return task;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public boolean isDone() {
        return isDone;
    }
}
