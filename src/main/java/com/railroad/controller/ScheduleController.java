package com.railroad.controller;

import com.railroad.dto.ScheduleDto;
import com.railroad.dto.ScheduleUpdateDto;
import com.railroad.dto.TrainDto;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/railroad")
public class ScheduleController {
    private static final Logger logger = Logger.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TrainService trainService;
    @Autowired
    private BusinessService businessService;


    @GetMapping(value = "/schedule/add")
    public String getAddSchedulePage(Model model){
        model.addAttribute("trainsNumbers",trainService.getAllTrainsNumbers());
        return "addSchedulePage";
    }

    @GetMapping(value = "/schedule/edit")
    public String getEditSchedulePage(Model model){
        model.addAttribute("trainsNumbers",trainService.getAllTrainsNumbers());
        return "editSchedulePage";
    }


    @PostMapping(value = "/schedule/add")
    public void addTrainToSchedule(@RequestBody ScheduleDto scheduleDto) {
        logger.info(scheduleDto);
        scheduleService.save(scheduleDto);
    }

    @PostMapping(value = "/schedule/update")
    public void updateTrainToSchedule(@RequestBody ScheduleUpdateDto scheduleUpdateDto) {
        logger.info(scheduleUpdateDto);
        businessService.updateSchedule(scheduleUpdateDto);
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
        return "deleteSchedulePage";
    }

    @PostMapping(value = "schedule/delete-train-success")
    public @ResponseBody
    String deleteSchedule(@RequestParam String trainNumber, @RequestParam String date) {
        businessService.removeTrainFromSchedule(new Integer(trainNumber), date);
        return "userPage";
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

    @InitBinder
    public void initFindDate(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

}
