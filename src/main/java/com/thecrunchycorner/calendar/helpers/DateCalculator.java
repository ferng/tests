package com.thecrunchycorner.calendar.helpers;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;

import java.time.LocalDate;

public class DateCalculator {
    public LocalDate nextWorkingDay(LocalDate date) {
        long offset;
        if (date.getDayOfWeek() == FRIDAY) {
            offset = 3L;
        } else if (date.getDayOfWeek() == SATURDAY) {
            offset = 2L;
        } else {
            offset = 1L;
        }
        return date.plusDays(offset);
    }
}
