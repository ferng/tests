package com.thecrunchycorner.calendar.helpers;

import com.thecrunchycorner.calendar.domain.Appointment;
import com.thecrunchycorner.calendar.domain.Slot;
import com.thecrunchycorner.calendar.domain.SlotStatus;
import java.time.LocalTime;

public class SlotCalculator {

    public static boolean slotInAppt(Slot slot, Appointment appt) {
        LocalTime slotStart = slot.getStart();
        LocalTime apptStart = appt.getAppStart();
        LocalTime apptEnd = appt.getAppEnd();

        return (slotStart.equals(apptStart)
                || slotStart.isAfter(apptStart))
                && slotStart.isBefore(apptEnd);
    }

    public static boolean slotAlreadyBooked(Slot slot, Appointment appt) {
        LocalTime slotStart = slot.getStart();
        LocalTime apptStart = appt.getAppStart();
        SlotStatus status = slot.getStatus();

        return status.equals(SlotStatus.BOOKED)
                && slotStart.equals(apptStart);
    }
}
