package com.railroad.controller;

import com.railroad.dto.WayDto;
import com.railroad.service.api.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/railroad/moderator", "/railroad/admin"})
public class WayController extends BaseController {
    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "/all-ways")
    public String showAllWays(Model model){
        model.addAttribute("ways", businessService.getAllWay());
        return "ways_page";
    }

    @GetMapping(value = "/add-way")
    public String showAddWayPage(Model model){
        model.addAttribute("way", new WayDto());
        model.addAttribute("stations", businessService.getAllNamesStations());
        return "add_way";
    }

    @PostMapping(value = "/add-way")
    public String showAddWayPage(@ModelAttribute("way") WayDto wayDto){
        businessService.saveWay(wayDto);
        return getUrl()+"all-ways";
    }
}
