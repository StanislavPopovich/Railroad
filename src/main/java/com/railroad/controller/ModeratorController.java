package com.railroad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/railroad/moderator")
public class ModeratorController {

    @GetMapping(value = "")
    public String showModeratorPage(){
        return "moderator_page";
    }
}
