package com.thecrunchycorner.calendar.controller


import com.thecrunchycorner.calendar.services.SlotEnricher
import com.thecrunchycorner.calendar.services.SlotGenerator
import com.thecrunchycorner.calendar.services.backend.AppointmentService
import com.thecrunchycorner.calendar.services.backend.ScheduleService
import spock.lang.Specification

import javax.servlet.http.HttpServletResponse
import java.time.LocalDate

class SlotControllerGetSlotsLoadSlotsSpec extends Specification {
    def test() {
        given:
        def scheduleService = Mock(ScheduleService.class)
        def apptService = Mock(AppointmentService.class)
        def slotGenerator = Mock(SlotGenerator.class)
        def slotEnricher = Mock(SlotEnricher.class)
        def controller = new SlotController(scheduleService, apptService, slotGenerator, slotEnricher)
        def response = Mock(HttpServletResponse.class)
        def start = LocalDate.of(2020, 9, 11)
        def end = LocalDate.of(2020, 9, 14)

        when:
        controller.getSlots("2", "2020-09-11", "2020-09-13", response)

        then:
        1 * scheduleService.loadSchedule(2)
        1 * slotGenerator.getSlots(LocalDate.of(2020, 9, 11), LocalDate.of(2020, 9, 14), null)
        1 * apptService.loadAppointments(2, start, end)
        1 * slotEnricher.populateSlots(null, null)
        1 * response.setStatus(HttpServletResponse.SC_OK)
    }

}
