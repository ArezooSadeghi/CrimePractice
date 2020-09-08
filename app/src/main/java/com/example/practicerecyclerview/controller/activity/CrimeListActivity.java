package com.example.practicerecyclerview.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.practicerecyclerview.R;
import com.example.practicerecyclerview.controller.fragment.CrimeDetailFragment;
import com.example.practicerecyclerview.controller.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CrimeListActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        CrimeListFragment crimeListFragment = CrimeListFragment.newInstance();
        return crimeListFragment;
    }
}