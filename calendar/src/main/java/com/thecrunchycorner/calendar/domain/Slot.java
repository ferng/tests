package com.thecrunchycorner.calendar.domain;

import java.time.LocalTime;

public class Slot {
    private long id;
    private Appointment appointment;
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

    public void setId(long id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }
}


