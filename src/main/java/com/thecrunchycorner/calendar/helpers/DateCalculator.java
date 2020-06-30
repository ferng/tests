package com.thecrunchycorner.calendar.helpers;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;

import java.time.LocalDate;

public class DateCalculator {

    public static LocalDate nextWorkingDay(LocalDate date) {
        LocalDate nextDay;
        if (date.getDayOfWeek() == FRIDAY) {
            nextDay = date.plusDays(3L);
        } else if (date.getDayOfWeek() == SATURDAY) {
            nextDay = date.plusDays(2L);
        } else {
            nextDay = date.plusDays(1L);
        }

        return nextDay;
    }
}
