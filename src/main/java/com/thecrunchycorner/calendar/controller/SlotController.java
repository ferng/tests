package com.thecrunchycorner.calendar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlotController {

    @RequestMapping("/slots")
    public String index() {
        return "These are your slots";
    }
}
