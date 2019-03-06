package com.railroad.controller;
import com.railroad.model.User;
import com.railroad.service.api.SecurityService;
import com.railroad.service.api.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;



@Controller
@RequestMapping(value = {"/","/railroad"})
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    /**
     * Returns index page
     *
     * @return index page
     */

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping(value = "/login")
    public ModelAndView login(){
        logger.info("Trying to log in.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("login_page");
        return modelAndView;
    }

    @GetMapping(value = "login/result")
    public String resultLogin() {
        return getRolePage();
    }


    @GetMapping(value = "/registration")
    public String registration(Model model){
        logger.info("Trying to registration");
        model.addAttribute("userForm", new User());
        return "registration_page";
    }

    @PostMapping(value = "/registration")
    public String registration(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                               Model model) {
        if(bindingResult.hasErrors()){
            return "registration_page";
        }
        if(userService.isAlreadyExist(userForm.getUserName())){
            model.addAttribute("exist", true);
            return "registration_page";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUserName(), userForm.getConfirmPassword());
        return getRolePage();
    }

    @SuppressWarnings("unchecked")
    private String getRolePage(){
        Collection<GrantedAuthority> authorities = (Collection)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for(GrantedAuthority authority: authorities){
            if(authority.getAuthority().equals("ROLE_ADMIN")){
                logger.info("Logged as Admin");
                return "redirect:/railroad/admin";
            }else if(authority.getAuthority().equals("ROLE_MODERATOR")){
                logger.info("Logged as Moderator");
                return "redirect:/railroad/moderator";
            }
        }
        logger.info("Logged as User");
        return "redirect:/railroad/user";
    }

}
