package com.railroad.controller;

import com.railroad.dto.route.RouteDto;
import com.railroad.dto.train.TrainDto;
import com.railroad.dto.train.TrainTargetDto;
import com.railroad.dto.train.TrainTransferTargetDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value =  "/railroad")
public class TrainController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private SearchTrainService searchTrainService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RouteService routeService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


    @GetMapping(value = "/train/all")
    public String getTrainsPage() {
        return "trainsPage";
    }

    @GetMapping(value = "/trains/get-all")
    public @ResponseBody List<TrainDto> getAllTrains() throws RailroadDaoException {
        return trainService.getAll();
    }

    @GetMapping(value = "/train/add")
    public ModelAndView addTrainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainForm", new TrainDto());
        modelAndView.setViewName("addTrainPage");
        return modelAndView;
    }

    @PostMapping(value = "/train/all-routes")
    public @ResponseBody List<RouteDto> getAllRoutesForDepartAndArrivalStations(@RequestParam String departStation,
                                                                         @RequestParam String arrivalStation ) throws RailroadDaoException {
        return routeService.getAllRoutes(departStation, arrivalStation);
    }

    @PostMapping(value = "/find-direct-trains")
    public @ResponseBody List<TrainTargetDto> getTargetTrains(@RequestParam String departStation,
                                                              @RequestParam String arrivalStation,
                                                              @RequestParam Date date) throws RailroadDaoException {
        return searchTrainService.getDirectTrains(departStation,arrivalStation,date);
    }
    @PostMapping(value = "/find-transfer-trains")
    public @ResponseBody List<TrainTransferTargetDto> getTargetTransferTrains(@RequestParam String departStation,
                                                                              @RequestParam String arrivalStation,
                                                                              @RequestParam Date date) throws RailroadDaoException {
        /*searchTrainService.getAlternativeTrasfer(departStation, arrivalStation, date);*/
        return searchTrainService.getTransferTrains(departStation, arrivalStation, date);
    }

    @PostMapping(value = "/train/add")
    public String addTrainResult(@ModelAttribute("trainForm") TrainDto trainDto) throws RailroadDaoException {
        trainService.save(trainDto);
        return "redirect:/railroad/user";
    }

    @GetMapping(value = "/train/all-from-schedule")
    public @ResponseBody List<Integer> getTrainsNumbersFromSchedule() throws RailroadDaoException {
        return scheduleService.getTrainsNumberFromSchedule();
    }

    @GetMapping(value = "/train/all-numbers")
    public @ResponseBody List<Integer> getTrainsNumbers() throws RailroadDaoException {
        return trainService.getAllTrainsNumbers();
    }

    @PostMapping(value = "/train/by-number")
    public @ResponseBody TrainDto getTrainByNumber(@RequestParam String trainNumber) throws RailroadDaoException {
        return trainService.getTrainDtoByNumber(new Integer(trainNumber));
    }

    @PostMapping(value = "/train/add-new-train")
    public @ResponseBody boolean[][] addNewTrain(@RequestBody  TrainDto trainDto) throws RailroadDaoException {
        return businessService.saveTrain(trainDto);
    }

    @GetMapping(value = "train/passengers")
    public String getTrainPassengersPage(){
        return "trainPassengersPage";
    }
}
