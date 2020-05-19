package com.thecrunchycorner.calendar.helpers;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;

import java.time.LocalDate;

public class DateCalculator {

    // Multiple return points allows this to be a static helper
    public static LocalDate nextWorkingDay(LocalDate date) {
        long offset;
        if (date.getDayOfWeek() == FRIDAY) {
            return date.plusDays(3L);
        } else if (date.getDayOfWeek() == SATURDAY) {
            return date.plusDays(2L);
        } else {
            return date.plusDays(1L);
        }
    }
}
