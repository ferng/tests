package com.thecrunchycorner.calendar.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class DailySlots {
    final LocalDate day;
    final ArrayList<Slot> slots;

    public DailySlots(LocalDate day, ArrayList<Slot> slots) {
        this.day = day;
        this.slots = slots;
    }

    public LocalDate getDay() {
        return day;
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }
}
