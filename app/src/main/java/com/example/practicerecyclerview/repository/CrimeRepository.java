package com.example.practicerecyclerview.repository;

import com.example.practicerecyclerview.model.Crime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CrimeRepository {
    private static CrimeRepository sInstance;
    private final static int LIST_CRIME_SIZE = 100;
    private List<Crime> mCrimes;

    private CrimeRepository() {
        mCrimes = new ArrayList<>();
        for (int i = 0; i < LIST_CRIME_SIZE; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime#" + (i + 1));
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    public static CrimeRepository getInstance() {
        if (sInstance == null) {
            sInstance = new CrimeRepository();
        }
        return sInstance;
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public void setCrimes(List<Crime> crimes) {
        mCrimes = crimes;
    }

    public void create(Crime crime) {
        mCrimes.add(crime);
    }

    public Crime read(UUID crimeId) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(crimeId)) {
                return crime;
            }
        }
        return null;
    }

    public void update(Crime crime) {
        Crime findCrime = read(crime.getId());
        findCrime.setTitle(crime.getTitle());
        findCrime.setDate(crime.getDate());
        findCrime.setSolved(crime.isSolved());
    }

    public void remove(Crime crime) {
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crime.getId())) {
                mCrimes.remove(crime);
                return;
            }
        }
    }
}
