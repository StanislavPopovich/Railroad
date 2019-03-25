package com.railroad.controller;

import com.railroad.dto.TrainDto;
import com.railroad.dto.UserDto;
import com.railroad.service.api.BusinessService;
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
@RequestMapping(value = {"/railroad/moderator", "/railroad/admin"})
public class TrainController extends BaseController {

    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "/all-trains")
    public String showAllTrains(Model model){
        List<TrainDto> trainDtos = businessService.getAllTrains();
        model.addAttribute("trains", trainDtos);
        return "trains_page";
    }
    @GetMapping(value = "/add-train")
    public ModelAndView showAddTrainPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("train", new TrainDto());
        modelAndView.addObject("stations", businessService.getAllNamesStations());
        modelAndView.setViewName("add_train");
        return modelAndView;
    }

    @PostMapping(value = "/add-train/add-train-route")
    public ModelAndView showAddRouteToTrainPage(@ModelAttribute("train") TrainDto trainDto){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("train", trainDto);
        modelAndView.addObject("routes", businessService.getAllRoutes(trainDto.getStations().getFirst(),
                trainDto.getStations().getLast()));
        modelAndView.setViewName("add_train_route");
        return modelAndView;
    }
    @PostMapping(value = "/add-train")
    public String showAddTrainPage(@ModelAttribute("train") TrainDto trainDto){
        businessService.saveTrain(trainDto);
        return getUrl() + "all-trains";
    }
}
