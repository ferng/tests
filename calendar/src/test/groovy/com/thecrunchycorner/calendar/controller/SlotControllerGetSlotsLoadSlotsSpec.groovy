package com.thecrunchycorner.calendar.controller

import com.thecrunchycorner.calendar.SlotHelper
import com.thecrunchycorner.calendar.domain.Appointment
import com.thecrunchycorner.calendar.domain.DailySlots
import com.thecrunchycorner.calendar.domain.Schedule
import com.thecrunchycorner.calendar.services.SlotEnricher
import com.thecrunchycorner.calendar.services.SlotGenerator
import com.thecrunchycorner.calendar.services.backend.AppointmentService
import com.thecrunchycorner.calendar.services.backend.ScheduleService
import spock.lang.Specification

import javax.servlet.http.HttpServletResponse
import java.time.LocalDate
import java.time.LocalTime

class SlotControllerGetSlotsLoadSlotsSpec extends Specification {
    def test() {
        given:
        def scheduleService = Mock(ScheduleService.class)
        def apptService = Mock(AppointmentService.class)
        def slotGenerator = Mock(SlotGenerator.class)
        def slotEnricher = Mock(SlotEnricher.class)
        def controller = new SlotController(scheduleService, apptService, slotGenerator, slotEnricher)
        def response = Mock(HttpServletResponse.class)
        def start = LocalDate.of(2020, 5, 20)
        def end = LocalDate.of(2020, 5, 22)
        def schedule = Mock(Schedule.class)
        def dailySlots = new ArrayList()
        def day1 = new DailySlots(LocalDate.of(2020, 5, 20), SlotHelper.generateSlots(10))
        def day2 = new DailySlots(LocalDate.of(2020, 5, 21), SlotHelper.generateSlots(10))
        dailySlots.add(day1)
        dailySlots.add(day2)
        def appointments = new ArrayList()
        def appt1 = new Appointment(2, LocalDate.of(2020, 5, 20), LocalTime.of(9, 30), LocalTime
                .of(10, 30))
        def appt2 = new Appointment(2, LocalDate.of(2020, 5, 21), LocalTime.of(10, 45), LocalTime
                .of(11, 00))
        appointments.add(appt1)
        appointments.add(appt2)

        when:
        scheduleService.loadSchedule(2) >> schedule
        slotGenerator.getSlots(start, end, schedule) >> dailySlots
        apptService.loadAppointments(2, start, end) >> appointments
        def returnedSlots = controller.getSlots("2", "2020-05-20", "2020-05-21", response)

        then:
        1 * slotEnricher.populateSlots(dailySlots, appointments)
        1 * response.setStatus(HttpServletResponse.SC_OK)
        returnedSlots.size() == 2
    }

}
