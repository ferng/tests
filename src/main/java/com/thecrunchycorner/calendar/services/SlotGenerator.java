package com.thecrunchycorner.calendar.services;

import com.thecrunchycorner.calendar.domain.DailySlots;
import com.thecrunchycorner.calendar.domain.Schedule;
import com.thecrunchycorner.calendar.domain.Slot;
import com.thecrunchycorner.calendar.domain.SlotStatus;
import com.thecrunchycorner.calendar.domain.SlotWindow;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class SlotGenerator {
    public ArrayList<DailySlots> getSlots(Schedule schedule) {
        return getSlots(LocalDate.now(), schedule);
    }

    public ArrayList<DailySlots> getSlots(LocalDate slotStartDay, Schedule schedule) {
        return getSlots(slotStartDay, slotStartDay.plusDays(7L), schedule);
    }

    public ArrayList<DailySlots> getSlots(LocalDate slotStartDay, LocalDate slotEndDay, Schedule schedule) {
        ArrayList<DailySlots> weekSlots = new ArrayList<>();
        long numOfDays = ChronoUnit.DAYS.between(slotStartDay, slotEndDay);
        LocalDate slotDay;

        for (long i = 0; i < numOfDays; i++) {
            slotDay = slotStartDay.plusDays(i);
            if (slotDay.getDayOfWeek() != DayOfWeek.SATURDAY && slotDay.getDayOfWeek() != DayOfWeek.SUNDAY) {
                ArrayList<Slot> slots = getDailySlots(schedule);
                weekSlots.add(new DailySlots(slotDay, slots));
            }
        }
        return weekSlots;
    }


    private ArrayList<Slot> getDailySlots(Schedule schedule) {
        ArrayList<SlotWindow> slotWindows = getSlotWindows(schedule);
        ArrayList<Slot> slots = new ArrayList<>();
        slotWindows.forEach((slotWindow -> {
            slots.addAll(generateSlotsForWindow(slotWindow, schedule.getSlotDuration()));
        }));
        return slots;
    }


    private ArrayList<SlotWindow> getSlotWindows(Schedule schedule) {
        ArrayList<SlotWindow> windows = new ArrayList<>();

        SlotWindow window1 = new SlotWindow(schedule.getStart1(), schedule.getEnd1());
        SlotWindow window2 = new SlotWindow(schedule.getStart2(), schedule.getEnd2());

        windows.add(window1);
        windows.add(window2);

        return windows;
    }


    private ArrayList<Slot> generateSlotsForWindow(SlotWindow slotWindow, long slotDuration) {
        ArrayList<Slot> slots = new ArrayList<>();
        LocalTime start = slotWindow.getStart();
        LocalTime end;

        do {
            end = start.plusMinutes(slotDuration);
            slots.add(new Slot(start, end, SlotStatus.FREE));
            start = start.plusMinutes(slotDuration);
        } while (! end.isAfter(slotWindow.getEnd()));

        slots.remove(slots.size() -1);
        return slots;
    }
}
