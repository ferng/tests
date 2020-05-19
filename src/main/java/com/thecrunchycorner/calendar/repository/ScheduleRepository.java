package com.thecrunchycorner.calendar.repository;

import com.thecrunchycorner.calendar.domain.Schedule;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, String> {

    Optional<Schedule> findByConsultantId(long consultantId);
}
