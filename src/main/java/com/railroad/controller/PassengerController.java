package com.railroad.controller;

import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.train.TrainTicketDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value =  "/railroad")
@SessionAttributes({"trainForm","passenger"})
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


    @GetMapping(value = "passenger/add")
    public String getAddPassengerPage(@ModelAttribute("trainForm") TrainTicketDto trainTicketDto, Model model){
        Collection<GrantedAuthority> authorities = (Collection)SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        for(GrantedAuthority authority: authorities){
            if(authority.getAuthority().equals("ROLE_ADMIN") || authority.getAuthority().equals("ROLE_MODERATOR")){
                return "redirect:/railroad/user";
            }
        }
        model.addAttribute("passenger", new PassengerDto());
        return "addPassengerPage";
    }

    @PostMapping(value = "passenger/add")
    public String getAddPassengerPageResult(@Valid @ModelAttribute("passenger") PassengerDto passengerDto,
                                            BindingResult bindingResult,
                                            @ModelAttribute("trainForm") TrainTicketDto trainTicketDto,
                                            Model model){
        if(bindingResult.hasErrors()){
            return "addPassengerPage";
        }
        if(businessService.isPassengerAlreadyBoughtTicket(trainTicketDto, passengerDto)){
            model.addAttribute("alreadyBought", true);
            return "addPassengerPage";
        }
        return "redirect:/railroad/ticket/buy";
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
