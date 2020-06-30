package com.thecrunchycorner.calendar.domain;

import java.time.LocalDate;

public class Customer {
    private long id;
    private String name;
    private LocalDate dob;
    private String tel;

    public long getId() {
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
