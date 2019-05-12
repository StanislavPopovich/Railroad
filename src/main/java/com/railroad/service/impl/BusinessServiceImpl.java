package com.railroad.service.impl;
import com.railroad.dto.passenger.PassengerDto;
import com.railroad.dto.route.RouteDto;
import com.railroad.dto.schedule.ScheduleMessageInfoDto;
import com.railroad.dto.schedule.ScheduleUpdateDto;
import com.railroad.dto.station.StationDto;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.dto.train.TrainDto;
import com.railroad.dto.train.TrainScheduleDto;
import com.railroad.dto.train.TrainTargetDto;
import com.railroad.dto.train.TrainTicketDto;
import com.railroad.dto.way.WayDto;
import com.railroad.mapper.PassengerEntityDtoMapper;
import com.railroad.mapper.RouteDtoMapper;
import com.railroad.mapper.TrainEntityDtoMapper;
import com.railroad.entity.*;
import com.railroad.service.api.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BusinessServiceImpl extends BaseService implements BusinessService {

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
    private ScheduleService scheduleService;


    @Autowired
    private TrainEntityDtoMapper trainEntityDtoMapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PassengerEntityDtoMapper passengerEntityDtoMapper;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private RouteService routeService;






    @Override
    public void saveStationAndWay(WayDto wayDto) {
        StationDto stationDto = new StationDto();
        stationDto.setName(wayDto.getFirstStation());
        stationService.save(stationDto);
        wayService.save(wayDto);

    }

    @Override
    public List<TrainScheduleDto> getTrainsFromSchedule(String station, Date date) {
        //getting schedules for station on departing date
        List<ScheduleEntity> stationSchedule = scheduleService.
                getSchedulesByStationNameAndDepartDate(station, date);
        List<TrainScheduleDto> trainScheduleDtos = new ArrayList<>();
        for(ScheduleEntity scheduleEntity: stationSchedule){
            trainScheduleDtos.add(trainEntityDtoMapper.trainEntityToTrainScheduleDto(scheduleEntity.getTrainEntity()));
        }
        return trainScheduleDtos;
    }

    @Override
    @Transactional
    public List<TicketDto> getNotActualTickets() {
        //getting current User
        UserEntity currentUser = userService.getCurrentUser();
        return ticketService.getNotActualTickets(currentUser);
    }

    @Override
    @Transactional
    public List<TicketDto> getActualTickets() {
        UserEntity currentUser = userService.getCurrentUser();
        return ticketService.getActualTickets(currentUser);
    }

    @Transactional
    @Override
    public List<PassengerDto> getPassengersOfCurrentUser() {
        UserEntity currentUser = userService.getCurrentUser();
        List<PassengerDto> passengers = passengerEntityDtoMapper.passengerEntitiesToPassengerDtos(currentUser.getPassengerEntities());
        return passengers;
    }

    @Transactional
    @Override
    public List<TicketDto> getPassengerNotActualTickets(PassengerDto passengerDto) {
        UserEntity currentUser = userService.getCurrentUser();
        PassengerEntity passengerEntity = passengerService.
                findPassengerByNameAndBirthDate(passengerEntityDtoMapper.passengerDtoToEntity(passengerDto));
        return ticketService.getPassengerNotActualTickets(currentUser, passengerEntity);
    }

    @Override
    public List<String> getDepartDatesForTrain(Integer trainNumber) {
        TrainEntity trainEntity = trainService.findTrainEntityByNumber(trainNumber);
        List<Date> dates = scheduleService.getDepartDatesForTrain(trainEntity);
        List<String> strDates = new ArrayList<>();
        for(Date date: dates){
            strDates.add(date.toString());
        }
        return strDates;
    }

    @Transactional
    @Override
    public void removeTrainFromSchedule(Integer trainNumber, String departingDate) {
        Date departDate = getDate(departingDate, "yyyy-MM-dd");
        TrainEntity train = trainService.findTrainEntityByNumber(trainNumber);
        List<TicketEntity> tickets = ticketService.getTicketsByTrainAndDepartDate(train, departDate);
        for(TicketEntity ticket: tickets){
            // send emails
            ticketService.removeTicketByNumber(ticket.getId());
        }
        scheduleService.removeSchedulesByTrainAndDepartDate(train, departDate);
    }

    @Transactional
    @Override
    public ScheduleUpdateDto getScheduleUpdateDtosByTrainAdnDate(Integer trainNumber, Date departDate) {
        TrainEntity train = trainService.findTrainEntityByNumber(trainNumber);
        return scheduleService.getScheduleByTrainAndDepartDate(train, departDate);
    }

    @Transactional
    @Override
    public List<PassengerDto> getTrainPassengers(Integer trainNumber, Date departDate) {
        TrainEntity trainEntity = trainService.findTrainEntityByNumber(trainNumber);
        List<PassengerEntity> passengers = ticketService.getTrainPassengers(trainEntity, departDate);
        return passengerEntityDtoMapper.passengerEntitiesToPassengerDtos(passengers);
    }

    @Override
    @Transactional
    public List<ScheduleMessageInfoDto> getActualSchedule() {
        return scheduleService.getActualSchedule(new Date());
    }

    @Transactional
    @Override
    public boolean isPassengerAlreadyBoughtTicket(TrainTicketDto trainTicketDto, PassengerDto passengerDto) {
        PassengerEntity passengerEntity = passengerEntityDtoMapper.passengerDtoToEntity(passengerDto);
        if(!passengerService.isAlreadyExist(passengerEntity)){
            return false;
        }
        passengerEntity = passengerService.findPassengerByNameAndBirthDate(passengerEntity);
        TrainEntity trainEntity = trainService.findTrainEntityByNumber(trainTicketDto.getNumber());
        ScheduleEntity scheduleEntity = scheduleService.
                findScheduleByTrainAndDepartDate(trainEntity, getDate(trainTicketDto.getDepartDate(), "dd-MM-yyyy HH:mm"));

        return ticketService.isPassengerAlreadyBoughtTicket(passengerEntity, trainEntity, scheduleEntity.getDepartDateFromFirstStation());
    }

    @Transactional
    @Override
    public boolean[][] saveTrain(TrainDto trainDto) {
        boolean [][] error = new boolean[2][2];
        error[0][0] = checkLength(trainDto.getNumber());
        error[0][1] = checkLength(trainDto.getSeats());
        if(!error[0][0] || !error[0][1]){
            return error;
        }
        error[1][0] = trainAlreadyExist(trainDto.getNumber());
        error[1][1] = chechRouteExist(trainDto.getStations());
        if(!error[1][0] || !error[1][1]){
            return error;
        }
        trainService.save(trainDto);
        return error;
    }

    @Transactional
    @Override
    public List<TicketDto> getPassengerActualTickets(PassengerDto passengerDto) {
        UserEntity currentUser = userService.getCurrentUser();
        PassengerEntity passengerEntity = passengerService.
                findPassengerByNameAndBirthDate(passengerEntityDtoMapper.passengerDtoToEntity(passengerDto));
        return ticketService.getPassengerActualTickets(currentUser, passengerEntity);
    }

    private boolean checkLength(Integer trainDtoFiels){
        if(trainDtoFiels.toString().length() > 4){
            return false;
        }
        return true;
    }

    private boolean trainAlreadyExist(Integer trainNumber){
        if(trainService.trainAlreadyExist(trainNumber)){
            return false;
        }
        return true;
    }

    private boolean chechRouteExist(List<String> stations){
        List<RouteDto> routes = routeService.getAllRoutes(stations.get(0), stations.get(stations.size() - 1));
        for(RouteDto routeDto: routes){
            int count = 0;
            List<String> routeStations = routeDto.getStations();
            for(int i = 0; i < stations.size(); i++){
                for(int j = 0; j < routeStations.size(); j++){
                    if(stations.get(i).equals(routeStations.get(j))){
                        count++;
                    }
                }
            }
            if(count == stations.size()){
                return true;
            }
        }
        return false;
    }







}
