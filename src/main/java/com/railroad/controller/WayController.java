package com.railroad.controller;

import com.railroad.dto.WayDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.StationService;
import com.railroad.service.api.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/railroad/user")
public class WayController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private WayService wayService;

    @Autowired
    private StationService stationService;

    @GetMapping(value = "/all-ways")
    public String showAllWays(Model model){
        model.addAttribute("ways", wayService.getAllWayDtos());
        return "waysPage";
    }

    @GetMapping(value = "/add-way")
    public String showAddWayPage(Model model){
        model.addAttribute("way", new WayDto());
        model.addAttribute("stations", stationService.getAllStationsName());
        return "addWay";
    }

    @PostMapping(value = "/add-way")
    public String showAddWayPage(@ModelAttribute("way") WayDto wayDto){
        wayService.save(wayDto);
        return "redirect:/railroad/user";
    }
}
