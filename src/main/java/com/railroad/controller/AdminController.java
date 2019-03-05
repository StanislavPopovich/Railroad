package com.railroad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/railroad/admin")
public class AdminController {

    @GetMapping(value = "")
    public String adminPage(){
        return "admin_page";
    }
}
