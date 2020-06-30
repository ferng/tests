package com.thecrunchycorner.calendar.services.backend;

import com.thecrunchycorner.calendar.domain.Appointment;
import com.thecrunchycorner.calendar.repository.AppointmentRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AppointmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentService.class);

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> loadAppointments(long consultantId, LocalDate start, LocalDate end) {
        LOGGER.debug("Loading consultant appointments for consultant{}", consultantId);

        return repository.findByConsultantIdAndAppDateBetween(consultantId, start, end).orElse(new ArrayList<>());
    }

    @Transactional
    public void saveAppointment(Appointment appointment) {
        LOGGER.debug("Attempting to create appointment for {}", appointment.getCustomerId());
        repository.save(appointment);
    }

    @Transactional
    public Long deleteAppointment(long id) {
        LOGGER.debug("Attempting to delete appointment {}", id);
        return repository.removeById(id);
    }
}
