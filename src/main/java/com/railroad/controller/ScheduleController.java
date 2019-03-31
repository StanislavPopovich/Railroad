package com.railroad.controller;

import com.railroad.dto.ScheduleDto;
import com.railroad.service.api.BusinessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = {"/railroad/moderator", "/railroad/admin"})
public class ScheduleController extends BaseController {
    private static final Logger logger = Logger.getLogger(ScheduleController.class);
    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "/schedule")
    public String showSchedule(){
        return "schedule_page";
    }

    @GetMapping(value = "/add-schedule")
    public String addSchedule(Model model){
        model.addAttribute("stations", businessService.getAllNamesStations());
        model.addAttribute("schedule", new ScheduleDto());
        return "add_schedule_page";
    }

    @PostMapping(value = "/add-schedule")
    public String addSchedule(@ModelAttribute("schedule") ScheduleDto scheduleDto){
        logger.info(scheduleDto.toString());
        businessService.saveSchedule(scheduleDto);
        return getUrl() + "schedule";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
