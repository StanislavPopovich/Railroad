package com.railroad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/railroad/user")
public class UserController {

    @GetMapping(value = "")
    public String showUserPage(){
        return "user_page";
    }

    @GetMapping(value = "/users")
    public String showAllUsersPage(Model model){
        return null;
    }

    @GetMapping(value = "buy-ticket/{train_number}")
    public ModelAndView buyTicket(@PathVariable("train_number") String train_number){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("buy_ticket_page");
        return modelAndView;
    }
}
