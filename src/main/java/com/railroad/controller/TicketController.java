package com.railroad.controller;
import com.railroad.dto.TicketDto;
import com.railroad.service.api.BusinessService;
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
public class TicketController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private TicketService ticketService;



    @PostMapping(value = "ticket/buy")
    public String buyTicket(@ModelAttribute("ticket")TicketDto ticketDto){
        businessService.saveTicket(ticketDto);
        return "redirect:/railroad/user";
    }

    @PostMapping(value = "ticket/info")
    public String ticketMoreInfoPage(@ModelAttribute("ticket")TicketDto ticketDto, Model model){
        model.addAttribute("ticket", ticketDto);
        return "ticketInfo";
    }

    @PostMapping(value = "ticket/delete")
    public String deleteTicket(@ModelAttribute("ticket")TicketDto ticketDto){
        ticketService.removeTicketByNumber(ticketDto.getNumber());
        return "redirect:/railroad/user";
    }

    @GetMapping(value = "ticket/all")
    public String ticketsPage(){
        return "allTickets";
    }

    @GetMapping(value = "tickets")
    public @ResponseBody
    List<List<TicketDto>> getStartContentUserPage() {
        return businessService.getAllTickets();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
