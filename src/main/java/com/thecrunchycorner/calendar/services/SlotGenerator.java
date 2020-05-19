package com.thecrunchycorner.calendar.services;

import com.thecrunchycorner.calendar.domain.Slot;
import com.thecrunchycorner.calendar.domain.DailySlots;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class SlotGenerator {
    public ArrayList<DailySlots> getSlots() {
        return getSlots(LocalDate.now());
    }

    public ArrayList<DailySlots> getSlots(LocalDate slotStartDay) {
        return getSlots(slotStartDay, slotStartDay.plusDays(7L));
    }

    public ArrayList<DailySlots> getSlots(LocalDate slotStartDay, LocalDate slotEndDay) {
        ArrayList<Slot> slots = new ArrayList<>();
        ArrayList<DailySlots> weekSlots = new ArrayList<>();
        long numOfDays = ChronoUnit.DAYS.between(slotStartDay, slotEndDay);
        LocalDate slotDay;
        for (long i = 0; i < numOfDays ; i++ ) {
            slotDay = slotStartDay.plusDays(i);
            if (slotDay.getDayOfWeek() != DayOfWeek.SATURDAY && slotDay.getDayOfWeek() !=
                    DayOfWeek.SUNDAY) {
                weekSlots.add(new DailySlots(slotDay, slots));
            }
        }

        return weekSlots;
    }


}
