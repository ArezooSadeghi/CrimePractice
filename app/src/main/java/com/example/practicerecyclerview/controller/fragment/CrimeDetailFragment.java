package com.example.practicerecyclerview.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.practicerecyclerview.R;
import com.example.practicerecyclerview.model.Crime;
import com.example.practicerecyclerview.repository.CrimeRepository;

import java.util.UUID;

public class CrimeDetailFragment extends Fragment {

    private EditText mEditTextTitle;
    private Button mButtonDate;
    private CheckBox mCheckBoxSolved;

    private Crime mCrime;
    public static final String ARGS_CRIME_ID = "ARGS_CRIME_ID";

    public CrimeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getArguments().getSerializable(ARGS_CRIME_ID);
        CrimeRepository crimeRepository = CrimeRepository.getInstance();
        mCrime = crimeRepository.read(crimeId);
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
        initViews();
        return view;
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
}