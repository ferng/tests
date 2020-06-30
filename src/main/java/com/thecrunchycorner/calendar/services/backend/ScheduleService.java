package com.thecrunchycorner.calendar.services.backend;

import com.thecrunchycorner.calendar.domain.Schedule;
import com.thecrunchycorner.calendar.repository.ScheduleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ScheduleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleService.class);

    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

    public Schedule loadSchedule(long consultantId) {
        LOGGER.debug("Loading consultant schedule for consultant{}", consultantId);

        return repository.findByConsultantId(consultantId).orElse(new Schedule());
    }

    // This would be retrieved from another backend exactly as with loadSchedule above,
    // could be cached if values don't change regularly
    public List<Integer> loadSlotSizes(long consultantId) {
        ArrayList<Integer> slotSizes = new ArrayList<>();
        slotSizes.add(15);
        slotSizes.add(60);
        return slotSizes;
    }

    public int getLargestSlot(long consultantId) {
        List<Integer> slotSizes = loadSlotSizes(consultantId);
        return slotSizes.stream()
                .mapToInt(slot -> slot)
                .max()
                .orElseThrow(NoSuchElementException::new);
    }

    public int getSmallestSlot(long consultantId) {
        List<Integer> slotSizes = loadSlotSizes(consultantId);
        return slotSizes.stream()
                .mapToInt(slot -> slot)
                .min()
                .orElseThrow(NoSuchElementException::new);
    }

}
