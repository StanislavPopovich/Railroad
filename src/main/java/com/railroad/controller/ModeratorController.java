package com.railroad.controller;

import com.railroad.service.api.BusinessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/railroad/moderator")
public class ModeratorController {

    private static final Logger logger = Logger.getLogger(ModeratorController.class);

    @Autowired
    private BusinessService businessService;


    @GetMapping(value = "")
    public String showModeratorPage(){
        return "user_page";
    }


}
