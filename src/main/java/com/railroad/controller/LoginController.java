package com.railroad.controller;
import com.railroad.model.User;
import com.railroad.service.SecurityService;
import com.railroad.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
    @RequestMapping(value = "")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(){
        logger.info("Trying to log in.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("login_page");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        logger.info("Trying to registration");
        model.addAttribute("userForm", new User());
        return "registration_page";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm) {
        userService.save(userForm);
        securityService.autoLogin(userForm.getUserName(), userForm.getConfirmPassword());
        return getRolePage();
    }

    @RequestMapping(value = "/login/result", method = RequestMethod.GET)
    public String registration() {
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
