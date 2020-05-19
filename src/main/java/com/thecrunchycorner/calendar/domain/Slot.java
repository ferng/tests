package com.thecrunchycorner.calendar.domain;

public class Slot {
    private long id;
    private Appointment appointmentId;
    private SlotStatus status;

    public long getId() {
        return id;
    }

    public Appointment getAppointmentId() {
        return appointmentId;
    }

    public SlotStatus getStatus() {
        return status;
    }
}


