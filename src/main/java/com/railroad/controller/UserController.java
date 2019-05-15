package com.railroad.controller;
import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.role.RoleDto;
import com.railroad.dto.schedule.ScheduleInfoDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.user.UserDto;
import com.railroad.exceptions.RailroadDaoException;
import com.railroad.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/railroad")
public class UserController {

    @Autowired
    private UserService userService;

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

    @GetMapping(value = "/admin/users")
    public String getAdminPage() {
        return "usersPage";
    }

    @GetMapping(value = "/admin/get-users")
    public @ResponseBody
    List<UserDto> getStartContentAdminPage() throws RailroadDaoException {
        return userService.getAll();
    }

    @PostMapping(value = "/user/schedule/get-schedule-by-station-date")
    public @ResponseBody
    List<ScheduleInfoDto> getSchedulesBuStationAndDate(@RequestParam String station, @RequestParam Date date) throws RailroadDaoException {
        return scheduleService.getScheduleDtosByStationAndDepartDate(station, date);
    }

    @GetMapping(value = "/admin/get-all-roles")
    public @ResponseBody List<RoleDto> getAllRoles() throws RailroadDaoException {
        return roleService.getAllRolesDto();
    }

    @PostMapping(value = "/admin/update")
    public void editUserResult(@RequestParam String userName, String role) throws RailroadDaoException {
        userService.update(userName, role);
    }

    @PostMapping(value = "/admin/user-delete")
    public void deleteUserResult(@RequestParam String userName) throws RailroadDaoException {
        userService.delete(userName);
    }

    @GetMapping(value = "/user/get-actual-tickets")
    public @ResponseBody
    List<TicketDto> getStartContentUserPage() throws RailroadDaoException {
        return businessService.getActualTickets();
    }

    @GetMapping(value = "/user/get-not-actual-tickets")
    public @ResponseBody List<TicketDto> getNotActualTickets() throws RailroadDaoException {
        return businessService.getNotActualTickets();
    }

    @GetMapping(value = "user/schedule-view")
    public String getSchedulePageForUser(Model model){
        model.addAttribute("date", new Date());
        return "viewSchedulePage";
    }

    @GetMapping(value = "user/passenger/all")
    public String getUserPassengerPage(Model model){
        model.addAttribute("currentPassenger", new PassengerDto());
        return "userPassengersPage";
    }

    @GetMapping(value = "user/passenger/all-for-current-user")
    public @ResponseBody List<PassengerDto> getPassengersOfUser() throws RailroadDaoException {
        return businessService.getPassengersOfCurrentUser();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
