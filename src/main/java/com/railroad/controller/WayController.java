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
@RequestMapping(value = "/railroad")
public class WayController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private WayService wayService;

    @Autowired
    private StationService stationService;

    @GetMapping(value = "/way/all")
    public String showAllWays(Model model){
        model.addAttribute("ways", wayService.getAllWayDtos());
        return "waysPage";
    }

    @GetMapping(value = "/way/add")
    public String addWayPage(Model model){
        model.addAttribute("departStation","");
        model.addAttribute("arrivalStation","");
        model.addAttribute("distanceForm","");
        model.addAttribute("wayForm", new WayDto());
        model.addAttribute("stations", stationService.getAllStationsName());
        return "addWay";
    }

    @PostMapping(value = "/way/add")
    public String addWayPage(@ModelAttribute("way") WayDto wayDto){
        wayService.save(wayDto);
        return "redirect:/railroad/train/add";
    }
}
