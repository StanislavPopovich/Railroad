package com.railroad.controller;

import com.railroad.dto.schedule.ScheduleTrainDto;
import com.railroad.dto.schedule.ScheduleUpdateDto;
import com.railroad.dto.train.TrainDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.ScheduleService;
import com.railroad.service.api.TableService;
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
    public String getAddSchedulePage(Model model) throws RailroadDaoException {
        model.addAttribute("trainsNumbers",trainService.getAllTrainsNumbers());
        return "addSchedulePage";
    }

    @GetMapping(value = "/schedule/edit")
    public String getEditSchedulePage(Model model) throws RailroadDaoException {
        model.addAttribute("trainsNumbers",trainService.getAllTrainsNumbers());
        model.addAttribute("departDate", "");
        return "editSchedulePage";
    }


    @PostMapping(value = "/schedule/add")
    public @ResponseBody boolean addTrainToSchedule(@RequestBody ScheduleTrainDto scheduleTrainDto) throws RailroadDaoException {
        if(!businessService.isTrainAlreadyExistsInSchedule(scheduleTrainDto)){
            scheduleService.save(scheduleTrainDto);
            tableService.updateSchedule();
            return true;
        }else{
           return false;
        }
    }

    @PostMapping(value = "/schedule/get-train")
    public @ResponseBody
    TrainDto getTrains(@RequestParam String trainNumber) throws RailroadDaoException {
        return trainService.getTrainDtoByNumber(new Integer(trainNumber));
    }

    @PostMapping(value = "/schedule/get-schedule-for-train")
    public @ResponseBody
    ScheduleUpdateDto getScheduleForTrain(@RequestParam String trainNumber, @RequestParam Date date) throws RailroadDaoException {
        return businessService.getScheduleUpdateDtosByTrainAdnDate(new Integer(trainNumber), date);
    }

    @PostMapping(value = "/schedule/update")
    public @ResponseBody boolean updateSchedule(@RequestBody ScheduleUpdateDto scheduleUpdateDto) throws RailroadDaoException {
        if(!businessService.isTrainAlreadyExistsInSchedule(scheduleUpdateDto)){
            scheduleService.updateSchedule(scheduleUpdateDto);
            tableService.updateSchedule();
            return true;
        }else{
            return false;
        }
    }

    @GetMapping(value = "/schedule/delete")
    public String deleteTrainFromSchedule(Model model) throws RailroadDaoException {
        model.addAttribute("trainsNumbers",trainService.getAllTrainsNumbers());
        model.addAttribute("departDate", "");
        return "deleteSchedulePage";
    }

    @PostMapping(value = "schedule/delete-train-success")
    public void deleteSchedule(@RequestParam String trainNumber, @RequestParam String date) throws RailroadDaoException {
        businessService.removeTrainFromSchedule(new Integer(trainNumber), date);
        tableService.updateSchedule();
    }

    @PostMapping(value = "/schedule/get-depart-dates")
    public @ResponseBody
    List<String> getDepartingDatesForTrain(@RequestParam String trainNumber) throws RailroadDaoException {
        return businessService.getDepartDatesForTrain(new Integer(trainNumber));
    }

    @InitBinder
    public void initFindDate(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
