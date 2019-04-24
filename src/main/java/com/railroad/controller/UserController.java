package com.railroad.controller;
import com.railroad.dto.ScheduleDto;
import com.railroad.dto.TicketDto;
import com.railroad.dto.UserDto;
import com.railroad.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/railroad")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StationService stationService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private BusinessService businessService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = "/user")
    public String getUserPage(Model model) {
        model.addAttribute("ticket", new TicketDto());
        return "userPage";
    }

    @GetMapping(value = "/user/user")
    public @ResponseBody
    List<TicketDto> getStartContentUserPage() {
        return businessService.getActualTickets();
    }

    @GetMapping(value = "/user/admin")
    public @ResponseBody
    List<UserDto> getStartContentAdminPage() {
        return userService.getAll();
    }

    @GetMapping(value = "user/schedule")
    public String getSchedulePageForUser(Model model){
        model.addAttribute("stations", stationService.getAllStationsName());
        model.addAttribute("station", "");
        model.addAttribute("date", new Date());
        return "userSchedulePage";
    }

    @PostMapping(value = "/user/schedule/get-schedule-by-station-date")
    public @ResponseBody
    List<ScheduleDto> getSchedulesBuStationAndDate(@RequestParam String station, @RequestParam Date date) {
        return scheduleService.getScheduleDtosByStationNameAndDepartDate(station, date);
    }

    @GetMapping(value = "/user/edit-user/{userName}")
    public ModelAndView getEditUserPage(@PathVariable("userName") String userName){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", roleService.getRolesNames());
        modelAndView.addObject("userDto", userService.findByUsername(userName));
        modelAndView.setViewName("userEditPage");
        return modelAndView;
    }

    @PostMapping(value = "/user/edit-user/result")
    public String editUserResult(@ModelAttribute("userDto") UserDto userDto){
        userService.update(userDto);
        return "redirect:/railroad/user";
    }

    @GetMapping(value = "/user/delete-user/{userName}")
    public String deleteUserResult(@PathVariable("userName") String userName){
       userService.delete(userName);
        return "redirect:/railroad/user";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
