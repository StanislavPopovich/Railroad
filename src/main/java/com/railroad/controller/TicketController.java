package com.railroad.controller;

import com.railroad.dto.PassengerDto;
import com.railroad.service.api.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/railroad/user")
public class TicketController {

    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "/buy-ticket/{train_number}/{date}")
    public ModelAndView buyTicket(@PathVariable("train_number") String train_number,
                                  @PathVariable("date") String date){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("passenger", new PassengerDto());
        modelAndView.addObject("train",train_number);
        modelAndView.addObject("departDate",date);
        modelAndView.setViewName("buyTicketPage");
        return modelAndView;
    }

    @PostMapping(value = "/buy-ticket/{train_number}/{date}")
    public String buyTicket(@PathVariable("train_number") String train_number,
                            @PathVariable("date") String date, @ModelAttribute("passenger") PassengerDto passengerDto){
        System.out.println("\n" + " -----" + "\n");
        businessService.saveTicketAndPassenger(passengerDto);
        return "userPage";
    }
}
