package com.example.simpletodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.simpletodo.adapter.MainRecyclerViewAdapter;
import com.example.simpletodo.adapter.OnTaskClickListener;
import com.example.simpletodo.model.SharedViewModel;
import com.example.simpletodo.model.Task;
import com.example.simpletodo.model.TaskPriority;
import com.example.simpletodo.model.TaskViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnTaskClickListener {

    private FloatingActionButton floatingActionButton;
    private TaskViewModel taskViewModel;
    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter mainRecyclerViewAdapter;
    private BottomSheetFragment bottomSheetFragment;

    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                taskViewModel.deleteTask(mainRecyclerViewAdapter.getTask(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();


            }
        }).attachToRecyclerView(recyclerView);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showbottomSheetFragment();

            }
        });

        taskViewModel.getAllTasks().observe(MainActivity.this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                mainRecyclerViewAdapter.setTasks(tasks);
            }
        });


    }

    private void showbottomSheetFragment() {
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        floatingActionButton = findViewById(R.id.main_fab);
        taskViewModel = new ViewModelProvider(MainActivity.this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TaskViewModel.class);

        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mainRecyclerViewAdapter = new MainRecyclerViewAdapter();
        recyclerView.setAdapter(mainRecyclerViewAdapter);
        bottomSheetFragment = new BottomSheetFragment();
        ConstraintLayout mainConstraintLayout = findViewById(R.id.bottom_sheet_constraintLayout);
        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(mainConstraintLayout);
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);
        mainRecyclerViewAdapter.setOnTaskClickListener(this);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);


    }

    @Override
    public void onItemClicked(Task task) {
        sharedViewModel.selectedItem(task);
        sharedViewModel.setUpdate(true);
        showbottomSheetFragment();

    }
}