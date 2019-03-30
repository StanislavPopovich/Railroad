package com.railroad.controller;

import com.railroad.dto.ScheduleDto;
import com.railroad.service.api.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/railroad/moderator", "/railroad/admin"})
public class ScheduleController extends BaseController {
    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "/schedule")
    public String showSchedule(){
        return "schedule_page";
    }

    @GetMapping(value = "/add-schedule")
    public String addSchedule(Model model){
        model.addAttribute("schedule", new ScheduleDto());
        return "add_schedule_page";
    }

    @PostMapping(value = "/add-schedule")
    public String showAddStationPage(@ModelAttribute("station") ScheduleDto scheduleDto){
        businessService.saveSchedule(scheduleDto);
        return getUrl() + "schedule";
    }
}
