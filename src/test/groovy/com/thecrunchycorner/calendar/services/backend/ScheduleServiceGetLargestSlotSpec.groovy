package com.thecrunchycorner.calendar.services.backend

import com.thecrunchycorner.calendar.repository.ScheduleRepository
import spock.lang.Specification

class ScheduleServiceGetLargestSlotSpec extends Specification {
    def test() {
        given:
        def repository = Mock(ScheduleRepository.class)
        def service = new ScheduleService(repository)

        when:
        def actual = service.getLargestSlot(1)
        def expected = 60

        then:
        actual == expected
    }
}
