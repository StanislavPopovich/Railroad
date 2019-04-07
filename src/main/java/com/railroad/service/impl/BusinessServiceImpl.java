package com.railroad.service.impl;

import com.railroad.dao.api.*;
import com.railroad.dto.*;
import com.railroad.mapper.PassengerEntityDtoMapper;
import com.railroad.mapper.TrainEntityDtoMapper;
import com.railroad.model.*;
import com.railroad.service.api.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

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
    private TicketService ticketService;

    @Autowired
    private PassengerGenericDao passengerGenericDao;

    @Autowired
    private PassengerEntityDtoMapper passengerEntityDtoMapper;


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
       /* TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setPassengerEntity(passengerGenericDao.
                findPassengerByLastnameAndName(passengerDto.getLastName(), passengerDto.getName()));
        TrainEntity trainEntity = trainGenericDao.findTrainByNumber(1);
        ticketEntity.setTrainEntity(trainEntity);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date date = format.parse(passengerDto.getDepartDate());
            ticketEntity.setStartData(scheduleGenericDao.findScheduleByTrainAndDepartDate(trainEntity, date));
            ticketDao.save(ticketEntity);
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/
    }

    @Transactional
    @Override
    public PassengerEntity savePas(PassengerDto passengerDto){
        PassengerEntity passengerEntity = passengerEntityDtoMapper.passengerDtoToEntity(passengerDto);
        Set<UserEntity> users = new HashSet<>();
        passengerEntity.setUserEntities(users);
        passengerGenericDao.save(passengerEntity);
        return passengerEntity;
    }


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
    public List<TrainDto> getDirectTrains(String departStation, String arrivalStation, Date departDate) {
        //getting schedules for station on departing date
        List<ScheduleEntity> startStationSchedule = scheduleService.getScheduleByStationNameAndDepartDate(departStation, departDate);
        List<TrainDto> trains = new ArrayList<>();

        //format for client
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");


        for(ScheduleEntity scheduleEntity: startStationSchedule){

            TrainDto trainDto = trainEntityDtoMapper.trainEntityToTrainDto(scheduleEntity.getTrainEntity());

            //check train on having start departing station and arrival station
            if(trainDto.getStations().contains(departStation) && trainDto.getStations().contains(arrivalStation)){

                //checking that depart station is before arrival station
                if(trainDto.getStations().indexOf(departStation) < trainDto.getStations().indexOf(arrivalStation)){

                    //getting arrival station schedule for train by departing date
                    List<ScheduleEntity> destStationsArrival = scheduleService.findScheduleByTrainAndDepartDate(scheduleEntity.getTrainEntity(), scheduleEntity.getDepartDate());
                    for(ScheduleEntity arrivalSchedule : destStationsArrival){
                        if(arrivalSchedule.getStationEntity().getName().equals(arrivalStation)){
                            trainDto.setDepartDate(format.format(scheduleEntity.getDepartDate()));
                            trainDto.setArrivalDate(format.format(arrivalSchedule.getArrivalDate()));
                            //getting counts  bought tickets
                            Long count = ticketService.getCountTicketsByTrainAndDate(scheduleEntity.getTrainEntity(), departDate);
                            //set counts free tickets
                            trainDto.setSeats(trainDto.getSeats() - count.intValue());
                            trains.add(trainDto);
                        }
                    }
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
            String[] roleArr = roleDto.getName().split("_");
            roles.add(roleArr[1]);
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
