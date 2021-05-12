package com.example.simpletodo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletodo.R;
import com.example.simpletodo.model.SharedViewModel;
import com.example.simpletodo.model.Task;
import com.example.simpletodo.util.Utils;
import com.google.android.material.chip.Chip;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private List<Task> tasks = new ArrayList<>();

    private OnTaskClickListener onTaskClickListener;


    public void setOnTaskClickListener(OnTaskClickListener onTaskClickListener) {
        this.onTaskClickListener = onTaskClickListener;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public Task getTask(int position) {
        return tasks.get(position);
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainRecyclerViewAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.txtTaskName.setText(task.getTask());


        holder.chip.setText(Utils.formatDate(task.getDueDate()));

        holder.chip.setTextColor(Utils.priorityColor(task));
        holder.imgCircle.setColorFilter(Utils.priorityColor(task));

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTaskName;
        private Chip chip;
        private ImageView imgCircle;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtTaskName = itemView.findViewById(R.id.row_txtTaskName);
            chip = itemView.findViewById(R.id.row_todo_chip);
            imgCircle = itemView.findViewById(R.id.row_todo_imgCircle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTaskClickListener.onItemClicked(tasks.get(getAdapterPosition()));
                }
            });


        }
    }
}
