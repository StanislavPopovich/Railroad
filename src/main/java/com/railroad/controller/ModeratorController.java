package com.railroad.controller;

import com.railroad.dto.StationDto;
import com.railroad.service.api.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/railroad/moderator")
public class ModeratorController {

    @Autowired
    private StationService stationService;


    @GetMapping(value = "")
    public String showModeratorPage(){
        return "moderator_page";
    }

    @GetMapping(value = "/all_stations")
    public String showAllStations(Model model){
        model.addAttribute("stations", stationService.getAll());
        return "station_page";
    }


    @GetMapping(value = "/add_station")
    public String showAddStationPage(Model model){
        model.addAttribute("station", new StationDto());
        return "add_station";
    }

    @PostMapping(value = "/add_station")
    public String showAddStationPage(@ModelAttribute("station") StationDto stationDto){
        stationService.save(stationDto);
        return "redirect:/railroad/moderator/all_stations";
    }
}
