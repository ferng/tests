package com.thecrunchycorner.calendar.controller;

import com.thecrunchycorner.calendar.domain.Appointment;
import com.thecrunchycorner.calendar.domain.DailySlots;
import com.thecrunchycorner.calendar.domain.Schedule;
import com.thecrunchycorner.calendar.services.SlotGenerator;
import com.thecrunchycorner.calendar.services.backend.AppointmentService;
import com.thecrunchycorner.calendar.services.backend.ScheduleService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slots")
public class SlotController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private AppointmentService apptService;

    @Autowired
    private SlotGenerator generator;

    @GetMapping(value = "/")
    public List<DailySlots> getSlots(@RequestParam(defaultValue = "1") String consultantId,
                                     @RequestParam(defaultValue = "1970-01-01") String rangeStart,
                                     @RequestParam(defaultValue = "1970-01-01") String rangeEnd,
                                     HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);

        long consId = Long.parseLong(consultantId);
        Schedule schedule =  scheduleService.loadSchedule(consId);
        List<DailySlots> slots = generator.getSlots(schedule);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        LocalDate start;
        LocalDate end;
        if (rangeStart.equals("1970-01-01") && rangeEnd.equals("1970-01-01")) {
            start = LocalDate.now().plusDays(1);
            end = start.plusDays(6);
        } else {
            start = LocalDate.parse(rangeStart, formatter);
            end = LocalDate.parse(rangeEnd, formatter);
        }

        List<Appointment> appointments = apptService.loadAppointments(consId, start, end);

        return slots;
    }
}
