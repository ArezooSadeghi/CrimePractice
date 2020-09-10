package com.example.practicerecyclerview.utils;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    private static final int YEAR_START = 2000;
    private static final int YEAR_END = 2020;

    public static Date RandomDate() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int year = randBetween(YEAR_START, YEAR_END);
        gregorianCalendar.set(gregorianCalendar.YEAR, year);
        int dayOfYear = randBetween(1, gregorianCalendar.getActualMaximum(gregorianCalendar.DAY_OF_YEAR));
        gregorianCalendar.set(gregorianCalendar.DAY_OF_YEAR, dayOfYear);
        return gregorianCalendar.getTime();
    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}
