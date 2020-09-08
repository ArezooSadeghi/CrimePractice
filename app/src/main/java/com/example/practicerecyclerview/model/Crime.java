package com.example.practicerecyclerview.model;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private String mTitle;
    private Date mDate;
    private boolean mIsSolved;
    private UUID mId;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public UUID getId() {
        return mId;
    }

    public boolean isSolved() {
        return mIsSolved;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setSolved(boolean solved) {
        mIsSolved = solved;
    }
}
