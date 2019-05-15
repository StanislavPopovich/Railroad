package com.railroad.controller;
import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.ticket.GlobalTrainsTicketDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.BuyTicketService;
import com.railroad.service.api.TicketService;
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
@SessionAttributes({"trainTicket","passenger"})
public class TicketController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private BuyTicketService buyTicketService;

    @GetMapping(value = "ticket/buy")
    public String getSuccessBuyTicketPage(@ModelAttribute("trainTicket") GlobalTrainsTicketDto globalTrainsTicketDto,
                                          @ModelAttribute("passenger") PassengerDto passengerDto,
                                          Model model) throws RailroadDaoException {
        model.addAttribute("trainTicket", globalTrainsTicketDto);
        model.addAttribute("passenger", passengerDto);
        buyTicketService.saveTicket(globalTrainsTicketDto, passengerDto);
        return "successBuyTicketPage";
    }

    @PostMapping(value = "ticket/info")
    public String getTicketInfoPage(@ModelAttribute("ticket") TicketDto ticketDto, Model model){
        model.addAttribute("ticket", ticketDto);
        return "ticketInfoPage";
    }

    @PostMapping(value = "ticket/delete")
    public String deleteTicket(@ModelAttribute("ticket")TicketDto ticketDto) throws RailroadDaoException {
        ticketService.removeTicketByNumber(ticketDto.getNumber());
        return "redirect:/railroad/user";
    }

    @PostMapping(value = "passenger/tickets")
    public String getPassengerTickets(@ModelAttribute("currentPassenger") PassengerDto passengerDto, Model model){
        model.addAttribute("passengerForm", new PassengerDto());
        model.addAttribute("currentPassenger", passengerDto);
        return "passengerTicketsPage";
    }

    @PostMapping(value = "/passenger/tickets/actual")
    public @ResponseBody List<TicketDto> getPassengerActualTickets(@RequestBody PassengerDto passengerDto) throws RailroadDaoException {
       return businessService.getPassengerActualTickets(passengerDto);
    }

    @PostMapping(value = "passenger/tickets/not-actual")
    public @ResponseBody List<TicketDto> getPassengerNotActualTickets(@RequestBody PassengerDto passengerDto) throws RailroadDaoException {
        return businessService.getPassengerNotActualTickets(passengerDto);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
