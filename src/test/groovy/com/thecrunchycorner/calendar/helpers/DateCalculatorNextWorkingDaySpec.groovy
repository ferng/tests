package com.thecrunchycorner.calendar.helpers

import spock.lang.Specification

import java.time.LocalDate

class DateCalculatorNextWorkingDaySpec extends Specification{
    private static MONDAY = LocalDate.of(2020, 5, 4);
    private static TUESDAY = LocalDate.of(2020, 5, 5);
    private static WEDNESDAY = LocalDate.of(2020, 5, 6);
    private static THURSDAY = LocalDate.of(2020, 5, 7);
    private static FRIDAY = LocalDate.of(2020, 5, 8);
    private static SATURDAY = LocalDate.of(2020, 5, 9);
    private static SUNDAY = LocalDate.of(2020, 5, 10);
    private static NEXT_MONDAY = LocalDate.of(2020, 5, 11);


    def test() {
        given:
        def calculator = new DateCalculator()

        when:
        def actualNextWorkingDay = calculator.nextWorkingDay(day)

        then:
        actualNextWorkingDay == expected

        where:
        day         | expected
        MONDAY      | TUESDAY
        TUESDAY     | WEDNESDAY
        WEDNESDAY   | THURSDAY
        THURSDAY    | FRIDAY
        FRIDAY      | NEXT_MONDAY
        SATURDAY    | NEXT_MONDAY
        SUNDAY      | NEXT_MONDAY




    }
}
