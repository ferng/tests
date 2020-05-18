package com.thecrunchycorner.calendar.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class Appointment {
    private UUID id;
    private Customer customer;
    private Consultant consultant;
    private LocalDate startDate;
    private LocalTime startTime;
    private Duration duration;
    private String complaint;

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getComplaint() {
        return complaint;
    }
}
