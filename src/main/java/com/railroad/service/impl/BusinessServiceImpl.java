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

    @Autowired
    private ScheduleService scheduleService;


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
    public void saveSchedule(ScheduleDto scheduleDto) {
        scheduleService.save(scheduleDto);
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

    @Override
    public List<TrainDto> getDirectTrains(String startStation, String destStation) {
        return trainService.getDirectTrains(startStation, destStation);
    }

    @Transactional
    @Override
    public void getTransferTrains(String startStation, String destStation) {
        trainService.getTrainsWithOneTransfer(startStation, destStation);
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
    public List<String> getAllRoutes(String startStation, String endStation) {
        List<String> targetRoutes = new ArrayList<>();
        List<String> routes = wayService.getAllRoutes(startStation, endStation);
        for(String str: routes){
            StringBuilder route = new StringBuilder();
            String[] stationsId = str.split(",");
            for(int i = 0; i < stationsId.length; i++){
                if(i == stationsId.length - 1){
                    route.append(stationService.getStationById(new Long(stationsId[i])).getName());
                }else{
                    route.append(stationService.getStationById(new Long(stationsId[i])).getName() + "=>");
                }
            }
            targetRoutes.add(route.toString());
        }
        return targetRoutes;
    }

    public List<StationDto> getAllStations(){
        return stationService.getAll();
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

    @Override
    public List<ScheduleDto> getSchedulesByTrainNumber(Integer trainNumber) {
        return scheduleService.getScheduleByTrainNumber(trainNumber);
    }

    @Override
    public List<ScheduleDto> getSchedulesByStationName(String stationName) {
        return scheduleService.getScheduleByStationName(stationName);
    }

}
