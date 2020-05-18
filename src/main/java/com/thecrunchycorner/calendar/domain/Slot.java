package com.thecrunchycorner.calendar.domain;

import java.util.UUID;

public class Slot {
    private UUID id;
    private Appointment appointmentId;
    private SlotStatus status;

    public UUID getId() {
        return id;
    }

    public Appointment getAppointmentId() {
        return appointmentId;
    }

    public SlotStatus getStatus() {
        return status;
    }
}


