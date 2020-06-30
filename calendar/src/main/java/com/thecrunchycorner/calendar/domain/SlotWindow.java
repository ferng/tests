package com.thecrunchycorner.calendar.domain;

import java.time.LocalTime;

public class SlotWindow {
    private final LocalTime start;
    private final LocalTime end;

    public SlotWindow(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }
}
