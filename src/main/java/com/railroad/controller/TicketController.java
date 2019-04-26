package com.railroad.controller;
import com.railroad.dto.PassengerDto;
import com.railroad.dto.TicketDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.TicketService;
import com.railroad.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/railroad")
public class TicketController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping(value = "ticket/buy")
    public String getSuccessBuyTicketPage(@ModelAttribute("ticket")TicketDto ticketDto, Model model){
        businessService.saveTicket(ticketDto);
        model.addAttribute("ticket", ticketDto);
        emailService.sendMail(ticketDto);
        return "successBuyTicketPage";
    }

    @GetMapping(value = "ticket/buy/success")
    public String getUserPage(){
        return "redirect:/railroad/user";
    }

    @PostMapping(value = "ticket/info")
    public String getTicketInfoPage(@ModelAttribute("ticket") TicketDto ticketDto, Model model){
        System.out.println();
        System.out.println(ticketDto);
        model.addAttribute("ticket", ticketDto);
        return "ticketInfoPage";
    }

    @PostMapping(value = "ticket/delete")
    public String deleteTicket(@ModelAttribute("ticket")TicketDto ticketDto){
        ticketService.removeTicketByNumber(ticketDto.getNumber());
        //send email
        return "redirect:/railroad/user";
    }

    @GetMapping(value = "ticket/all-not-actual")
    public @ResponseBody List<TicketDto> getNotActualTickets(){
        return businessService.getNotActualTickets();
    }

    @GetMapping(value = "ticket/all")
    public String getTicketsPage(){
        return "ticketsPage";
    }

    @PostMapping(value = "passenger/tickets")
    public String getPassengerTickets(@ModelAttribute("passenger") PassengerDto passengerDto, Model model){
        model.addAttribute("passengerForm", new PassengerDto());
        model.addAttribute("passenger", passengerDto);
        return "passengerTicketsPage";
    }

    @PostMapping(value = "/passenger/tickets/actual")
    public @ResponseBody List<TicketDto> getPassengerActualTickets(@RequestBody PassengerDto passengerDto){
       return businessService.getPassengerActualTickets(passengerDto);
    }

    @PostMapping(value = "passenger/tickets/not-actual")
    public @ResponseBody List<TicketDto> getPassengerNotActualTickets(@RequestBody PassengerDto passengerDto){
        return businessService.getPassengerNotActualTickets(passengerDto);
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
