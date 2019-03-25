package com.railroad.controller;

import com.railroad.dto.StationDto;
import com.railroad.service.api.BusinessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping(value = {"/railroad/moderator", "/railroad/admin"})
public class StationController extends BaseController {
    private static final Logger logger = Logger.getLogger(StationController.class);

    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "/all-stations")
    public String showAllStations(Model model){
        model.addAttribute("stations", businessService.getAllNamesStations());
        return "stations_page";
    }

    @GetMapping(value = "/add-station")
    public String showAddStationPage(Model model){
        model.addAttribute("station", new StationDto());
        return "add_station";
    }

    @PostMapping(value = "/add-station")
    public String showAddStationPage(@ModelAttribute("station") StationDto stationDto){
        businessService.saveStation(stationDto);
        return getUrl() + "all-stations";
    }
}
