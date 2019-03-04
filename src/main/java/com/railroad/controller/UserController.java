package com.railroad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/railroad/user")
public class UserController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String registration(){
        return "user_page";
    }
}
