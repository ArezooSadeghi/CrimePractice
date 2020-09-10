package com.example.practicerecyclerview.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.practicerecyclerview.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {
    public static final String ARGS_CRIME_DATE = "crimeDate";
    public static final String EXTRA_USER_SELECTED_DATE = "com.example.practicerecyclerview.userSelectedDate";
    private Date mCrimeDate;
    private DatePicker mDatePicker;

    public DatePickerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCrimeDate = (Date) getArguments().getSerializable(ARGS_CRIME_DATE);
    }

    public static DatePickerFragment newInstance(Date crimeDate) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_CRIME_DATE, crimeDate);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.fragment_date_picker, null);
        findViews(view);
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setTitle("Date Of Crime")
                .setMessage("Test Message").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Date userSelectedDate = extractDateFromDatePicker();
                        sendResult(userSelectedDate);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null).setView(view).create();
        return alertDialog;
    }


    private Date extractDateFromDatePicker() {
        int year = mDatePicker.getYear();
        int month = mDatePicker.getMonth();
        int dayOfMonth = mDatePicker.getDayOfMonth();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, dayOfMonth);
        Date date = gregorianCalendar.getTime();
        return date;
    }

    public void findViews(View view) {
        mDatePicker = view.findViewById(R.id.date_picker_crime);
    }
    public void sendResult(Date userSelectedDate) {
        Fragment fragment = getTargetFragment();
        int requestCode = getTargetRequestCode();
        int resultCode = Activity.RESULT_OK;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_USER_SELECTED_DATE, userSelectedDate);
        fragment.onActivityResult(requestCode, resultCode, intent);
    }
    public void initViews() {
        initDatePicker();
    }

    private void initDatePicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mCrimeDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year, month, dayOfMonth, null);
    }

}