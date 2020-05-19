package com.thecrunchycorner.calendar.services.backend;

import com.thecrunchycorner.calendar.domain.Schedule;
import com.thecrunchycorner.calendar.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ScheduleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleService.class);

    private ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

    public Schedule loadSchedule(long consultantId) {
        LOGGER.debug("Loading consultant schedule for consultant{}", consultantId);

        return repository.findByConsultantId(consultantId).orElse(new Schedule());
    }
}
