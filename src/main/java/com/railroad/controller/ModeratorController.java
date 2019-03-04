package com.railroad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/railroad/moderator")
public class ModeratorController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String registration(){
        return "moderator_page";
    }
}
