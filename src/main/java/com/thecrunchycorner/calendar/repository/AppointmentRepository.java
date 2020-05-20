package com.thecrunchycorner.calendar.repository;

import com.thecrunchycorner.calendar.domain.Appointment;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, String> {

    Optional<List<Appointment>> findByConsultantIdAndAppDateBetween(long consultantId,
                                                                    LocalDate start, LocalDate end);

    Appointment findByConsultantIdAndAppDateAndAppStartBetween(long consultantId, LocalDate date,
                                                               LocalTime start, LocalTime end);
}
