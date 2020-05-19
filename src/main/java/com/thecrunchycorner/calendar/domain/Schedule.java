package com.thecrunchycorner.calendar.domain;

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "SCHEDULE")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "ID", updatable = false, nullable = false)
    private long id;

    private long consultantId;
    private LocalTime start1;
    private LocalTime end1;
    private LocalTime start2;
    private LocalTime end2;
    private int slotDuration;

    public long getId() {
        return id;
    }

    public long getConsultantId() {
        return consultantId;
    }

    public LocalTime getStart1() {
        return start1;
    }

    public LocalTime getEnd1() {
        return end1;
    }

    public LocalTime getStart2() {
        return start2;
    }

    public LocalTime getEnd2() {
        return end2;
    }

    public int getSlotDuration() {
        return slotDuration;
    }
}