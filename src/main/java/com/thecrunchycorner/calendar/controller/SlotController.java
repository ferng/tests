package com.thecrunchycorner.calendar.controller;

import com.thecrunchycorner.calendar.domain.Schedule;
import com.thecrunchycorner.calendar.services.backend.ScheduleService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slots")
public class SlotController {

    @Autowired
    private ScheduleService service;

    @GetMapping(value = "/")
    public Schedule getSlots(@RequestParam(defaultValue = "1") String consultantId,
                             HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);

        return service.loadSchedule(Long.parseLong(consultantId));
    }
}
