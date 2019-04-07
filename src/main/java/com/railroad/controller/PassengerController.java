package com.railroad.controller;

import com.railroad.dto.PassengerDto;
import com.railroad.dto.TrainDto;
import com.railroad.service.api.BusinessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

@Controller
@RequestMapping(value =  "/railroad/user")
public class PassengerController {
    private static final Logger logger = Logger.getLogger(PassengerController.class);

    @Autowired
    private BusinessService businessService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping(value = "/add-passenger/{trainNumber}/{date}/{departStation}/{arrivalStation}")
    public ModelAndView buyTicket(@PathVariable("trainNumber") String trainNumber,
                                  @PathVariable("date") String date, @PathVariable("departStation") String departStation,
                                  @PathVariable("arrivalStation") String arrivalStation){
        ModelAndView modelAndView = new ModelAndView();
        TrainDto train = new TrainDto();
        train.setNumber(new Integer(trainNumber));
        train.setDepartDate(date);
        LinkedList<String> stations = new LinkedList<>();
        stations.add(departStation);
        stations.add(arrivalStation);
        train.setStations(stations);
        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setTrain(train);
        modelAndView.addObject("passenger", passengerDto);
        modelAndView.setViewName("addPassengerPage");
        return modelAndView;
    }

}
