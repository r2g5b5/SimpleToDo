package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.simpletodo.adapter.MainRecyclerViewAdapter;
import com.example.simpletodo.model.Task;
import com.example.simpletodo.model.TaskPriority;
import com.example.simpletodo.model.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private TaskViewModel taskViewModel;
    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter mainRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        taskViewModel.getAllTasks().observe(MainActivity.this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
               mainRecyclerViewAdapter.setTasks(tasks);
            }
        });




    }

    private void init() {
        Toolbar toolbar=findViewById(R.id.main_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        floatingActionButton=findViewById(R.id.main_fab);
        taskViewModel= new ViewModelProvider(MainActivity.this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TaskViewModel.class);

        recyclerView=findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mainRecyclerViewAdapter=new MainRecyclerViewAdapter();
        recyclerView.setAdapter(mainRecyclerViewAdapter);



    }
}