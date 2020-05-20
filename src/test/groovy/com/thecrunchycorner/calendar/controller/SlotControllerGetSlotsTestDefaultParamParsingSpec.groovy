package com.thecrunchycorner.calendar.controller


import com.thecrunchycorner.calendar.services.SlotEnricher
import com.thecrunchycorner.calendar.services.SlotGenerator
import com.thecrunchycorner.calendar.services.backend.AppointmentService
import com.thecrunchycorner.calendar.services.backend.ScheduleService
import spock.lang.Specification

import javax.servlet.http.HttpServletResponse
import java.time.LocalDate

class SlotControllerGetSlotsTestDefaultParamParsingSpec extends Specification {
    def test() {
        given:
        def scheduleService = Mock(ScheduleService.class)
        def apptService = Mock(AppointmentService.class)
        def slotGenerator = Mock(SlotGenerator.class)
        def slotEnricher = Mock(SlotEnricher.class)
        def controller = new SlotController(scheduleService, apptService, slotGenerator, slotEnricher)
        def response = Mock(HttpServletResponse.class)

        when:
        controller.getSlots("1", "1970-01-01", "1970-01-01", response)

        then:
        1 * scheduleService.loadSchedule(1)
        1 * slotGenerator.getSlots(LocalDate.now(), LocalDate.now().plusDays(6), null)
        1 * response.setStatus(HttpServletResponse.SC_OK)
    }
}
