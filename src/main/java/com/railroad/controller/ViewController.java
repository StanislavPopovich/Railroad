package com.railroad.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value =  "/railroad")
public class ViewController {

    @GetMapping(value = "accessDenied")
    public String getAccessDeniedPage(){
        return "accessDeniedPage";
    }
}
