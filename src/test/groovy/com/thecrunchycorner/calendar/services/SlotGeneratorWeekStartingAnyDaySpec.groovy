package com.thecrunchycorner.calendar.services

import spock.lang.Specification

import java.time.DayOfWeek
import java.time.LocalDate

class SlotGeneratorWeekStartingAnyDaySpec extends Specification {
    def test() {
        given:
        def generator = new SlotGenerator();
        def slots = generator.getSlots(LocalDate.of(2020, 5, 18));
        def firstDay = slots.get(0).getDay()
        def secondDay = slots.get(1).getDay()
        def thirdDay = slots.get(2).getDay()
        def fourthDay = slots.get(3).getDay()
        def fifthDay = slots.get(4).getDay()

        when:
        def expectedFirst
        def expectedSecond
        def expectedThird
        def expectedFourth
        def expectedFifth
        if (firstDay.getDayOfWeek() == today) {
            expectedFirst = firstDay.plusDays(todayOffset)
            expectedSecond = firstDay.plusDays(tomorrow)
            expectedThird = firstDay.plusDays(dayAfter)
            expectedFourth = firstDay.plusDays(dayAfterThat)
            expectedFifth = firstDay.plusDays(lastDay)
        } else {
            expectedFirst = firstDay
            expectedSecond = secondDay
            expectedThird = thirdDay
            expectedFourth = fourthDay
            expectedFifth = fifthDay
        }


        then:
        slots.size() == 5
        firstDay == expectedFirst
        secondDay == expectedSecond
        thirdDay == expectedThird
        fourthDay == expectedFourth
        fifthDay == expectedFifth


        where:
        today               | todayOffset | tomorrow | dayAfter | dayAfterThat | lastDay
        DayOfWeek.MONDAY    | 0           | 1        | 2        | 3            | 4
        DayOfWeek.TUESDAY   | 0           | 1        | 2        | 3            | 6
        DayOfWeek.WEDNESDAY | 0           | 1        | 2        | 5            | 6
        DayOfWeek.THURSDAY  | 0           | 1        | 4        | 5            | 6
        DayOfWeek.FRIDAY    | 0           | 3        | 4        | 5            | 6

    }
}
