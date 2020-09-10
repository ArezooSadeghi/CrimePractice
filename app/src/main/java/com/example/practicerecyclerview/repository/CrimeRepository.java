package com.example.practicerecyclerview.repository;

import com.example.practicerecyclerview.model.Crime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CrimeRepository implements IRepository {
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

    @Override
    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public void setCrimes(List<Crime> crimes) {
        mCrimes = crimes;
    }

    @Override
    public void create(Crime crime) {
        mCrimes.add(crime);
    }

    @Override
    public Crime read(UUID crimeId) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(crimeId)) {
                return crime;
            }
        }
        return null;
    }

    @Override
    public void update(Crime crime) {
        Crime findCrime = read(crime.getId());
        findCrime.setTitle(crime.getTitle());
        findCrime.setDate(crime.getDate());
        findCrime.setSolved(crime.isSolved());
    }

    @Override
    public void delete(Crime crime) {
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crime.getId())) {
                mCrimes.remove(crime);
                return;
            }
        }
    }

    @Override
    public int getPosition(UUID crimeId) {
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                return i;
            }
        }
        return 0;
    }
}
