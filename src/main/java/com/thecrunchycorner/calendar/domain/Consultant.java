package com.thecrunchycorner.calendar.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "SCHEDULE")
public class Consultant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
