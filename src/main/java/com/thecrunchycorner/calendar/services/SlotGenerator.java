package com.thecrunchycorner.calendar.services;

import com.thecrunchycorner.calendar.domain.Slot;
import com.thecrunchycorner.calendar.domain.DailySlots;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class SlotGenerator {
    public ArrayList<DailySlots> getSlots() {

        return getSlots(LocalDate.now());
    }

    public ArrayList<DailySlots> getSlots(LocalDate slotStartDay) {
        ArrayList<Slot> slots = new ArrayList<>();
        ArrayList<DailySlots> weekSlots = new ArrayList<>();
        LocalDate slotDay;
        for (int i = 0; i < 7; i++ ) {
            slotDay = LocalDate.now().plusDays(i);
            if (slotDay.getDayOfWeek() != DayOfWeek.SATURDAY && slotDay.getDayOfWeek() !=
                    DayOfWeek.SUNDAY) {
                weekSlots.add(new DailySlots(slotDay, slots));
            }
        }

        return weekSlots;
    }
}
