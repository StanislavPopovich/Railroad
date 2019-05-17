package com.railroad.controller;

import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.ticket.GlobalTrainsTicketDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.SearchTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value =  "/railroad")
@SessionAttributes({"trainTicket","passenger"})
public class PassengerController {


    @Autowired
    private BusinessService businessService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping(value = "passenger/add")
    public String getAddPassengerPage(Model model){
        if(checkRole()){
            return "redirect:/railroad/user";
        }
        model.addAttribute("passenger", new PassengerDto());
        return "addPassengerPage";
    }

    private boolean checkRole(){
        Collection<GrantedAuthority> authorities = (Collection)SecurityContextHolder.getContext().
                getAuthentication().getAuthorities();
        for(GrantedAuthority authority: authorities){
            if(authority.getAuthority().equals("ROLE_ADMIN") || authority.getAuthority().equals("ROLE_MODERATOR")){
              return true;
            }
        }
        return false;
    }

    @PostMapping(value = "passenger/add")
    public String getAddPassengerPage(@ModelAttribute("trainTicket") GlobalTrainsTicketDto globalTrainsTicketDto,
                                      Model model){
        if(checkRole()){
            return "redirect:/railroad/user";
        }
        model.addAttribute("passenger", new PassengerDto());
        model.addAttribute("trainTicket", globalTrainsTicketDto);
        return "addPassengerPage";
    }

    @ModelAttribute
    public GlobalTrainsTicketDto createTrainTicketsDto(){
        return new GlobalTrainsTicketDto();
    }

    @PostMapping(value = "passenger/add/result")
    public String getAddPassengerPageResult(@Valid @ModelAttribute("passenger") PassengerDto passengerDto,
                                            BindingResult bindingResult,
                                            @ModelAttribute("trainTicket") GlobalTrainsTicketDto globalTrainsTicketDto,
                                            Model model) throws RailroadDaoException {
        if(bindingResult.hasErrors()){
            return "addPassengerPage";
        }
        int error = businessService.isPassengerAlreadyBoughtTicket(globalTrainsTicketDto, passengerDto);
        if(error !=0){
            switch (error){
                case 1:
                    model.addAttribute("alreadyBought", true);
                    model.addAttribute("trainError", globalTrainsTicketDto.getToTrain().getFirstTrain().getNumber());
                    break;
                case 2:
                    model.addAttribute("alreadyBought", true);
                    model.addAttribute("trainError", globalTrainsTicketDto.getToTrain().getSecondTrain().getNumber());
                    break;
                case 3:
                    model.addAttribute("alreadyBought", true);
                    model.addAttribute("trainError", globalTrainsTicketDto.getReturnTrain().getFirstTrain().getNumber());
                    break;
                case 4:
                    model.addAttribute("alreadyBought", true);
                    model.addAttribute("trainError", globalTrainsTicketDto.getReturnTrain().getSecondTrain().getNumber());
                    break;
            }
            return "addPassengerPage";
        }

        return "redirect:/railroad/ticket/buy";
    }



    @PostMapping(value = "passenger/train/all")
    public @ResponseBody List<PassengerDto> getTrainPassengers(@RequestParam String trainNumber, @RequestParam Date date) throws RailroadDaoException {
        return businessService.getTrainPassengers(new Integer(trainNumber), date);
    }
}
