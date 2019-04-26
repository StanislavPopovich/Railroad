package com.railroad.controller;

import com.railroad.dto.PassengerDto;
import com.railroad.dto.TicketDto;
import com.railroad.dto.TrainTicketDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private TrainService trainService;

    @Autowired
    private BusinessService businessService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


    @PostMapping(value = "passenger/add")
    public String getAddPassengerPage(@ModelAttribute("trainForm") TrainTicketDto trainTicketDto,
                                            ModelMap modelMap, Model model){
        Collection<GrantedAuthority> authorities = (Collection)SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        for(GrantedAuthority authority: authorities){
            if(authority.getAuthority().equals("ROLE_ADMIN") || authority.getAuthority().equals("ROLE_MODERATOR")){
                return "redirect:/railroad/user";
            }
        }
        TicketDto ticketDto = new TicketDto(trainTicketDto, new PassengerDto());
        modelMap.addAttribute("ticket", ticketDto);
        model.addAttribute(modelMap);
        return "addPassengerPage";
    }

    @GetMapping(value = "passenger/train")
    public String getTrainPassengersPage(Model model){
        model.addAttribute("trainsNumbers",trainService.getAllTrainsNumbers());
        model.addAttribute("departDate", "");
        return "trainPassengersPage";
    }

    @PostMapping(value = "passenger/train/all")
    public @ResponseBody List<PassengerDto> getTrainPassengers(@RequestParam String trainNumber, @RequestParam Date date){
        return businessService.getTrainPassengers(new Integer(trainNumber), date);
    }




}
