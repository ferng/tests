package com.thecrunchycorner.calendar.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Customer {
    private UUID id;
    private String name;
    private LocalDate dob;
    private String tel;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getTel() {
        return tel;
    }
}
