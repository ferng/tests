package com.thecrunchycorner.calendar

import com.thecrunchycorner.calendar.domain.Slot
import com.thecrunchycorner.calendar.domain.SlotStatus

import java.time.LocalTime

class SlotHelper {

    def static generateSlots(int quantity) {
        LocalTime start = LocalTime.of(9, 00)

        def slots = new ArrayList()

        for (int i = 0; i < quantity; i++) {
            def slot = new Slot(start, start.plusMinutes(15), SlotStatus.FREE)
            slots.add(slot)
            start = start.plusMinutes(15)
        }

        return slots
    }
}
