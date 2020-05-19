package com.thecrunchycorner.calendar.repository;

import com.thecrunchycorner.calendar.domain.Appointment;
import com.thecrunchycorner.calendar.domain.Schedule;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, String> {

    Optional<List<Appointment>> findByConsultantIdAndAppDateBetween(long consultantId, LocalDate start
            , LocalDate end);
}
