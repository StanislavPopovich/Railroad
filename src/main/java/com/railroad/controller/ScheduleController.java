package com.railroad.controller;

import com.railroad.dto.schedule.ScheduleDto;
import com.railroad.dto.schedule.ScheduleTrainDto;
import com.railroad.dto.schedule.ScheduleUpdateDto;
import com.railroad.dto.train.TrainDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.ScheduleService;
import com.railroad.service.api.TrainService;
import com.railroad.service.impl.TableService;
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

    @Autowired
    private TableService tableService;

    @GetMapping(value = "/schedule")
    public String getSchedulePage(){
        return "schedulePage";
    }


    @GetMapping(value = "/schedule/add")
    public String getAddSchedulePage(Model model){
        model.addAttribute("trainsNumbers",trainService.getAllTrainsNumbers());
        return "addSchedulePage";
    }

    @GetMapping(value = "/schedule/edit")
    public String getEditSchedulePage(Model model){
        model.addAttribute("trainsNumbers",trainService.getAllTrainsNumbers());
        model.addAttribute("departDate", "");
        return "editSchedulePage";
    }


    @PostMapping(value = "/schedule/add")
    public void addTrainToSchedule(@RequestBody ScheduleTrainDto scheduleTrainDto) {
        logger.info(scheduleTrainDto.toString());
        scheduleService.save(scheduleTrainDto);
        tableService.updateSchedule();
    }

    @PostMapping(value = "/schedule/get-train")
    public @ResponseBody
    TrainDto getTrains(@RequestParam String trainNumber) {
        return trainService.getTrainDtoByNumber(new Integer(trainNumber));
    }

    @PostMapping(value = "/schedule/get-schedule-for-train")
    public @ResponseBody
    ScheduleUpdateDto getScheduleForTrain(@RequestParam String trainNumber, @RequestParam Date date) {
        return businessService.getScheduleUpdateDtosByTrainAdnDate(new Integer(trainNumber), date);
    }

    @PostMapping(value = "/schedule/update")
    public void updateSchedule(@RequestBody ScheduleUpdateDto scheduleUpdateDto) {
        logger.info("update");
        scheduleService.updateSchedule(scheduleUpdateDto);
        tableService.updateSchedule();
    }

    @GetMapping(value = "/schedule/delete")
    public String deleteTrainFromSchedule(Model model){
        model.addAttribute("trainsNumbers",trainService.getAllTrainsNumbers());
        model.addAttribute("departDate", "");
        return "deleteSchedulePage";
    }

    @PostMapping(value = "schedule/delete-train-success")
    public void deleteSchedule(@RequestParam String trainNumber, @RequestParam String date) {
        logger.info(trainNumber + " ------- " + date);
        businessService.removeTrainFromSchedule(new Integer(trainNumber), date);
        tableService.updateSchedule();
    }

    @PostMapping(value = "/schedule/get-depart-dates")
    public @ResponseBody
    List<String> getDepartingDatesForTrain(@RequestParam String trainNumber) {
        return businessService.getDepartDatesForTrain(new Integer(trainNumber));
    }

    @InitBinder
    public void initFindDate(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    /*@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }*/



}
