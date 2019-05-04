package com.railroad.controller;
import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.train.TrainTicketDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.TicketService;
import com.railroad.service.impl.BuyTicketService;
import org.apache.log4j.Logger;
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
@SessionAttributes({"trainForm","passenger"})
public class TicketController {

    private static final Logger logger = Logger.getLogger(TicketController.class);

    @Autowired
    private BusinessService businessService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private BuyTicketService buyTicketService;

    @GetMapping(value = "ticket/buy")
    public String getSuccessBuyTicketPage(@ModelAttribute("trainForm") TrainTicketDto trainTicketDto,
                                          @ModelAttribute("passenger") PassengerDto passengerDto,
                                          Model model){
        model.addAttribute("ticket", buyTicketService.saveTicket(trainTicketDto, passengerDto));
        return "successBuyTicketPage";
    }

    @GetMapping(value = "ticket/buy/success")
    public String getUserPage(){
        return "redirect:/railroad/user";
    }

    @PostMapping(value = "ticket/info")
    public String getTicketInfoPage(@ModelAttribute("ticket") TicketDto ticketDto, Model model){
        model.addAttribute("ticket", ticketDto);
        return "ticketInfoPage";
    }

    @PostMapping(value = "ticket/delete")
    public String deleteTicket(@ModelAttribute("ticket")TicketDto ticketDto){
        ticketService.removeTicketByNumber(ticketDto.getNumber());
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
