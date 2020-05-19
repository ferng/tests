package com.thecrunchycorner.calendar.controller;

import com.thecrunchycorner.calendar.domain.DailySlots;
import com.thecrunchycorner.calendar.domain.Schedule;
import com.thecrunchycorner.calendar.services.SlotGenerator;
import com.thecrunchycorner.calendar.services.backend.ScheduleService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.weaver.patterns.ConcreteCflowPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slots")
public class SlotController {

    @Autowired
    private ScheduleService service;

    @Autowired
    private SlotGenerator generator;

    @GetMapping(value = "/")
    public ArrayList<DailySlots> getSlots(@RequestParam(defaultValue = "1") String consultantId,
                                          HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);

        Schedule schedule =  service.loadSchedule(Long.parseLong(consultantId));
        return generator.getSlots(schedule);
    }
}
