package com.railroad.service.impl;

import com.railroad.dto.*;
import com.railroad.service.api.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    private static final Logger logger = Logger.getLogger(BusinessServiceImpl.class);

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private WayService wayService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @Transactional
    @Override
    public void saveTrain(TrainDto trainDto) {
        trainService.save(trainDto);
    }

    @Transactional
    @Override
    public void saveStation(StationDto stationDto) {
        stationService.save(stationDto);
    }

    @Transactional
    @Override
    public void saveWay(WayDto wayDto) {
        wayService.save(wayDto);
    }

    @Transactional
    @Override
    public void saveUser(UserDto userDto) {
        userService.save(userDto);
    }

    @Transactional
    @Override
    public List<String> getAllNamesStations() {
        return stationService.getAllStationsName();
    }

    @Transactional
    @Override
    public List<TrainDto> getAllTrains() {
        return trainService.getAll();
    }

    @Transactional
    @Override
    public List<RoleDto> getAllRoles() {
        return roleService.getAll();
    }

    @Transactional
    @Override
    public List<WayDto> getAllWay() {
        return wayService.getAll();
    }

    @Transactional
    @Override
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @Transactional
    @Override
    public List<List<String>> getAllRoutes(String startStation, String endStation) {
        List<List<String>> targetRoutes = new ArrayList<>();
        List<String> routes = wayService.getAllRoutes(startStation, endStation);
        for(String str: routes){
            List<String> route = new ArrayList<>();
            String[] stationsId = str.split(",");
            for(String stationId: stationsId){
                route.add(stationService.getStationById(new Long(stationId)).getName());
            }
            targetRoutes.add(route);
        }
        return targetRoutes;
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        return userService.findByUsername(userName);
    }

    @Transactional
    @Override
    public void updateUser(UserDto userDto) {
        userService.update(userDto);
    }
}
