package com.thecrunchycorner.calendar.services

import com.thecrunchycorner.calendar.domain.Schedule
import com.thecrunchycorner.calendar.domain.SlotStatus
import com.thecrunchycorner.calendar.domain.SlotWindow
import spock.lang.Specification

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class SlotGenerator15MinSlotSpec extends Specification {
    def test() {
        given:
        def schedule = new Schedule(1, LocalTime.of(9,0), LocalTime.of(12,30), LocalTime.of(13,
                30), LocalTime.of(16, 30), 15);
        def generator = new SlotGenerator();

        when:
        def slots = generator.getSlots(LocalDate.of(2020, 5, 18), schedule);
        def slot0 = slots.get(0).getSlots().get(0)
        def slot1 = slots.get(0).getSlots().get(1)
        def slot13 = slots.get(0).getSlots().get(13)
        def slot14 = slots.get(0).getSlots().get(14)
        def slot25 = slots.get(0).getSlots().get(25)


        then:
        slots.size() == 5
        slots.get(0).getSlots().size == 26

        slot0.getStart() == LocalTime.of(9,00)
        slot0.getEnd() == LocalTime.of(9,15)
        slot0.getStatus() == SlotStatus.FREE
        slot1.getStart() == LocalTime.of(9, 15)
        slot1.getEnd() == LocalTime.of(9, 30)
        slot13.getStart() == LocalTime.of(12, 15)
        slot13.getEnd() == LocalTime.of(12, 30)
        slot14.getStart() == LocalTime.of(13, 30)
        slot14.getEnd() == LocalTime.of(13, 45)
        slot25.getStart() == LocalTime.of(16, 15)
        slot25.getEnd() == LocalTime.of(16, 30)
    }
}
