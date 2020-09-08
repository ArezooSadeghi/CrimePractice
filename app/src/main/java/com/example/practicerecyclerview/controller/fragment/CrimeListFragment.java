package com.example.practicerecyclerview.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practicerecyclerview.R;
import com.example.practicerecyclerview.controller.activity.CrimeDetailActivity;
import com.example.practicerecyclerview.model.Crime;
import com.example.practicerecyclerview.repository.CrimeRepository;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mRecyclerView;

    public CrimeListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static CrimeListFragment newInstance() {
        Bundle args = new Bundle();
        CrimeListFragment fragment = new CrimeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        findViews(view);
        initViews();
        return view;
    }

    private void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL); --> Horizantal Positioning
        /*GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager); --> Grid Positioning*/
        mRecyclerView.setLayoutManager(linearLayoutManager);
        List<Crime> crimes;
        CrimeRepository crimeRepository = CrimeRepository.getInstance();
        crimes = crimeRepository.getCrimes();
        CrimeAdapter crimeAdapter = new CrimeAdapter(crimes);
        mRecyclerView.setAdapter(crimeAdapter);
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.crime_list_recyclerview);
    }

    public class CrimeHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle, mTextViewDate;
        private ImageView mImageViewSolved;
        private Crime mCrime;

        public CrimeHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.txt_crimetitle);
            mTextViewDate = itemView.findViewById(R.id.txt_crimedate);
            mImageViewSolved = itemView.findViewById(R.id.img_solved);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = CrimeDetailActivity.newIntent(getActivity(), mCrime.getId());
                    startActivity(intent);
                }
            });
        }

        public void bindCrime(Crime crime) {
            mCrime = crime;
            mTextViewTitle.setText(crime.getTitle());
            mTextViewDate.setText(crime.getDate().toString());
            mImageViewSolved.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }
    }

    public class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        public List<Crime> getCrimes() {
            return mCrimes;
        }

        public void setCrimes(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.row_list_crime, parent, false);
            CrimeHolder crimeHolder = new CrimeHolder(view);
            return crimeHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            holder.bindCrime(mCrimes.get(position));
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}