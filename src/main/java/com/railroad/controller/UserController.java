package com.railroad.controller;
import com.railroad.dto.TicketDto;
import com.railroad.dto.TrainDto;
import com.railroad.dto.UserDto;
import com.railroad.model.TicketEntity;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.RoleService;
import com.railroad.service.api.TrainService;
import com.railroad.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/railroad")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "/user")
    public String getUserPage(Model model) {
        model.addAttribute("ticket", new TicketDto());
        return "userPage";
    }

    @GetMapping(value = "/user/user")
    public @ResponseBody
    List<TicketDto> getStartContentUserPage() {
        return businessService.getActualTickets();
    }

    @GetMapping(value = "/user/admin")
    public @ResponseBody
    List<UserDto> getStartContentAdminPage() {
        return userService.getAll();
    }

    @GetMapping(value = "/user/moderator")
    public @ResponseBody
    List<TrainDto> getStartContentModeratorPage() {
        return trainService.getAll();
    }

    @GetMapping(value = "/user/edit-user/{userName}")
    public ModelAndView editUserPage(@PathVariable("userName") String userName){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", roleService.getRolesNames());
        modelAndView.addObject("userDto", userService.findByUsername(userName));
        modelAndView.setViewName("userEditPage");
        return modelAndView;
    }

    @PostMapping(value = "/user/edit-user/result")
    public String resultEditUser(@ModelAttribute("userDto") UserDto userDto){
        userService.update(userDto);
        return "redirect:/railroad/user";
    }

    @GetMapping(value = "/user/delete-user/{userName}")
    public String resultDeleteUser(@PathVariable("userName") String userName){
       userService.delete(userName);
        return "redirect:/railroad/user";
    }
}
