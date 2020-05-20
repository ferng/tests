package com.thecrunchycorner.calendar.helpers;

import com.thecrunchycorner.calendar.domain.Appointment;
import com.thecrunchycorner.calendar.domain.Slot;
import com.thecrunchycorner.calendar.domain.SlotStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class SlotCalculator {

    public static boolean slotInAppt(Slot slot, Appointment appt) {
        LocalTime slotStart = slot.getStart();
        LocalTime apptStart = appt.getAppStart();
        LocalTime apptEnd = appt.getAppEnd();

        return (slotStart.equals(apptStart) || slotStart.isAfter(apptStart)) && slotStart.isBefore(apptEnd);
    }

    public static boolean slotAlreadyBooked(Slot slot, Appointment appt) {
        LocalTime slotStart = slot.getStart();
        LocalTime apptStart = appt.getAppStart();
        LocalTime apptEnd = appt.getAppEnd();

        return slot.getStatus().equals(SlotStatus.BOOKED) && slotStart.equals(apptStart);
    }
}
