package com.example.simpletodo;

import android.content.DialogInterface;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.ViewModelProvider;

import com.example.simpletodo.model.SharedViewModel;
import com.example.simpletodo.model.Task;
import com.example.simpletodo.model.TaskPriority;
import com.example.simpletodo.model.TaskViewModel;
import com.example.simpletodo.util.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private EditText edtToDoTask;
    private ImageButton imgCalender;
    private ImageButton imgPriority;
    private RadioGroup radioGroup;
    private int selectedItemId;
    private RadioButton selectedRadioButton;
    private ImageButton imgSave;
    private CalendarView calendarView;
    private Group calenderGroup;
    private Chip chipToday;
    private Chip chipTomorrow;
    private Chip chipNextWeek;

    private TaskViewModel taskViewModel;

    private Date date;

    private Calendar calendar;

    private SharedViewModel sharedViewModel;

    private boolean update;


    public BottomSheetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        init(view);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (sharedViewModel.getSelectedItem().getValue() != null && sharedViewModel.getUpdate()) {
            Task task = sharedViewModel.getSelectedItem().getValue();
            update = sharedViewModel.getUpdate();
            edtToDoTask.setText(task.getTask());

        }

    }

    @Override
    public void onDismiss(@NonNull @NotNull DialogInterface dialog) {
        super.onDismiss(dialog);
        edtToDoTask.setText("");
        date = null;
        sharedViewModel.setUpdate(false);
        update = false;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskToDo = edtToDoTask.getText().toString();

                if (!taskToDo.isEmpty()) {
                    if (date == null) {
                        Toast.makeText(getActivity(), "Please select a date", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Task task = new Task(taskToDo, TaskPriority.HIGH, date,
                            Calendar.getInstance().getTime(), false);

                    if (update) {
                        Task updateTask = sharedViewModel.getSelectedItem().getValue();
                        updateTask.setTask(taskToDo);
                        updateTask.setCreatedDate(Calendar.getInstance().getTime());
                        updateTask.setDueDate(date);
                        updateTask.setTaskPriority(TaskPriority.HIGH);


                        taskViewModel.updateTask(updateTask);
                        edtToDoTask.setText("");
                        date = null;
                        sharedViewModel.setUpdate(false);
                        dismiss();
                        Toast.makeText(getActivity(), "Item Updated", Toast.LENGTH_SHORT).show();
                    } else {


                        if (taskViewModel != null) {
                            taskViewModel.insertTask(task);
                            edtToDoTask.setText("");
                            date = null;
                            dismiss();
                            Toast.makeText(getActivity(), "Item Saved", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });


        imgCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calenderGroup.setVisibility(calendarView.getVisibility() == View.GONE ?
                        View.VISIBLE : View.GONE);
                Utils.hideKeyBord(v);
            }
        });

        imgPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Utils.hideKeyBord(v);
            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.clear();
                calendar.set(year, month, dayOfMonth);
                date = calendar.getTime();
            }
        });

        chipToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar.add(Calendar.DAY_OF_YEAR, 0);
                date = calendar.getTime();
                calendarView.setDate(Calendar.getInstance().getTimeInMillis(), true, true);


            }
        });
        chipTomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.clear();
                calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                date = calendar.getTime();
                calendarView.setDate(date.getTime(), true, true);


            }
        });
        chipNextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.clear();
                calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                date = calendar.getTime();
                calendarView.setDate(date.getTime(), true, true);


            }
        });


    }

    private void init(View view) {
        calendarView = view.findViewById(R.id.calendar_view);
        calenderGroup = view.findViewById(R.id.bottom_sheet_calendar_group);
        imgCalender = view.findViewById(R.id.bottom_sheet_imgTodayCalendar);
        edtToDoTask = view.findViewById(R.id.bottom_sheet_edtEnterTodo);
        imgPriority = view.findViewById(R.id.bottom_sheet_imgPriorityTodo);
        radioGroup = view.findViewById(R.id.bottom_sheet_radioGroupPriority);
        imgSave = view.findViewById(R.id.bottom_sheet_imgSaveTodo);
        chipToday = view.findViewById(R.id.bottom_sheet_ChipToday);
        chipTomorrow = view.findViewById(R.id.bottom_sheet_ChipTomorrow);
        chipNextWeek = view.findViewById(R.id.bottom_sheet_ChipNextWeek);

        calendar = Calendar.getInstance();
        if (getActivity() != null) {
            taskViewModel = new ViewModelProvider(requireActivity()
                    , ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(TaskViewModel.class);
            sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        }

    }

}