package com.railroad.controller;

import com.railroad.dto.RoleDto;
import com.railroad.dto.UserDto;
import com.railroad.service.api.BusinessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(value = "/railroad/admin")
public class AdminController extends BaseController {

    private static final Logger logger = Logger.getLogger(AdminController.class);
    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "")
    public String showAdminPage(){
        return "user_page";
    }

    @GetMapping(value = "/all-users")
    public String showAllUsers(Model model){
        model.addAttribute("users", businessService.getAllUsers());
        return "users_page";
    }

    @GetMapping(value = "/all-users/edit/{userName}")
    public String editUserPage(@PathVariable("userName") String userName, Model model){
        List<String> roles = new ArrayList<>();
        for(RoleDto roleDto: businessService.getAllRoles()){
            roles.add(roleDto.getName());
        }
        model.addAttribute("roles", roles);
        model.addAttribute("userDto", businessService.getUserByUserName(userName));
        return "user_edit_page";
    }

    @PostMapping(value = "/all-users/edit/result")
    public String editUserPage(@ModelAttribute("userDto") UserDto userDto){
        businessService.updateUser(userDto);
        return getUrl() + "all-users";
    }
}
