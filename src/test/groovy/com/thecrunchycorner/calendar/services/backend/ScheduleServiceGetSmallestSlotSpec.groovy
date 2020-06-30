package com.thecrunchycorner.calendar.services.backend

import com.thecrunchycorner.calendar.repository.ScheduleRepository
import spock.lang.Specification

class ScheduleServiceGetSmallestSlotSpec extends Specification {
    def test() {
        given:
        def repository = Mock(ScheduleRepository.class)
        def service = new ScheduleService(repository)

        when:
        def actual = service.getSmallestSlot(1)
        def expected = 15

        then:
        actual == expected
    }
}
