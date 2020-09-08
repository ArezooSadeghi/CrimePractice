package com.example.practicerecyclerview.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.practicerecyclerview.R;
import com.example.practicerecyclerview.controller.fragment.CrimeDetailFragment;

import java.util.UUID;

public class CrimeDetailActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID = "com.example.practicerecyclerview.EXTRA_CRIME_ID";

    public static Intent newIntent(Context context, UUID crimeId) {
        Intent intent = new Intent(context, CrimeDetailActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        CrimeDetailFragment crimeDetailFragment = CrimeDetailFragment.newInstance(crimeId);
        return crimeDetailFragment;
    }
}
