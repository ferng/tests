package com.thecrunchycorner.calendar.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "APPOINTMENTS")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "ID", updatable = false, nullable = false)
    private long id;

    private long customerId;
    private long consultantId;
    private LocalDate appDate;
    private LocalTime appStart;
    private LocalTime appEnd;
    private String complaint;

    public Appointment() {
    }

    public Appointment(long consultantId, LocalDate appDate, LocalTime appStart, LocalTime appEnd) {
        this.consultantId = consultantId;
        this.appDate = appDate;
        this.appStart = appStart;
        this.appEnd = appEnd;
    }

    public long getId() {
        return id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getConsultantId() {
        return consultantId;
    }

    public LocalDate getAppDate() {
        return appDate;
    }

    public LocalTime getAppStart() {
        return appStart;
    }

    public LocalTime getAppEnd() {
        return appEnd;
    }

    public String getComplaint() {
        return complaint;
    }
}
