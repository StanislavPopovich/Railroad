package com.railroad.controller;

import com.railroad.dto.StationDto;
import com.railroad.dto.TrainDto;
import com.railroad.dto.WayDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.StationService;
import com.railroad.service.api.WayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/railroad/moderator")
public class ModeratorController {

    private static final Logger logger = Logger.getLogger(ModeratorController.class);

    @Autowired
    private BusinessService businessService;


    @GetMapping(value = "")
    public String showModeratorPage(){
        return "moderator_page";
    }

    @GetMapping(value = "/all_stations")
    public String showAllStations(Model model){
        model.addAttribute("stations", businessService.getAllStations());
        return "stations_page";
    }


    @GetMapping(value = "/add_station")
    public String showAddStationPage(Model model){
        model.addAttribute("station", new StationDto());
        return "add_station";
    }

    @PostMapping(value = "/add_station")
    public String showAddStationPage(@ModelAttribute("station") StationDto stationDto){
        businessService.saveStation(stationDto);
        return "redirect:/railroad/moderator/all_stations";
    }

    @GetMapping(value = "/all_ways")
    public String showAllWays(Model model){
        model.addAttribute("ways", businessService.getAllWay());
        return "ways_page";
    }

    @GetMapping(value = "/add_way")
    public String showAddWayPage(Model model){
        model.addAttribute("way", new WayDto());
        model.addAttribute("stations", businessService.getAllStations());
        return "add_way";
    }

    @PostMapping(value = "/add_way")
    public String showAddWayPage(@ModelAttribute("way") WayDto wayDto){
        businessService.saveWay(wayDto);
        return "redirect:/railroad/moderator/all_ways";
    }

    @GetMapping(value = "/all_trains")
    public String showAllTrains(Model model){
        List<TrainDto> trainDtos = businessService.getAllTrains();
        model.addAttribute("trains", trainDtos);
        return "trains_page";
    }
    @GetMapping(value = "/add_train")
    public ModelAndView showAddTrainPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("train", new TrainDto());
        modelAndView.addObject("stations", businessService.getAllStations());
        modelAndView.setViewName("add_train");
        return modelAndView;
    }

    @PostMapping(value = "/add_train/add_train_route")
    public ModelAndView showAddRouteToTrainPage(@ModelAttribute("train") TrainDto trainDto){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("train", trainDto);
        modelAndView.addObject("routes", businessService.getAllRoutes(trainDto.getStartStation(),
                trainDto.getEndStation()));
        modelAndView.setViewName("add_train_route");
        return modelAndView;
    }
    @PostMapping(value = "/add_train")
    public String showAddTrainPage(@ModelAttribute("train") TrainDto trainDto){
        businessService.saveTrain(trainDto);
        return "redirect:/railroad/moderator/all_trains";
    }

}
