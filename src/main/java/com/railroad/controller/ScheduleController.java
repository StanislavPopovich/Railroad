package com.railroad.controller;

import com.railroad.dto.ScheduleDto;
import com.railroad.dto.TrainScheduleDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.ScheduleService;
import com.railroad.service.api.StationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/railroad")
public class ScheduleController {
    private static final Logger logger = Logger.getLogger(ScheduleController.class);

    @Autowired
    private StationService stationService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "/schedule")
    public String getSchedulePage(Model model){
        model.addAttribute("stations", stationService.getAllStationsName());
        return "schedulePage";
    }

    @PostMapping(value = "/schedule/trains-for-station")
    public @ResponseBody
    List<TrainScheduleDto> getTrains(@RequestParam String station, Date date) {
        return businessService.getTrainsFromSchedule(station, date);
    }

    @GetMapping(value = "/schedule/add")
    public String addSchedule(Model model){
        model.addAttribute("stations", stationService.getAllStationsName());
        model.addAttribute("schedule", new ScheduleDto());
        return "addSchedulePage";
    }

    @PostMapping(value = "/schedule/add")
    public String addSchedule(@ModelAttribute("schedule") ScheduleDto scheduleDto){
        logger.info(scheduleDto.toString());
        scheduleService.save(scheduleDto);
        return "redirect:/railroad/user";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
