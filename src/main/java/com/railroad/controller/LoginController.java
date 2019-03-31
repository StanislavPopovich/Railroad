package com.railroad.controller;
import com.railroad.dto.UserDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.SecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = {"/","/railroad"})
public class LoginController extends BaseController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BusinessService businessService;

    /**
     * Returns index page
     *
     * @return index page
     */

    @GetMapping
    public ModelAndView showIndexPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stations", businessService.getAllNamesStations());
        modelAndView.addObject("startStation", new String());
        modelAndView.addObject("endStation", new String());
        modelAndView.addObject("date", new Date());
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @PostMapping(value = "/dest-station")
    public @ResponseBody String ajax(@RequestParam String start) {
        return getStation(start, businessService.getAllNamesStations());
    }


    @GetMapping(value = "/login")
    public ModelAndView showLoginPage(){
        logger.info("Trying to log in.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new UserDto());
        modelAndView.setViewName("login_page");
        return modelAndView;
    }

    @GetMapping(value = "login/result")
    public String resultLogin() {
        return "user_page";
    }


    @GetMapping(value = "/registration")
    public String showRegistrationPage(Model model){
        logger.info("Trying to registration");
        model.addAttribute("userForm", new UserDto());
        return "registration_page";
    }

    @PostMapping(value = "/registration")
    public String resultRegistration(@Valid @ModelAttribute("userForm") UserDto userDto, BindingResult bindingResult,
                               Model model) {
        if(bindingResult.hasErrors()){
            return "registration_page";
        }
        if(securityService.isAlreadyExist(userDto.getUserName())){
            model.addAttribute("exist", true);
            return "registration_page";
        }
        securityService.registration(userDto);
        return "user_page";
    }
}
