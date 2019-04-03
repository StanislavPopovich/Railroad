package com.railroad.controller;

import com.railroad.dto.PassengerDto;
import com.railroad.service.api.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/railroad/user")
public class TicketController {

    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "/buy-ticket/{train_number}/{date}")
    public ModelAndView buyTicket(@PathVariable("train_number") String train_number,
                                  @PathVariable("date") String date){
        ModelAndView modelAndView = new ModelAndView();
        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setTrainNumber(train_number);
        passengerDto.setDepartDate(date);
        modelAndView.addObject("passenger", passengerDto);
        modelAndView.setViewName("buyTicketPage");
        return modelAndView;
    }

    @PostMapping(value = "/buy-ticket/result")
    public String buyTicket(@ModelAttribute("passenger") PassengerDto passengerDto){
        System.out.println("\n" + " -----" + "\n");

        businessService.saveTicketAndPassenger(passengerDto);
        return "redirect:/railroad/user";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
