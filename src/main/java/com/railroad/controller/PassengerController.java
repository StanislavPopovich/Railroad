package com.railroad.controller;

import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.train.GlobalTrainsTicketDto;
import com.railroad.dto.train.TrainTicketDto;
import com.railroad.dto.train.TrainTransferTicketDto;
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
@SessionAttributes({"trainTicket","passenger" })
public class PassengerController {


    @Autowired
    private BusinessService businessService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


    @PostMapping(value = "passenger/add")
    public String getAddPassengerPage(@ModelAttribute("trainTicket") GlobalTrainsTicketDto globalTrainsTicketDto,
                                      Model model){
        Collection<GrantedAuthority> authorities = (Collection)SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        System.out.println("\n");
        System.out.println(globalTrainsTicketDto.toString());
        System.out.println("\n");
        for(GrantedAuthority authority: authorities){
            if(authority.getAuthority().equals("ROLE_ADMIN") || authority.getAuthority().equals("ROLE_MODERATOR")){
                return "redirect:/railroad/user";
            }
        }
        model.addAttribute("passenger", new PassengerDto());
        model.addAttribute("trainTicket", globalTrainsTicketDto);
        return "addPassengerPage";
    }

    @PostMapping(value = "passenger/add/result")
    public String getAddPassengerPageResult(@Valid @ModelAttribute("passenger") PassengerDto passengerDto,
                                            BindingResult bindingResult,
                                            @ModelAttribute("trainTicket") GlobalTrainsTicketDto globalTrainsTicketDto,
                                            Model model){
        if(bindingResult.hasErrors()){
            return "addPassengerPage";
        }
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        /*if(businessService.isPassengerAlreadyBoughtTicket(trainTicketDto, passengerDto)){
            entity.addAttribute("alreadyBought", true);
            return "addPassengerPage";
        }*/
        return "redirect:/railroad/ticket/buy";
    }



    @PostMapping(value = "passenger/train/all")
    public @ResponseBody List<PassengerDto> getTrainPassengers(@RequestParam String trainNumber, @RequestParam Date date){
        return businessService.getTrainPassengers(new Integer(trainNumber), date);
    }




}
