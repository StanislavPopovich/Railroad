package com.railroad.controller;

import com.railroad.dto.TrainDto;
import com.railroad.service.api.BusinessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value =  "/railroad")
public class TrainController {

    private static final Logger logger = Logger.getLogger(TrainController.class);

    @Autowired
    private BusinessService businessService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


    @GetMapping(value = "/user/trains")
    public String getAllTrains(Model model) {
        List<TrainDto> trainDtos = businessService.getAllTrains();
        model.addAttribute("trains", trainDtos);
        return "trainPage";
    }

    @GetMapping(value = "/user/add-train")
    public ModelAndView getAddTrainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("train", new TrainDto());
        modelAndView.addObject("startStation", new String());
        modelAndView.addObject("endStation", new String());
        modelAndView.addObject("stations", businessService.getAllNamesStations());
        modelAndView.setViewName("addTrain");
        return modelAndView;
    }

    @PostMapping(value = "/user/all-routes")
    public @ResponseBody List<String> getAllRoutesForStartAndEndStations(@RequestParam String start,@RequestParam String end ) {
        List<String> routes = businessService.getAllRoutes(start, end);
        return routes;
    }

    @PostMapping(value = "/find-trains-with-date")
    public @ResponseBody List<TrainDto> getTrains(@RequestParam String start,@RequestParam String end,
                                                   @RequestParam Date date){
        List<TrainDto> trainDtos = businessService.getDirectTrains(start,end,date);
        return trainDtos;
    }


    @PostMapping(value = "/user/add-train")
    public String resultAddTrainPage(@ModelAttribute("train") TrainDto trainDto) {
        businessService.saveTrain(trainDto);
        return "redirect:/railroad/user";
    }
}
