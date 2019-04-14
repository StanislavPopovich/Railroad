package com.railroad.controller;

import com.railroad.dto.StationDto;
import com.railroad.dto.WayDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.StationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/railroad")
public class StationController {
    private static final Logger logger = Logger.getLogger(StationController.class);

    @Autowired
    private StationService stationService;

    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "/station/add")
    public String addStationPage(Model model){
        model.addAttribute("stations", stationService.getAllStationsName());
        model.addAttribute("way", new WayDto());
        return "addStation";
    }

    @PostMapping(value = "/station/add")
    public String resultAddStation(@ModelAttribute("way") WayDto way){
        businessService.saveStationAndWay(way);
        return "redirect:/railroad/train/add";
    }

    @PostMapping(value = "/dest-station")
    public @ResponseBody
    List<String> getStationsWithoutStartStation(@RequestParam String departStation) {
        return getStations(departStation, stationService.getAllStationsName());
    }

    public List<String> getStations(String startStation, List<String> stations){
        List<String> list = new ArrayList<>();
        for(String station: stations){
            if(!station.equals(startStation)){
                list.add(station);
            }
        }
        return list;
    }
}
