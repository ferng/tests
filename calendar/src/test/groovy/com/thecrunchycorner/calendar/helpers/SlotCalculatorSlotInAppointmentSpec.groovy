package com.thecrunchycorner.calendar.helpers

import com.thecrunchycorner.calendar.domain.Appointment
import com.thecrunchycorner.calendar.domain.Slot
import spock.lang.Specification

import java.time.LocalTime

class SlotCalculatorSlotInAppointmentSpec extends Specification {

    def test() {
        given:
        def slot = Mock(Slot.class)
        def appt = Mock(Appointment.class)

        when:
        slot.getStart() >> LocalTime.of(15, slotstart)
        appt.getAppStart() >> LocalTime.of(15, appstart)
        appt.getAppEnd() >> LocalTime.of(15, append)

        then:
        SlotCalculator.slotInAppt(slot, appt) == expected

        where:
        slotstart | appstart | append | expected
        15        | 15       | 30     | true        // slot and appt start at the same time
        15        | 00       | 45     | true        // slot within appointment
        00        | 15       | 30     | false       // slot before appointment
        30        | 00       | 15     | false       // slot after appointment
        30        | 00       | 30     | false       // slot immediately after appointment


    }

}
