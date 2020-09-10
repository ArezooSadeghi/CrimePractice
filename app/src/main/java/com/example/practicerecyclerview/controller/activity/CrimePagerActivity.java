package com.example.practicerecyclerview.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.practicerecyclerview.R;
import com.example.practicerecyclerview.controller.fragment.CrimeDetailFragment;
import com.example.practicerecyclerview.model.Crime;
import com.example.practicerecyclerview.repository.CrimeRepository;
import com.example.practicerecyclerview.repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {
    private ViewPager2 mViewPagerCrime;
    private UUID mCrimeId;
    private Crime mCrime;
    private IRepository mCrimeRepository;
    public static final String EXTRA_CRIME_ID = "com.example.practicerecyclerview.EXTRA_CRIME_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        mCrimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        findViews();
        initViews();
    }

    private void initViews() {
        mCrimeRepository = CrimeRepository.getInstance();
        mCrime = mCrimeRepository.read(mCrimeId);
        int currentIndex = mCrimeRepository.getPosition(mCrimeId);
        List<Crime> crimes = mCrimeRepository.getCrimes();
        CrimePagerAdapter crimePagerAdapter = new CrimePagerAdapter(this, crimes);
        mViewPagerCrime.setAdapter(crimePagerAdapter);
        mViewPagerCrime.setCurrentItem(currentIndex);
    }

    private void findViews() {
        mViewPagerCrime = findViewById(R.id.crime_viewpager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateCrime();
    }

    public static Intent newIntent(Context context, UUID crimeId) {
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    public class CrimePagerAdapter extends FragmentStateAdapter {
        private List<Crime> mCrimes;

        public CrimePagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Crime> crimes) {
            super(fragmentActivity);
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
        public Fragment createFragment(int position) {
            CrimeDetailFragment crimeDetailFragment = CrimeDetailFragment.newInstance(mCrimes.get(position).getId());
            return crimeDetailFragment;
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    public void updateCrime() {
        mCrimeRepository.update(mCrime);
    }
}