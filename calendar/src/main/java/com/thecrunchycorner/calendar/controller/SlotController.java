package com.thecrunchycorner.calendar.controller;

import static java.time.temporal.ChronoUnit.MINUTES;

import com.thecrunchycorner.calendar.domain.Appointment;
import com.thecrunchycorner.calendar.domain.DailySlots;
import com.thecrunchycorner.calendar.domain.Schedule;
import com.thecrunchycorner.calendar.domain.Slot;
import com.thecrunchycorner.calendar.domain.SlotStatus;
import com.thecrunchycorner.calendar.helpers.SlotCalculator;
import com.thecrunchycorner.calendar.services.SlotEnricher;
import com.thecrunchycorner.calendar.services.SlotGenerator;
import com.thecrunchycorner.calendar.services.backend.AppointmentService;
import com.thecrunchycorner.calendar.services.backend.ScheduleService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary")
public class SlotController {

    @Autowired
    private final ScheduleService scheduleService;

    @Autowired
    private final AppointmentService apptService;

    @Autowired
    private final SlotGenerator generator;

    @Autowired
    private final SlotEnricher enricher;

    public SlotController(ScheduleService scheduleService, AppointmentService apptService,
                          SlotGenerator generator, SlotEnricher enricher) {
        this.scheduleService = scheduleService;
        this.apptService = apptService;
        this.generator = generator;
        this.enricher = enricher;
    }


    @GetMapping(value = "/slots/{consultantId}")
    public List<DailySlots> getSlots(@PathVariable String consultantId,
                                     @RequestParam(defaultValue = "1970-01-01") String rangeStart,
                                     @RequestParam(defaultValue = "1970-01-01") String rangeEnd,
                                     HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);

        long parsedConsultantId = Long.parseLong(consultantId);

        LocalDate start;
        LocalDate end;
        if (rangeStart.equals("1970-01-01") && rangeEnd.equals("1970-01-01")) {
            start = LocalDate.now();
            end = start.plusDays(6);
        } else {
            start = LocalDate.parse(rangeStart, DateTimeFormatter.ISO_LOCAL_DATE);
            end = LocalDate.parse(rangeEnd, DateTimeFormatter.ISO_LOCAL_DATE).plusDays(1);
        }

        return loadSlots(parsedConsultantId, start, end);
    }


    @GetMapping(value = "/slot/{consultantId}")
    public Slot checkSlot(@PathVariable String consultantId,
                          @RequestParam(defaultValue = "1970-01-01") String date,
                          @RequestParam(defaultValue = "09:00") String appStart,
                          @RequestParam(defaultValue = "09:15") String appEnd,
                          HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);

        long parsedConsultantId = Long.parseLong(consultantId);
        LocalDate parsedDate;
        LocalTime start;
        LocalTime end;
        if (date.equals("1970-01-01") && appStart.equals("09:00")) {
            parsedDate = LocalDate.now();
            start = LocalTime.now();
            end = LocalTime.now().plusMinutes(15);
        } else {
            parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            start = LocalTime.parse(appStart, DateTimeFormatter.ISO_TIME);
            end = LocalTime.parse(appEnd, DateTimeFormatter.ISO_TIME);
        }

        return getSlotResponse(parsedConsultantId, parsedDate, start, end);
    }


    @PostMapping(value = "/appointment")
    public Slot addAppointment(@RequestBody Appointment appointment,
                               HttpServletResponse response) {

        long requestedApptSize = appointment.getAppStart().until(appointment.getAppEnd(), MINUTES);
        List<Integer> availableSlotSizes =
                scheduleService.loadSlotSizes(appointment.getConsultantId());

        boolean validSlotSize = false;
        for (long size : availableSlotSizes) {
            if (requestedApptSize == size) {
                validSlotSize = true;
                break;
            }
        }

        Slot responseSlot = new Slot(appointment.getAppStart(), appointment.getAppEnd(),
                SlotStatus.BOOKED);
        if (validSlotSize) {
            Slot existingSlot = getSlotResponse(appointment.getConsultantId(),
                    appointment.getAppDate(),
                    appointment.getAppStart(), appointment.getAppEnd());

            if (existingSlot.getStatus() == SlotStatus.BOOKED) {
                responseSlot.setStatus(SlotStatus.BOOKING_FAILED);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else {
                apptService.saveAppointment(appointment);
                response.setStatus(HttpServletResponse.SC_CREATED);
            }
        } else {
            responseSlot.setStatus(SlotStatus.INVALID_SLOT_SIZE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        return responseSlot;
    }

    @DeleteMapping("/appointment/{id}")
    void deleteAppointment(@PathVariable Long id, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        apptService.deleteAppointment(id);
    }


    private Slot getSlotResponse(long parsedConsultantId, LocalDate parsedDate, LocalTime start,
                                 LocalTime end) {
        ArrayList<Slot> slots =
                loadSlots(parsedConsultantId, parsedDate, parsedDate.plusDays(1)).get(0).getSlots();

        Appointment proposed = new Appointment(parsedConsultantId, parsedDate, start, end);
        Slot slotResponse = new Slot(start, end, SlotStatus.FREE);

        for (Slot slot : slots) {
            if (SlotCalculator.slotAlreadyBooked(slot, proposed)) {
                slotResponse.setStatus(SlotStatus.BOOKED);
                break;
            }
        }
        return slotResponse;
    }


    private List<DailySlots> loadSlots(long parsedConsultantId, LocalDate start, LocalDate end) {
        Schedule schedule = scheduleService.loadSchedule(parsedConsultantId);
        List<DailySlots> slots = generator.getSlots(start, end, schedule);

        List<Appointment> appointments = apptService.loadAppointments(parsedConsultantId, start,
                end);

        enricher.populateSlots(slots, appointments);
        return slots;
    }
}
