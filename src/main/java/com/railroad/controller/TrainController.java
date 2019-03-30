package com.railroad.controller;

import com.railroad.dto.TrainDto;
import com.railroad.service.api.BusinessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = {"/railroad/moderator", "/railroad/admin", "railroad"})
public class TrainController extends BaseController {

    private static final Logger logger = Logger.getLogger(TrainController.class);

    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "/all-trains")
    public String showAllTrains(Model model) {
        List<TrainDto> trainDtos = businessService.getAllTrains();
        model.addAttribute("trains", trainDtos);
        return "trains_page";
    }

    @GetMapping(value = "/add-train")
    public ModelAndView showAddTrainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("train", new TrainDto());
        modelAndView.addObject("startStation", new String());
        modelAndView.addObject("endStation", new String());
        modelAndView.addObject("stations", businessService.getAllNamesStations());
        modelAndView.setViewName("add_train");
        return modelAndView;
    }

    @PostMapping(value = "/all-routes")
    public @ResponseBody List<String> ajax(@RequestParam String start,@RequestParam String end ) {
        List<String> routes = businessService.getAllRoutes(start, end);
        return routes;
    }

    @PostMapping(value = "/dest-station")
    public @ResponseBody String getStation(@RequestParam String start) {
        return getStation(start, businessService.getAllNamesStations());
    }

    @PostMapping(value = "/find-trains")
    public @ResponseBody List<TrainDto> getTrains(@RequestParam String start,@RequestParam String end){
        List<TrainDto> trains = businessService.getDirectTrains(start, end);
        return trains;
    }


    @PostMapping(value = "/add-train")
    public String showAddTrainPage(@ModelAttribute("train") TrainDto trainDto) {
        businessService.saveTrain(trainDto);
        return getUrl() + "all-trains";
    }
}
