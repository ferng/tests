package com.thecrunchycorner.calendar.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {
    private UUID id;
    private Customer customer;
    private Consultant consultant;
    private LocalDateTime start;
    private Duration duration;

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public Duration getDuration() {
        return duration;
    }
}
