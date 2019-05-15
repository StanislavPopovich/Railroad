package com.railroad.controller;

import com.railroad.dto.way.WayDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.service.api.StationService;
import com.railroad.service.api.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/railroad")
public class WayController {

    @Autowired
    private WayService wayService;

    @Autowired
    private StationService stationService;

    @GetMapping(value = "/way/add")
    public String addWayPage(Model model){
        model.addAttribute("exist", false);
        model.addAttribute("incorrectSelect", false);
        model.addAttribute("wayForm", new WayDto());
        return "addWayPage";
    }

    @PostMapping(value = "/way/add")
    public String addWayPage(@Valid @ModelAttribute("wayForm") WayDto wayDto, BindingResult bindingResult,
                             Model model) throws RailroadDaoException {
        if(bindingResult.hasErrors()){
            return "addWayPage";
        }
        if(!stationService.isAlreadyExist(wayDto.getFirstStation()) ||
                !stationService.isAlreadyExist(wayDto.getSecondStation())){
            model.addAttribute("incorrectSelect", true);
            return "addWayPage";
        }
        if(wayService.isAlreadyExist(wayDto)){
            model.addAttribute("exist", true);
            return "addWayPage";
        }
        wayService.save(wayDto);
        return "redirect:/railroad/train/add";
    }
}
