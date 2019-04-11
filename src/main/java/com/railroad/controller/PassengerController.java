package com.railroad.controller;

import com.railroad.dto.PassengerDto;
import com.railroad.dto.TicketDto;
import com.railroad.dto.TrainDto;
import com.railroad.service.api.BusinessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value =  "/railroad/user")
public class PassengerController {
    private static final Logger logger = Logger.getLogger(PassengerController.class);


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


    @PostMapping(value = "/add-passenger")
    public ModelAndView addPassengerPage(@ModelAttribute("train") TrainDto trainDto, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        TicketDto ticketDto = new TicketDto(trainDto, new PassengerDto());
        modelMap.addAttribute("ticket", ticketDto);
        modelAndView.addAllObjects(modelMap);
        modelAndView.setViewName("addPassengerPage");
        return modelAndView;
    }
}
