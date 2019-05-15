package com.railroad.controller;
import com.railroad.dto.service.SearchServiceDto;
import com.railroad.dto.ticket.GlobalTrainsTicketDto;
import com.railroad.dto.user.UserDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.service.api.SecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping(value = {"/","/railroad"})
@SessionAttributes({"trainTicket", "searchData"})
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private SecurityService securityService;

    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("searchData", new SearchServiceDto());
        model.addAttribute("currentDate", new Date());
        return "index";
    }

    @PostMapping(value = "/trains")
    public String getTargetTrainsPage(@ModelAttribute("searchData") SearchServiceDto searchServiceDto, Model model){
        model.addAttribute("searchData", searchServiceDto);
        model.addAttribute("trainTicket", new GlobalTrainsTicketDto());
        model.addAttribute("page", "start");
        return "targetTrainsPage";
    }

    @ModelAttribute
    public GlobalTrainsTicketDto createTrainTicketsDto(){
        return new GlobalTrainsTicketDto();
    }

    @PostMapping(value = "trains/return")
    public String getTargetReturnTrainsPage(@ModelAttribute("searchData") SearchServiceDto searchServiceDto,
                                            @ModelAttribute("trainTicket") GlobalTrainsTicketDto train,
                                            Model model){
        model.addAttribute("searchData", searchServiceDto);
        model.addAttribute("trainTicket", train);
        model.addAttribute("page", "return");
        return "targetTrainsPage";
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model){
        logger.info("Trying to log in.");
        model.addAttribute("user", new UserDto());
        return "loginPage";
    }

    @GetMapping(value = "/login/result")
    public String loginResult() {
        return "redirect:/railroad/user";
    }


    @GetMapping(value = "/login/error")
    public String loginError(@ModelAttribute("user") UserDto userDto, Model model) {
        model.addAttribute("notExist", true);
        return "loginPage";
    }

    @GetMapping(value = "/registration")
    public String getRegistrationPage(Model model){
        model.addAttribute("userForm", new UserDto());
        return "registrationPage";
    }

    @PostMapping(value = "/registration")
    public String resultRegistration(@Valid @ModelAttribute("userForm") UserDto userDto, BindingResult bindingResult,
                               Model model) throws RailroadDaoException {
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
