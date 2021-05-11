package com.example.simpletodo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletodo.R;
import com.example.simpletodo.model.Task;
import com.google.android.material.chip.Chip;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private  List<Task> tasks=new ArrayList<>();

    public void  setTasks(List<Task> tasks){
        this.tasks=tasks;
        notifyDataSetChanged();
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainRecyclerViewAdapter.ViewHolder holder, int position) {
        Task task=tasks.get(position);
        holder.txtTaskName.setText(task.getTask());

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTaskName;
        private Chip chip;
        private RadioButton radioButton;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtTaskName=itemView.findViewById(R.id.row_txtTaskName);
            chip=itemView.findViewById(R.id.row_todo_chip);
            radioButton=itemView.findViewById(R.id.row_todo_RadioButton);


        }
    }
}
