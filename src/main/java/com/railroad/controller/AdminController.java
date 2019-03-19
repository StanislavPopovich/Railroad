package com.railroad.controller;

import com.railroad.dto.RoleDto;
import com.railroad.dto.UserDto;
import com.railroad.service.api.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/railroad/admin")
public class AdminController {
    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "")
    public String showAdminPage(){
        return "admin_page";
    }

    @GetMapping(value = "/all_users")
    public String showAllUsers(Model model){
        model.addAttribute("users", businessService.getAllUsers());
        return "users_page";
    }

    @GetMapping(value = "/all_users/edit/{userName}")
    public String editUserPage(@PathVariable("userName") String userName, Model model){
        List<String> roles = new ArrayList<>();
        for(RoleDto roleDto: businessService.getAllRoles()){
            roles.add(roleDto.getName());
        }
        model.addAttribute("roles", roles);
        model.addAttribute("userDto", businessService.getUserByUserName(userName));
        return "user_edit_page";
    }

    @PostMapping(value = "/all_users/edit/result")
    public String editUserPage(@ModelAttribute("userDto") UserDto userDto){
        businessService.updateUser(userDto);
        return "redirect:/railroad/admin/all_users";
    }
}
