package com.railroad.controller;
import com.railroad.dto.TicketDto;
import com.railroad.service.api.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/railroad/user")
public class TicketController {

    @Autowired
    private BusinessService businessService;



    @PostMapping(value = "ticket/buy")
    public String buyTicket(@ModelAttribute("ticket")TicketDto ticketDto){
        businessService.saveTicket(ticketDto);
        return "redirect:/railroad/user";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
