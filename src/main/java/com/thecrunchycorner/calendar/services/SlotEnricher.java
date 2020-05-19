package com.thecrunchycorner.calendar.services;

import com.thecrunchycorner.calendar.domain.Appointment;
import com.thecrunchycorner.calendar.domain.DailySlots;
import com.thecrunchycorner.calendar.domain.Slot;
import com.thecrunchycorner.calendar.domain.SlotStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SlotEnricher {

    // I could create a new list of slots to make it purely functional, but this way we don't
    // duplicate all the slot data
    public void populateSlots(List<DailySlots> weeklySlots, List<Appointment> appointments) {
        weeklySlots.forEach((dailySlots) -> {
            List<Appointment> dailyAppointments = getDailyAppointments(dailySlots.getDay(),
                    appointments);
            if (dailyAppointments.size() > 0) {
                dailyAppointments.forEach((appt -> {
                    dailySlots.getSlots().forEach((slot -> {
                        if (slotInAppt(slot, appt)) {
                            slot.setAppointment(appt);
                            slot.setStatus(SlotStatus.BOOKED);
                        }
                    }));
                }));
            }
        });

    }

    private boolean slotInAppt(Slot slot, Appointment appt) {
        LocalTime slotStart = slot.getStart();
        LocalTime apptStart = appt.getAppStart();
        LocalTime apptEnd = appt.getAppEnd();

        return (slotStart.equals(apptStart) || slotStart.isAfter(apptStart)) && slotStart.isBefore(apptEnd);
    }

    private List<Appointment> getDailyAppointments(LocalDate slotDay,
                                                   List<Appointment> appointments) {
        return appointments.stream()
                .filter((a) -> a.getAppDate().equals(slotDay))
                .collect(Collectors.toList());
    }
}
