package com.thecrunchycorner.calendar.domain;

import java.time.LocalTime;

public class Slot {
    private long id;
    private Appointment appointmentId;
    private LocalTime start;
    private LocalTime end;
    private SlotStatus status;

    public Slot(LocalTime start, LocalTime end, SlotStatus status) {
        this.start = start;
        this.end = end;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public Appointment getAppointmentId() {
        return appointmentId;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public SlotStatus getStatus() {
        return status;
    }
}


