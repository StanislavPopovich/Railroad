package com.railroad.controller;

import com.railroad.dto.PassengerDto;
import com.railroad.dto.TicketDto;
import com.railroad.dto.TrainDto;
import com.railroad.dto.TrainTicketDto;
import com.railroad.service.api.BusinessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value =  "/railroad")
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


    @PostMapping(value = "passenger/add")
    public ModelAndView addPassengerPage(@ModelAttribute("trainForm") TrainTicketDto trainTicketDto, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        TicketDto ticketDto = new TicketDto(trainTicketDto, new PassengerDto());
        modelMap.addAttribute("ticket", ticketDto);
        modelAndView.addAllObjects(modelMap);
        modelAndView.setViewName("addPassengerPage");
        return modelAndView;
    }

    @GetMapping(value = "user/passenger/all")
    public String getPassengersOfUser(Model model){
        model.addAttribute("passengers", businessService.getPassengersOfCurrentUser());
        return "userPassengersPage";
    }
}
