package com.thecrunchycorner.calendar.services.backend;

import com.thecrunchycorner.calendar.domain.Appointment;
import com.thecrunchycorner.calendar.domain.Slot;
import com.thecrunchycorner.calendar.domain.SlotStatus;
import com.thecrunchycorner.calendar.repository.AppointmentRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AppointmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentService.class);

    private AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> loadAppointments(long consultantId, LocalDate start, LocalDate end) {
        LOGGER.debug("Loading consultant appointments for consultant{}", consultantId);

        return repository.findByConsultantIdAndAppDateBetween(consultantId, start, end).orElse(new ArrayList<>());
    }

    public Appointment checkSlot(long consultantId, LocalDate date, LocalTime start,
                                 LocalTime end) {
        LOGGER.debug("Checking slot status for consultant{}", consultantId);

        return repository.findByConsultantIdAndAppDateAndAppStartBetween(consultantId, date, start
                , end);
    }

    public Appointment saveAppointment(Appointment appointment) {
        repository.save(appointment);
        return appointment;
    }
}
