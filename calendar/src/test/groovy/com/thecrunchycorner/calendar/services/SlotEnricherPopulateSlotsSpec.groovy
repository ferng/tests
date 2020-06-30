package com.thecrunchycorner.calendar.services

import com.thecrunchycorner.calendar.SlotHelper
import com.thecrunchycorner.calendar.domain.Appointment
import com.thecrunchycorner.calendar.domain.DailySlots
import com.thecrunchycorner.calendar.domain.SlotStatus
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalTime

class SlotEnricherPopulateSlotsSpec extends Specification {
    def test() {
        given:
        def enricher = new SlotEnricher()
        def dailySlots = new ArrayList()
        def day1 = new DailySlots(LocalDate.of(2020, 5, 20), SlotHelper.generateSlots(10))
        def day2 = new DailySlots(LocalDate.of(2020, 5, 21), SlotHelper.generateSlots(10))
        dailySlots.add(day1)
        dailySlots.add(day2)
        def appointments = new ArrayList()
        def appt1 = new Appointment(1, LocalDate.of(2020, 5, 20), LocalTime.of(9, 30), LocalTime
                .of(10, 30))
        def appt2 = new Appointment(1, LocalDate.of(2020, 5, 21), LocalTime.of(10, 45), LocalTime
                .of(11, 00))
        appointments.add(appt1)
        appointments.add(appt2)

        when:
        enricher.populateSlots(dailySlots, appointments)

        then:
        // test first day
        dailySlots.get(0).getSlots().get(0).getStatus() == SlotStatus.FREE
        dailySlots.get(0).getSlots().get(1).getStatus() == SlotStatus.FREE
        dailySlots.get(0).getSlots().get(2).getStatus() == SlotStatus.BOOKED
        dailySlots.get(0).getSlots().get(3).getStatus() == SlotStatus.BOOKED
        dailySlots.get(0).getSlots().get(4).getStatus() == SlotStatus.BOOKED
        dailySlots.get(0).getSlots().get(5).getStatus() == SlotStatus.BOOKED
        dailySlots.get(0).getSlots().get(6).getStatus() == SlotStatus.FREE
        dailySlots.get(0).getSlots().get(7).getStatus() == SlotStatus.FREE
        dailySlots.get(0).getSlots().get(8).getStatus() == SlotStatus.FREE
        dailySlots.get(0).getSlots().get(9).getStatus() == SlotStatus.FREE

        // test second day
        dailySlots.get(1).getSlots().get(0).getStatus() == SlotStatus.FREE
        dailySlots.get(1).getSlots().get(1).getStatus() == SlotStatus.FREE
        dailySlots.get(1).getSlots().get(2).getStatus() == SlotStatus.FREE
        dailySlots.get(1).getSlots().get(3).getStatus() == SlotStatus.FREE
        dailySlots.get(1).getSlots().get(4).getStatus() == SlotStatus.FREE
        dailySlots.get(1).getSlots().get(5).getStatus() == SlotStatus.FREE
        dailySlots.get(1).getSlots().get(6).getStatus() == SlotStatus.FREE
        dailySlots.get(1).getSlots().get(7).getStatus() == SlotStatus.BOOKED
        dailySlots.get(1).getSlots().get(8).getStatus() == SlotStatus.FREE
        dailySlots.get(1).getSlots().get(9).getStatus() == SlotStatus.FREE


    }

}
