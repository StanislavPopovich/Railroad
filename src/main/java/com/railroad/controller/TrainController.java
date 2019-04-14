package com.railroad.controller;

import com.railroad.dto.TrainDto;
import com.railroad.dto.TrainSearchDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.StationService;
import com.railroad.service.api.TrainService;
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

    @Autowired
    private TrainService trainService;
    @Autowired
    private StationService stationService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


    @GetMapping(value = "/train/all")
    public String getAllTrains(Model model) {
        model.addAttribute("trains", trainService.getAll());
        return "trainPage";
    }

    @GetMapping(value = "/train/add")
    public ModelAndView addTrainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainForm", new TrainDto());
        modelAndView.addObject("departStation", "");
        modelAndView.addObject("arrivalStation", "");
        modelAndView.addObject("stations", stationService.getAllStationsName());
        modelAndView.setViewName("addTrain");
        return modelAndView;
    }

    @PostMapping(value = "/train/all-routes")
    public @ResponseBody List<String> getAllRoutesForStartAndEndStations(@RequestParam String departStation,
                                                                         @RequestParam String arrivalStation ) {
        List<String> routes = businessService.getAllRoutes(departStation, arrivalStation);
        return routes;
    }

    @PostMapping(value = "/find-trains-with-date")
    public @ResponseBody List<TrainSearchDto> getTrains(@RequestParam String departStation,
                                                        @RequestParam String arrivalStation,
                                                        @RequestParam Date date){
        List<TrainSearchDto> trainSearchDtos = businessService.getDirectTrains(departStation,arrivalStation,date);
        return trainSearchDtos;
    }


    @PostMapping(value = "/train/add")
    public String resultAddTrainPage(@ModelAttribute("trainForm") TrainDto trainDto) {
        trainService.save(trainDto);
        return "redirect:/railroad/user";
    }
}
