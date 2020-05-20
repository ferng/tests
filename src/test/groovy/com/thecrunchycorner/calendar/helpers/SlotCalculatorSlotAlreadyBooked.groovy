package com.thecrunchycorner.calendar.helpers

import com.thecrunchycorner.calendar.domain.Appointment
import com.thecrunchycorner.calendar.domain.Slot
import com.thecrunchycorner.calendar.domain.SlotStatus
import spock.lang.Specification

import java.time.LocalTime

class SlotCalculatorSlotAlreadyBooked extends Specification {

    def test() {
        given:
        def slot = Mock(Slot.class)
        def appt = Mock(Appointment.class)

        when:
        slot.getStatus() >> slotstatus
        slot.getStart() >> LocalTime.of(15, slotstart)
        appt.getAppStart() >> LocalTime.of(15, appstart)

        then:
        SlotCalculator.slotAlreadyBooked(slot, appt) == expected

        where:
        slotstatus        | slotstart | appstart | expected
        SlotStatus.FREE   | 15        | 15       | false      // slot is free
        SlotStatus.BOOKED | 15        | 30       | false      // different slot
        SlotStatus.BOOKED | 15        | 15       | true       // slot is booked


    }

}
