package com.railroad.controller;

import com.railroad.dto.StationDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.StationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/railroad")
public class StationController {
    private static final Logger logger = Logger.getLogger(StationController.class);

    @Autowired
    private StationService stationService;

    @GetMapping(value = "/user/add-station")
    public String getAddStationPage(Model model){
        model.addAttribute("station", new StationDto());
        return "addStation";
    }

    @PostMapping(value = "/user/add-station")
    public String resultAddStation(@ModelAttribute("station") StationDto stationDto){
        stationService.save(stationDto);
        return "redirect:/railroad/user";
    }

    @PostMapping(value = "/dest-station")
    public @ResponseBody
    List<String> getStationsWithoutStartStation(@RequestParam String start) {
        return getStations(start, stationService.getAllStationsName());
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
