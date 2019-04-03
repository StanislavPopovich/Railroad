package com.railroad.controller;
import com.railroad.dto.UserDto;
import com.railroad.service.api.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/railroad")
public class UserController {

    @Autowired
    private BusinessService businessService;

   /* @GetMapping(value = "/user")
    public ModelAndView getUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", businessService.getAllUsers());
        modelAndView.setViewName("userPage");
        return modelAndView;
    }*/
    @GetMapping(value = "/user")
    public String getUserPage() {
        return "userPage";
    }

    @GetMapping(value = "/user/admin")
    public @ResponseBody
    List<UserDto> getStartContentUserPage() {
        return businessService.getAllUsers();
    }

    @GetMapping(value = "/user/edit-user/{userName}")
    public ModelAndView editUserPage(@PathVariable("userName") String userName){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", businessService.getRolesNames());
        modelAndView.addObject("userDto", businessService.getUserByUserName(userName));
        modelAndView.setViewName("userEditPage");
        return modelAndView;
    }

    @PostMapping(value = "/user/edit-user/result")
    public String resultEditUser(@ModelAttribute("userDto") UserDto userDto){
        businessService.updateUser(userDto);
        return "redirect:/railroad/user";
    }

    @GetMapping(value = "/user/delete-user/{userName}")
    public String resultDeleteUser(@PathVariable("userName") String userName){
        businessService.deleteUser(userName);
        return "redirect:/railroad/user";
    }
}
