package com.railroad.controller;

import com.railroad.dto.way.WayDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.StationService;
import com.railroad.service.impl.TableService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/railroad")
public class StationController {
    private static final Logger logger = Logger.getLogger(StationController.class);

    @Autowired
    private StationService stationService;

    @Autowired
    private BusinessService businessService;
    @Autowired
    private TableService tableService;

    @GetMapping(value = "/station/add")
    public String getAddStationPage(Model model){
        model.addAttribute("stations", stationService.getAllStationsName());
        model.addAttribute("way", new WayDto());
        return "addStationPage";
    }

    @PostMapping(value = "/station/add")
    public String resultAddStationAndWay(@ModelAttribute("way") WayDto way){
        businessService.saveStationAndWay(way);
        tableService.updateStations();
        return "redirect:/railroad/train/add";
    }

    @PostMapping(value = "/dest-station")
    public @ResponseBody
    List<String> getStationsWithoutStartStation(@RequestParam String departStation) {
        return stationService.getAllStationsNameWithoutDepartStation(departStation);
    }
}
