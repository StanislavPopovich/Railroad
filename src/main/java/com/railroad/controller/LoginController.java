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


@Controller
@RequestMapping(value = {"/","/railroad"})
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BusinessService businessService;

    /**
     * The method returns the index page that allows you to search for trains from
     * the starting station to the destination station on a specific date
     *
     * @return model - list if stations names, strings for names of stations;
     * view - index pagejsp
     */

    @GetMapping
    public ModelAndView getIndexPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stations", businessService.getAllNamesStations());
        modelAndView.addObject("startStation", new String());
        modelAndView.addObject("endStation", new String());
        modelAndView.addObject("date", new Date());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * The method returns the page with login form
     * @return model - userDto;
     * view - loginPage.jsp
     */
    @GetMapping(value = "/login")
    public ModelAndView getLoginPage(){
        logger.info("Trying to log in.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new UserDto());
        modelAndView.setViewName("loginPage");
        return modelAndView;
    }

    /**
     * Method redirects to user page
     * @return url to user page
     */
    @GetMapping(value = "/login/result")
    public String loginResult() {
        return "redirect:/railroad/user";
    }

    /**
     * The method returns the page with registration form
     * @param model
     * @return model - userDto;
     * view - registrationPage.jsp
     */
    @GetMapping(value = "/registration")
    public String getRegistrationPage(Model model){
        logger.info("Trying to registration");
        model.addAttribute("userForm", new UserDto());
        return "registrationPage";
    }

    /**
     * The method returns a page for an authorized user
     * @param userDto
     * @param bindingResult
     * @param model
     * @return userPage.jsp
     */
    @PostMapping(value = "/registration")
    public String resultRegistration(@Valid @ModelAttribute("userForm") UserDto userDto, BindingResult bindingResult,
                               Model model) {
        if(bindingResult.hasErrors()){
            return "registrationPage";
        }
        if(securityService.isAlreadyExist(userDto.getUserName())){
            model.addAttribute("exist", true);
            return "registrationPage";
        }
        securityService.registration(userDto);
        return "userPage";
    }
}
