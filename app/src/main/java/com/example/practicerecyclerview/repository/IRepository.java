package com.example.practicerecyclerview.repository;

import com.example.practicerecyclerview.model.Crime;

import java.util.List;
import java.util.UUID;

public interface IRepository {
    List<Crime> getCrimes();

    void create(Crime crime);

    Crime read(UUID crimeId);

    void update(Crime crime);

    void delete(Crime crime);

    int getPosition(UUID crimeId);
}
