package com.railroad.controller;

import com.railroad.dto.ScheduleDto;
import com.railroad.dto.TrainDto;
import com.railroad.dto.TrainScheduleDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.ScheduleService;
import com.railroad.service.api.StationService;
import com.railroad.service.api.TrainService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private TrainService trainService;
    @Autowired
    private BusinessService businessService;

    /*@GetMapping(value = "/schedule")
    public String getSchedulePage(Model model){
        model.addAttribute("stations", stationService.getAllStationsName());
        return "schedulePage";
    }

    @PostMapping(value = "/schedule/trains-for-station")
    public @ResponseBody
    List<TrainScheduleDto> getTrains(@RequestParam String station, Date date) {
        return businessService.getTrainsFromSchedule(station, date);
    }*/

    @GetMapping(value = "/schedule/add")
    public String addTrainToSchedule(Model model){
        model.addAttribute("trainsNumbers",trainService.getAllTrainsNumbers());
        return "addSchedulePage";
    }


    @PostMapping(value = "/schedule/add-success")
    public @ResponseBody
    String addTrainToScheduleResult(@RequestBody ScheduleDto scheduleDto) {
        logger.info(scheduleDto);
        scheduleService.save(scheduleDto);
        return "success";
    }

    @PostMapping(value = "/schedule/get-train")
    public @ResponseBody
    TrainDto getTrains(@RequestParam String trainNumber) {
        return trainService.getTrainDtoByNumber(new Integer(trainNumber));
    }

    @GetMapping(value = "/schedule/delete")
    public String deleteTrainFromSchedule(Model model){
        model.addAttribute("trainsNumbers",trainService.getAllTrainsNumbers());
        model.addAttribute("departDate", "");
        businessService.removeTrainFromSchedule(7, "2019-05-04");
        return "deleteSchedulePage";
    }

    @PostMapping(value = "/schedule/get-depart-dates")
    public @ResponseBody
    List<String> getDepartingDatesForTrain(@RequestParam String trainNumber) {
        return businessService.getDepartDatesForTrain(new Integer(trainNumber));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
