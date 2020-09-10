package com.example.practicerecyclerview.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.practicerecyclerview.R;
import com.example.practicerecyclerview.model.Crime;
import com.example.practicerecyclerview.repository.CrimeRepository;
import com.example.practicerecyclerview.repository.IRepository;

import java.util.Date;
import java.util.UUID;

public class CrimeDetailFragment extends Fragment {
    private EditText mEditTextTitle;
    private Button mButtonDate;
    private CheckBox mCheckBoxSolved;
    private IRepository mCrimeRepository;
    private Crime mCrime;
    public static final String ARGS_CRIME_ID = "ARGS_CRIME_ID";
    public static final String FRAGMENT_TAG_DATE_PICKER = "datePicker";
    public static final int REQUIST_CODE_DATE_PICKER = 0;

    public CrimeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getArguments().getSerializable(ARGS_CRIME_ID);
        mCrimeRepository = CrimeRepository.getInstance();
        mCrime = mCrimeRepository.read(crimeId);
    }

    public static CrimeDetailFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_CRIME_ID, crimeId);
        CrimeDetailFragment fragment = new CrimeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_detail, container, false);
        findViews(view);
        setListeners();
        initViews();
        return view;
    }

    private void setListeners() {
        mEditTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mCrime.setTitle(s.toString());
            }
        });

        mCheckBoxSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);

            }
        });

        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mCrime.getDate());
                datePickerFragment.setTargetFragment(CrimeDetailFragment.this, REQUIST_CODE_DATE_PICKER);
                datePickerFragment.show(getActivity().getSupportFragmentManager(), FRAGMENT_TAG_DATE_PICKER);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null) {
            return;
        }
        if (requestCode == REQUIST_CODE_DATE_PICKER) {
            Date userSelectedDate = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_USER_SELECTED_DATE);
            updateCrimeDate(userSelectedDate);

        }
    }

    private void initViews() {
        mEditTextTitle.setText(mCrime.getTitle());
        mButtonDate.setText(mCrime.getDate().toString());
        mCheckBoxSolved.setChecked(mCrime.isSolved());
    }

    private void findViews(View view) {
        mEditTextTitle = view.findViewById(R.id.crime_title);
        mButtonDate = view.findViewById(R.id.crime_date);
        mCheckBoxSolved = view.findViewById(R.id.crime_solved);
    }


    public void updateCrimeDate(Date userSelectedDate) {
        mCrime.setDate(userSelectedDate);
        mCrimeRepository.update(mCrime);
        mButtonDate.setText(mCrime.getDate().toString());
    }
}