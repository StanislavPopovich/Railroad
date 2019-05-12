package com.railroad.controller;

import com.railroad.dto.station.StationDto;
import com.railroad.dto.way.WayDto;
import com.railroad.service.api.BusinessService;
import com.railroad.service.api.StationService;
import com.railroad.service.impl.TableService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        model.addAttribute("way", new WayDto());
        return "addStationPage";
    }

    @PostMapping(value = "/station/add")
    public String resultAddStationAndWay(@Valid @ModelAttribute("way") WayDto way, BindingResult bindingResult,
                                         Model model){
        if(bindingResult.hasErrors()){
            return "addStationPage";
        }
        if(stationService.isAlreadyExist(way.getFirstStation())){
            model.addAttribute("exist", true);
            return "addStationPage";
        }
        if(!stationService.isAlreadyExist(way.getSecondStation())){
            model.addAttribute("incorrectSelect", true);
            return "addStationPage";
        }
        businessService.saveStationAndWay(way);
        tableService.updateStations();
        return "redirect:/railroad/train/add";
    }

    @PostMapping(value = "/dest-station")
    public @ResponseBody
    List<String> getStationsWithoutStartStation(@RequestParam String departStation) {
        return stationService.getAllStationsNameWithoutDepartStation(departStation);
    }

    @GetMapping(value = "/stations")
    public @ResponseBody
    List<String> getStations() {
        return stationService.getAllStationsName();
    }
}
