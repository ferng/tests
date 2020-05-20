package com.thecrunchycorner.calendar.controller


import com.thecrunchycorner.calendar.services.SlotEnricher
import com.thecrunchycorner.calendar.services.SlotGenerator
import com.thecrunchycorner.calendar.services.backend.AppointmentService
import com.thecrunchycorner.calendar.services.backend.ScheduleService
import spock.lang.Specification

import javax.servlet.http.HttpServletResponse
import java.time.LocalDate

class SlotControllerDeleteAppointment extends Specification {
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
        controller.deleteAppointment(2L, response)

        then:
        1 * apptService.deleteAppointment(2)
        1 * response.setStatus(HttpServletResponse.SC_NO_CONTENT);

    }

}
