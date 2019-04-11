package com.railroad.controller;
import com.railroad.dto.TrainDto;
import com.railroad.dto.UserDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.SecurityService;
import com.railroad.service.api.StationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    private StationService stationService;

    /**
     * The method returns the index page that allows you to search for trains from
     * the departing station to the arrival station on a specific date
     * @param modelMap
     * @return
     */
    @GetMapping
    public String getIndexPage(ModelMap modelMap){
        modelMap.addAttribute("stations", stationService.getAllStationsName());
        modelMap.addAttribute("startStation", "");
        modelMap.addAttribute("endStation", "");
        modelMap.addAttribute("date", new Date());
        modelMap.addAttribute("train", new TrainDto());
        return "index";
    }

    /**
     * The method returns the page with login form
     * @param model
     * @return login page
     */
    @GetMapping(value = "/login")
    public String getLoginPage(Model model){
        logger.info("Trying to log in.");
        model.addAttribute("user", new UserDto());
        return "loginPage";
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
     * @return registration page
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
