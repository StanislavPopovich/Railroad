package com.railroad.service.impl;

import com.railroad.dao.api.*;
import com.railroad.dto.*;
import com.railroad.mapper.PassengerEntityDtoMapper;
import com.railroad.mapper.TrainEntityDtoMapper;
import com.railroad.model.PassengerEntity;
import com.railroad.model.ScheduleEntity;
import com.railroad.model.TicketEntity;
import com.railroad.model.TrainEntity;
import com.railroad.service.api.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


    @Autowired
    private TrainEntityDtoMapper trainEntityDtoMapper;

    @Autowired
    private TicketGenericDao ticketDao;

    @Autowired
    private TicketGenericDao ticketGenericDao;
    @Autowired
    private PassengerGenericDao passengerGenericDao;

    @Autowired
    private PassengerEntityDtoMapper passengerEntityDtoMapper;

    @Autowired
    private TrainGenericDao trainGenericDao;

    @Autowired
    private ScheduleGenericDao scheduleGenericDao;


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


    // Надо переписать
    @Transactional
    @Override
    public void saveTicketAndPassenger(PassengerDto passengerDto) {
        PassengerEntity passengerEntity = passengerEntityDtoMapper.passengerDtoToEntity(passengerDto);
        passengerGenericDao.save(passengerEntity);
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setPassengerEntity(passengerGenericDao.
                findPassengerByLastnameAndName(passengerDto.getLastName(), passengerDto.getName()));
        TrainEntity trainEntity = trainGenericDao.findTrainByNumber(new Integer(passengerDto.getTrainNumber()));
        ticketEntity.setTrainEntity(trainEntity);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date date = format.parse(passengerDto.getDepartDate());
            ticketEntity.setStartData(scheduleGenericDao.getScheduleByTrainAndDepartDate(trainEntity, date));
            ticketDao.save(ticketEntity);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    @Override
    public List<String> getAllNamesStations() {
        return stationService.getAllStationsName();
    }

    @Override
    public List<TrainDto> getAllTrains() {
        return trainService.getAll();
    }

    @Transactional
    @Override
    public List<TrainDto> getDirectTrains(String startStation, String destStation, Date date) {
        List<ScheduleEntity> scheduleEntities = scheduleService.getScheduleByStationName(startStation, date);
        List<TrainDto> trains = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        for(ScheduleEntity scheduleEntity: scheduleEntities){
            TrainDto trainDto = trainEntityDtoMapper.trainEntityToTrainDto(scheduleEntity.getTrainEntity());
            trainDto.setDepartDate(format.format(scheduleEntity.getDepartDate()));
            if(trainDto.getStations().contains(startStation) && trainDto.getStations().contains(destStation)){
                if(trainDto.getStations().indexOf(startStation) < trainDto.getStations().indexOf(destStation)){
                    Long count = ticketDao.getCountTicketsByTrain(scheduleEntity.getTrainEntity(), date);
                    trainDto.setSeats(trainDto.getSeats() - count.intValue());
                    trains.add(trainDto);
                }
            }
        }
        return trains;
    }

    @Transactional
    @Override
    public List<String> getRolesNames() {
        List<String> roles = new ArrayList<>();
        for(RoleDto roleDto: roleService.getAll()){
            roles.add(roleDto.getName());
        }
        return roles;
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



    @Override
    public UserDto getUserByUserName(String userName) {
        return userService.findByUsername(userName);
    }

    @Transactional
    @Override
    public void updateUser(UserDto userDto) {
        userService.update(userDto);
    }

    @Transactional
    @Override
    public void deleteUser(String userName) {
        userService.delete(userName);
    }


}
